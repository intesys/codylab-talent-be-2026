package it.intesys.codylab.db.repository;

import it.intesys.codylab.db.config.HikariDataSourceProvider;
import it.intesys.codylab.db.model.Activity;
import it.intesys.codylab.db.model.Project;
import it.intesys.codylab.db.model.ProjectStatus;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ActivityRepositoryIntTest {
    private ActivityRepository activityRepository = new ActivityRepository(HikariDataSourceProvider.getDataSource());

    //Test findById con l'id  esiste
    @Test
    void testfindById() {
        Optional<Activity> activity = activityRepository.findById(1L);
        assert(activity.isPresent());
    }

    //Test findById con l'id non esiste
    @Test
    void test2findById() {
        Optional<Activity> activity = activityRepository.findById(100L);
        assert(activity.isEmpty());
    }

    // 1. Test findAll:restituisca tutti i record presenti nella tabella tracking
    @Test
    void findAll() {
        Optional<Activity> activity = activityRepository.findAll().stream().findAny();
        assert(activity.isPresent());
    }

    @Test
    void insert() {
        Activity newActivity = new Activity();
        newActivity.setName("New Activity")
                .setEstimatedHours(50)
                .setCreateDate(LocalDate.now())
                .setUpdateDate(LocalDate.now())
                .setProjectId(1L);

        Long activityId = activityRepository.insert(newActivity);
        assert(activityId != null);

        Optional<Activity> project = activityRepository.findById(activityId);
        assert(project.isPresent());
    }

    @Test
    void update() {
        Activity newActivity = new Activity();
        newActivity.setName("New Activity")
                .setEstimatedHours(50)
                .setCreateDate(LocalDate.now())
                .setUpdateDate(LocalDate.now())
                .setProjectId(1L);

        activityRepository.update(1L, newActivity);
        Optional<Activity> activity = activityRepository.findById(1L);
        assert(activity.isPresent());
        assert(activity.get().getName().equals("New Activity"));

    }
}