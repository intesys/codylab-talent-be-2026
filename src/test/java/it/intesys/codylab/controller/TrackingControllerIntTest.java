package it.intesys.codylab.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import it.intesys.codylab.db.model.Tracking;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TrackingControllerIntTest {

    @Autowired
    private TrackingController trackingController;

    @Test
    void shouldReturnListOfTrackings() {
        // Testa diretamente o método do controller garantindo que a integração com o Service/Repository funciona
        List<Tracking> trackings = trackingController.getAll();
        assertNotNull(trackings);
    }
}