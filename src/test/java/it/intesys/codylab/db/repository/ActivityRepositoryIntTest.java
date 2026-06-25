package it.intesys.codylab.db.repository;

import it.intesys.codylab.db.config.HikariDataSourceProvider;
import it.intesys.codylab.db.model.Activity;
import org.junit.jupiter.api.Test;



import javax.sql.DataSource;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ActivityRepositoryIntTest {

    @Test
    void findById() {
        DataSource dataSource = HikariDataSourceProvider.getDataSource();
        ActivityRepository activityRepository = new ActivityRepository(dataSource);
        Optional<Activity> activity = activityRepository.findById(1L);
        assert(activity.isPresent());
    }

    @Test
    void findById1() {
        DataSource dataSource = HikariDataSourceProvider.getDataSource();
        ActivityRepository activityRepository = new ActivityRepository(dataSource);
        Optional<Activity> activity = activityRepository.findById(100L);
        assert(activity.isEmpty());
    }
}