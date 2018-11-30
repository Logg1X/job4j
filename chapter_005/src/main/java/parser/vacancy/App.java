package parser.vacancy;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.time.LocalDateTime;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;
import static parser.vacancy.ParserHTML.getURL;

public class App implements Job {
    private Dao dao;
    private ParserHTML parserHTML;
    private LocalDateTime allYear = LocalDateTime.now().with(firstDayOfYear());
    private LocalDateTime oneDay = LocalDateTime.now().minusDays(1);
    private String connectionPropertyPath = "connectionSQLite.properties";
    private String queryPropertyPath = "query.sql";
    private static final Logger LOG = LogManager.getLogger(App.class.getName());

    public App(String connectionPropertyPath, String queryPropertyPath) {
        this.connectionPropertyPath = connectionPropertyPath;
        this.queryPropertyPath = queryPropertyPath;
        this.dao = new Dao(this.connectionPropertyPath,this.queryPropertyPath);
        this.parserHTML = new ParserHTML();
    }

    private boolean checkFirstStart() {
        return dao.getDateUpdate().isEmpty();
    }

    private void start() {
        if (checkFirstStart()) {
            firstStart(dao, parserHTML);
        } else {
            nextStart(dao, parserHTML);
            System.out.println("gjdnjhyjt dsgjkytybt");
        }
    }

    private void firstStart(Dao dao, ParserHTML parserHTML) {
        dao.insertVacancyInDB(parserHTML
                .parser(getURL(),allYear)
        );
        dao.insertDateUpdate();
    }
    private void nextStart(Dao dao, ParserHTML parserHTML) {
        dao.insertVacancyInDB(parserHTML
        .parser(getURL(),oneDay));
    }


    @Override
public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    System.out.println("sdfgdfgsdfhdfgjdfghjskdjvhjdashjgvsghjfdghjfvdgjdfds");
        App app = new App("connectionSQLite.properties"
                , "query.sql");
        app.start();
}
}
