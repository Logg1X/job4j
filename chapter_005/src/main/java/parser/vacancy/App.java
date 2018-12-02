package parser.vacancy;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Properties;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class App {
    private static final Logger LOG = LogManager.getLogger(App.class.getName());
    private Dao dao;
    private ParserHTML parserHTML;
    private ParserHH parserHH;
    private LocalDateTime allYear = LocalDateTime.now().with(firstDayOfYear());
    private LocalDateTime lastUpdate;


    public App(String connectionPropertyPath, String queryPropertyPath) {
        this.dao = new Dao(connectionPropertyPath, queryPropertyPath);
        this.parserHTML = new ParserHTML();
        this.parserHH = new ParserHH();
        if (this.checkFirstStart()) {
            this.lastUpdate = allYear;
        } else {
            this.lastUpdate = LocalDateTime.parse(dao.getDateUpdate().get(0).getDate());
        }
    }

    public static void main(String[] args) throws SchedulerException {
        Properties prop = new Properties();
        try (InputStream in = App.class.getClassLoader().getResourceAsStream("config.properties")) {
            prop.load(in);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        JobDetail job = newJob(MyJob.class).build();

//        CronTrigger trigger = newTrigger()
//                .withSchedule(cronSchedule(prop.getProperty("cron.time")))
//                .forJob(job)
//                .startNow()
//                .build();

        SimpleTrigger trigger = newTrigger()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(30)
                        .repeatForever())
                .startNow()
                .forJob(job)
                .build();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }

    private boolean checkFirstStart() {
        return dao.getDateUpdate().isEmpty();
    }

    public void start() {
        if (checkFirstStart()) {
            firstStart(dao, parserHTML, parserHH);
        } else {
            nextStart(dao, parserHTML, parserHH);
        }
    }

    private void firstStart(Dao dao, ParserHTML parserHTML, ParserHH parserHH) {
        dao.insertVacancyInDB(
                parserHTML.parser(allYear)
                , "INSERT_VACANSY_IN_TABLE_SQL_RU"
        );
        dao.insertVacancyInDB(
                parserHH.parser(allYear)
                , "INSERT_VACANSY_IN_TABLE_HH_RU"
        );
        dao.insertDateUpdate();
    }

    private void nextStart(Dao dao, ParserHTML parserHTML, ParserHH parserHH) {
        dao.insertVacancyInDB(
                parserHTML.parser(lastUpdate)
                , "INSERT_VACANSY_IN_TABLE_SQL_RU"
        );
        dao.insertVacancyInDB(
                parserHH.parser(lastUpdate)
                , "INSERT_VACANSY_IN_TABLE_HH_RU"
        );
        dao.updateDateUpdate();
    }
}
