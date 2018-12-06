package parser.vacancy;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class ParserHH {
    private static final Logger LOG = LogManager.getLogger(ParserHH.class.getName());
    private String baseUrl = "https://api.hh.ru/vacancies";

    public String getRequest(String urlToRead) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(urlToRead);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            try (BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public Set<Vacancy> parser(LocalDateTime dateTime) {
        Set<Vacancy> result = new HashSet<>();
        int pages = new JsonParser().parse(getRequest(createRequest(dateTime) + 0)).getAsJsonObject().get("pages").getAsInt();
        LOG.info(String.format("Всего страниц на HH.ru: %s", pages));
        for (int i = 0; i < pages; i++) {
            JsonElement n = new JsonParser().parse(getRequest(createRequest(dateTime) + i));
            JsonArray obi = n.getAsJsonObject().getAsJsonArray("items");
            obi.forEach(jsonElement -> {
                JsonObject jOb = jsonElement.getAsJsonObject();
                String title = jOb.get("name").getAsString();
                String author = jOb.get("employer").getAsJsonObject().get("name").getAsString();
                String link = jOb.get("apply_alternate_url").getAsString();
                String[] strDate = jOb.get("published_at").getAsString().split("\\+");
                LocalDateTime date = LocalDateTime.parse(strDate[0]);
                result.add(new Vacancy(title, link, author, date));
            });

        }
        LOG.info(String.format("Всего вакансий на HH.ru найдено: %s", result.size()));
        return result;
    }

    private String createRequest(LocalDateTime dateTime) {
        return new StringBuilder(baseUrl)
                .append("?area=1") //регион(город)
                .append("&text=NAME:(java+NOT+script+NOT+android)") //текст запроса
                .append("&employment=full") //тип занятости (полна/не полная)
                .append("&order_by=publication_time") // сортировка по дате публикации
                .append(String.format("&date_from=%s", dateTime)) // ограничевающая поиск "снизу" дата
                .append("&per_page=100") //кол-во вакансий на одной странице
                .append("&page=") //№ страницы
                .toString();
    }
}
