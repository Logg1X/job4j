package parserVacancy;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class ParserHTML {

    private static final Logger LOG = LogManager.getLogger(ParserHTML.class.getName());
    private static final String URL = "https://www.sql.ru/forum/job-offers";
    private static final String TABLE_FORUMTABLE = "table.forumTable";
    private static final String TR_TAG = "tr";
    private static final String TD_PASTLISTTOPIC = "td.postlisttopic";
    private static final String A_TAG = "a";
    private static final String TD_ALTCOL = "td.altCol";
    private static final String TABLE_SORT_OPTION = "table.sort_options";
    private static final String B_TAG = "b";
    private static final String TD_TAG = "td";
    private static final String HREF_ATTRIBUTE = "href";
    private static final String REGEX = ".*([Jj]ava|[Jj]avaSE|JAVA|[Jj]avaEE)(?!([Ss]cript|\\s+[Ss]cript)).*";
    private static final Set<Vacancy> vacancies = new HashSet<>();
    private static final String TODAY = "сегодня";
    private static final String YESTERDAY = "вчера";

    private int getAllPage(String url) {
        int result = 0;
        Document document;
        try {
            document = Jsoup.connect(url).get();
            String allPage = document.body()
                    .select(TABLE_SORT_OPTION)
                    .last()
                    .select(TR_TAG)
                    .select(TD_TAG)
                    .select(A_TAG)
                    .last()
                    .text();
            LOG.info(String.format("Всего страниц: %s", allPage));
            result = Integer.parseInt(allPage);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    public Set<Vacancy> parser(String url, Date date) {
        Document doc = this.getDocument(url);
        Set<Vacancy> resunt = new HashSet<>();
        List<Element> elements = doc.body().select(TABLE_FORUMTABLE).first().select(TR_TAG);
        elements.forEach(element -> {
            String title = element.children().select(TD_PASTLISTTOPIC).text();
            String link = "";
            String author;
            String dateCol;
            LocalDateTime date1;
            if (Pattern.matches(REGEX, title)) {
                LOG.info(String.format("Найдена вакансия: %s", title));
            }
        });
        return resunt;
    }

    private LocalDateTime convertigStringToDateFormat(String date) {
        LocalDateTime result = null;
        if (date.contains(TODAY) || date.contains(YESTERDAY)) {
            String[] pointOnDay = date.split(",");
            if (pointOnDay[0].equalsIgnoreCase(TODAY)) {
                result = LocalDateTime.now();
            }
        }
        return result;
    }

    private Document getDocument(String url) {
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    public static void main(String[] args) {
        ParserHTML parser = new ParserHTML();
        System.out.println(parser.getAllPage(parser.URL));
    }
}
