package it.intesys.codylab.service;

import it.intesys.codylab.db.config.HikariTestDataSourceProvider;
import it.intesys.codylab.db.model.Tracking;
import it.intesys.codylab.db.repository.ActivityRepository;
import it.intesys.codylab.db.repository.ProjectRepository;
import it.intesys.codylab.db.repository.TrackingRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.time.LocalDate;

class TrackingServiceIntTest {

    @Test
    @DisplayName("Given a Project Completed When Insert Tracking Then An IllegalArgumentException Is Thrown")
    void givenProjectCompletedWhenInsertTrackingThenAnIllegalArgumentExceptionIsThrown() {
        // Arrange
        DataSource dataSource = HikariTestDataSourceProvider.getDataSource();
        TrackingService trackingService = new TrackingService(
                new TrackingRepository(dataSource),
                new ActivityRepository(dataSource),
                new ProjectRepository(dataSource)
        );

        Tracking tracking = new Tracking()
                .setActivityId(16)
                .setDurationMinutes(30)
                .setDescription("Sviluppo nuova funzionalita")
                .setUserId(1)
                .setCreateDate(LocalDate.now());

        //act
        try {
            trackingService.insertTrack(tracking);
            assert (false);
        } catch (IllegalArgumentException e) {
            assert (true);
        }

    }

    @Test
    @DisplayName("Given a Project not completed When Insert Tracking Then the tracking is persisted")
    void givenProjectIsOpenWhenInsertTrackingThenAnInsertIsDone() {
        // Arrange
        DataSource dataSource = HikariTestDataSourceProvider.getDataSource();
        TrackingRepository trackingRepository = new TrackingRepository(dataSource);
        int sizeBefore = trackingRepository.findAll().size();
        TrackingService trackingService = new TrackingService(
                trackingRepository,
                new ActivityRepository(dataSource),
                new ProjectRepository(dataSource)
        );

        Tracking tracking = new Tracking()
                .setActivityId(2)
                .setDurationMinutes(30)
                .setDescription("Sviluppo nuova funzionalita")
                .setUserId(1)
                .setCreateDate(LocalDate.now());

        //act
        trackingService.insertTrack(tracking);

        // assert
        int sizeAfter = trackingRepository.findAll().size();
        assert (sizeAfter == sizeBefore + 1);
    }
}