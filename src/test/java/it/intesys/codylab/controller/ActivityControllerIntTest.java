package it.intesys.codylab.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import it.intesys.codylab.db.model.Activity;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ActivityControllerIntTest {

    @Autowired
    private ActivityController activityController;

    @Test
    void shouldReturnListOfActivities() {
        List<Activity> activities = activityController.getAll();
        assertNotNull(activities);
    }
}

