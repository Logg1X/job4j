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
import java.util.*;
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
    private static final String DATE_FORMAT = "d MMMM yy, HH:mm";
    private static final String DATE_TIME_FORMAT = "dd LLLL yyyy, HH:mm";
    private static final String TIME_FORMAT = "HH:mm";
    private static final Map<String, String> MONTH = addAllMonth();


    private static Map<String, String> addAllMonth() {
        Map<String, String> month = new HashMap<>();
        month.put("янв", "января");
        month.put("фев", "февраля");
        month.put("мар", "марта");
        month.put("апр", "апреля");
        month.put("май", "мая");
        month.put("июн", "июня");
        month.put("июл", "июля");
        month.put("авг", "августа");
        month.put("сен", "сентября");
        month.put("окт", "октября");
        month.put("ноя", "ноября");
        month.put("дек", "декабря");
        return month;

    }

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
                    String dateCol = element.select(TD_ALTCOL).last().text();
                    LocalDateTime date = convertingStringToDateFormat(dateCol);
                    if (dateUpdate != null && date.compareTo(dateUpdate) < 0 || !isCurrentYear(dateUpdate)) {
                        LOG.info("Выход за диапозон даты.....STOP.....");
                        point = false;
                        break;
                    }
                    String link = element.select(TD_POSTLISTTOPIC).select(A_TAG).attr(HREF_ATTRIBUTE);
                    String author = element.select(TD_ALTCOL).first().text();
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
        LocalDate now = LocalDate.now();
        if (date.contains(TODAY) || date.contains(YESTERDAY)) {
            String[] pointOnDay = date.split(", ");
            if (pointOnDay[0].equalsIgnoreCase(TODAY)) {
                result = LocalDateTime.of(now,
                        LocalTime.parse(pointOnDay[1],
                                timeFormatter)
                );
            } else if (pointOnDay[0].equalsIgnoreCase(YESTERDAY)) {
                result = LocalDateTime.of(now.minusDays(1),
                        LocalTime.parse(pointOnDay[1],
                                timeFormatter)
                );
            }
        } else {
            String[] d = date.split(" ");
            if (MONTH.keySet().contains(d[1])) {
                d[1] = MONTH.get(d[1]);
                date = "";
                for (String s : d) {
                    date = String.format("%s %s", date, s).strip();
                }
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
