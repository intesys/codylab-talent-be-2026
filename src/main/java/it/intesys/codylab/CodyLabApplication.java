package it.intesys.codylab;

import it.intesys.codylab.db.config.HikariDataSourceProvider;
import it.intesys.codylab.db.model.Tracking;
import it.intesys.codylab.db.repository.ActivityRepository;
import it.intesys.codylab.db.repository.ProjectRepository;
import it.intesys.codylab.db.repository.TrackingRepository;
import it.intesys.codylab.service.TrackingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.time.LocalDate;

public class CodyLabApplication {

    private static final Logger logger = LoggerFactory.getLogger(CodyLabApplication.class);

    static void main() {
        DataSource dataSource = HikariDataSourceProvider.getDataSource();
        TrackingService trackingService = new TrackingService(
                new TrackingRepository(dataSource),
                new ActivityRepository(dataSource),
                new ProjectRepository(dataSource)
        );

        Tracking tracking = new Tracking()
                .setActivityId(2)
                .setDurationMinutes(30)
                .setDescription("Sviluppo nuova funzionalita")
                .setUserId(1)
                .setCreateDate(LocalDate.now());
        trackingService.insertTrack(tracking);

        tracking = new Tracking()
                .setActivityId(5)
                .setDurationMinutes(120)
                .setDescription("Riunione di analisi")
                .setUserId(1)
                .setCreateDate(LocalDate.now());
        trackingService.insertTrack(tracking);
    }
}
