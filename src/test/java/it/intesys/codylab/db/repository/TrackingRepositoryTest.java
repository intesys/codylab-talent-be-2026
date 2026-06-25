package it.intesys.codylab.db.repository;

import it.intesys.codylab.db.config.HikariDataSourceProvider;
import it.intesys.codylab.db.model.Project;
import it.intesys.codylab.db.model.Tracking;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TrackingRepositoryTest {

    private TrackingRepository trackingRepository = new TrackingRepository(HikariDataSourceProvider.getDataSource());


    @Test
    void findById1() {
        Optional<Tracking> tracking = trackingRepository.findById(1L);
        assert(tracking.isPresent());
    }
}