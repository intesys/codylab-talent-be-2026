package it.intesys.codylab.db.repository;

import it.intesys.codylab.db.config.HikariDataSourceProvider;
import it.intesys.codylab.db.config.HikariTestDataSourceProvider;
import it.intesys.codylab.db.model.Tracking;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class TrackingRepositoryIntTest {


    private TrackingRepository trackingRepository = new TrackingRepository(HikariDataSourceProvider.getDataSource());

    // 1. Test findAll:restituisca tutti i record presenti nella tabella tracking
    @Test
    void findAll() {
        Optional<Tracking> tracking = trackingRepository.findAll().stream().findAny();
        assert(tracking.isPresent());
    }


    //Test findById con l'id esiste
    @Test
    void testfindById() {
        Optional<Tracking> tracking = trackingRepository.findById(1L);
        assert(tracking.isPresent());
    }

    //Test findById con l'id non esiste
    @Test
    void test2findById() {
        Optional<Tracking> tracking = trackingRepository.findById(100L);
        assert(tracking.isEmpty());
    }

    @Test
    void insert() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }
}





