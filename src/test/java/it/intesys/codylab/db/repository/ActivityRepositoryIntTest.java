package it.intesys.codylab.db.repository;

import it.intesys.codylab.db.config.HikariTestDataSourceProvider;
import it.intesys.codylab.db.model.Activity;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ActivityRepositoryIntTest {

    private ActivityRepository activityRepository = new ActivityRepository(HikariTestDataSourceProvider.getDataSource());

    @Test
    void testfindById() {

        Optional<Activity> activity = activityRepository.findById(1L);
        assert(activity.isPresent());

    }

    @Test
    void test2findById() {
        DataSource ds = HikariTestDataSourceProvider.getDataSource();
        ActivityRepository activityRepository = new ActivityRepository(ds);
        Optional<Activity> activity = activityRepository.findById(100L);
        assert(activity.isEmpty());

    }


}