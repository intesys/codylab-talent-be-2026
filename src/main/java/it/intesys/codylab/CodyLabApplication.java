package it.intesys.codylab;

import it.intesys.codylab.db.JdbcDemo;
import it.intesys.codylab.db.model.Project;
import it.intesys.codylab.db.model.ProjectStatus;
import it.intesys.codylab.db.model.Tracking;
import it.intesys.codylab.service.TrackingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public class CodyLabApplication {

    private static final Logger logger = LoggerFactory.getLogger(CodyLabApplication.class);
    static void main() {
        TrackingService trackingService = new TrackingService();

        Tracking tracking = new Tracking()
                .setActivityId(13)
                .setDurationMinutes(30)
                .setDescription("Sviluppo nuova funzionalita")
                .setUserId(1)
                .setCreateDate(LocalDate.now());
        try {
            trackingService.insertTrack(tracking);
        } catch (Exception e) {
            logger.error("Si e verificato un errore: ", e);
        }
    }
}
