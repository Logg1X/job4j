package parser.vacancy;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;


public class ParserHTML {
    private static final Logger LOG = LogManager.getLogger(ParserHTML.class.getName());
    private static final String URL = "https://www.sql.ru/forum/job-offers/";
    private static final String TABLE_FORUMTABLE = "table.forumTable";
    private static final String TR_TAG = "tr";
    private static final String TD_POSTLISTTOPIC = "td.postslisttopic";
    private static final String A_TAG = "a";
    private static final String TD_ALTCOL = "td.altCol";
    private static final String HREF_ATTRIBUTE = "href";
    private static final String REGEX = ".*([Jj]ava|[Jj]avaSE|JAVA|[Jj]avaEE)(?!([Ss]cript|\\s+[Ss]cript)).*";
    private static final String TODAY = "сегодня";
    private static final String YESTERDAY = "вчера";
    private static final String DATE_FORMAT = "d MMM yy, HH:mm";
    private static final String TIME_FORMAT = "HH:mm";


    public Set<Vacancy> parser(LocalDateTime dateUpdate) {
        Set<Vacancy> result = new HashSet<>();
        int countPage = 1;
        boolean point = true;
        while (point) {
            Document doc = this.getDocument(URL + countPage);
            List<Element> elements = doc.body().select(TABLE_FORUMTABLE).first().select(TR_TAG);
            for (Element element : elements) {
                String[] title = element.select(TD_POSTLISTTOPIC).select(A_TAG).text().split("\\[");
                if (Pattern.matches(REGEX, title[0])) {
                    var dateCol = element.select(TD_ALTCOL).last().text();
                     var date = convertingStringToDateFormat(dateCol);
                    if (dateUpdate != null && date.compareTo(dateUpdate) < 0 || !isCurrentYear(dateUpdate)) {
                        LOG.info("Выход за диапозон даты.....STOP.....");
                        point = false;
                        break;
                    }
                    var link = element.select(TD_POSTLISTTOPIC).select(A_TAG).attr(HREF_ATTRIBUTE);
                    var author = element.select(TD_ALTCOL).first().text();
                    result.add(new Vacancy(title[0], link, author, date));
                }
            }
            countPage++;
        }
        LOG.info(String.format("Всего вакансий на sql.ru найдено: %s", result.size()));
        return result;
    }

    private LocalDateTime convertingStringToDateFormat(String date) {
        LocalDateTime result = null;
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(TIME_FORMAT);
        var now = LocalDate.now();
        if (date.contains(TODAY) || date.contains(YESTERDAY)) {
            String[] pointOnDay = date.split(", ");
            if (pointOnDay[0].equalsIgnoreCase(TODAY)) {
                result = LocalDateTime.of(now,
                        LocalTime.parse(pointOnDay[1],
                                timeFormatter
                        )
                );
            } else if (pointOnDay[0].equalsIgnoreCase(YESTERDAY)) {
                result = LocalDateTime.of(now.minusDays(1),
                        LocalTime.parse(pointOnDay[1],
                                timeFormatter
                        )
                );
            }
        } else {
            if (date.contains("май")) {
                date = date.replace("й", "я");
            }
            result = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT));
        }
        return result;
    }

    private Document getDocument(String url) {
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return document;
    }

    private boolean isCurrentYear(LocalDateTime dateTime) {
        return dateTime != null && LocalDateTime.now().getYear() == dateTime.getYear();
    }
}
