package it.intesys.codylab.db.repository;

import it.intesys.codylab.db.config.HikariTestDataSourceProvider;
import it.intesys.codylab.db.model.Tracking;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrackingRepositoryTest {

    private TrackingRepository trackingRepository = new TrackingRepository(HikariTestDataSourceProvider.getDataSource());

    @Test
    void findAll() {
        List<Tracking> trackings = trackingRepository.findAll();

        assertNotNull(trackings, "La lista non deve essere nulla");
        assertFalse(trackings.isEmpty(), "La lista deve contenere almeno un record.");


    }
}