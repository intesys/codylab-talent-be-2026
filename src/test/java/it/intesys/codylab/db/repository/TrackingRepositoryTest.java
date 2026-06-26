package it.intesys.codylab.db.repository;

import it.intesys.codylab.db.config.HikariTestDataSourceProvider;
import it.intesys.codylab.db.model.Tracking;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;

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
    @Test
    void findById() {
        Tracking tracking = trackingRepository.findById(1L).orElse(null);

        assertNotNull(tracking, "Dovrebbe esistere un tracciamento con ID 1.");
        assertEquals(1L, tracking.getId(), "L'ID restituito dovrebbe essere 1.");

    }
    @Test
    void insert() {
        Tracking nuova = new Tracking();
        nuova.setDescription("test: Nuova attività da inserire");
        nuova.setDurationMinutes(30);
        nuova.setActivityId(1L);
        nuova.setUserId(1L);
        nuova.setCreateDate(java.time.LocalDate.now());

        long getId = trackingRepository.insert(nuova);
        assertNotNull(getId, "L'ID non deve essere nullo");
        assertTrue(trackingRepository.findById(getId).isPresent(), "Il record deve esistere nel database.");

    }
    @Test
    void update() {
        Tracking tracking = trackingRepository.findById(1L).get();
        tracking.setDescription("Descrizione modificata");

        trackingRepository.update(1L, tracking);
        Tracking updatedTracking = trackingRepository.findById(1L).get();
        assertEquals("Descrizione modificata", updatedTracking.getDescription());
    }
    
}