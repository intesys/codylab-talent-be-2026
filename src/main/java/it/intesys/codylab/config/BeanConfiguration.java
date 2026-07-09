package it.intesys.codylab.config;

import it.intesys.codylab.db.config.HikariDataSourceProvider;
import it.intesys.codylab.db.repository.ActivityRepository;
import it.intesys.codylab.db.repository.ProjectRepository;
import it.intesys.codylab.db.repository.TrackingRepository;
import it.intesys.codylab.service.ActivityService;
import it.intesys.codylab.service.ProjectService;
import it.intesys.codylab.service.TrackingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class BeanConfiguration {

    @Bean
    public DataSource createDataSource() {
        return HikariDataSourceProvider.getDataSource();
    }

    @Bean
    public ProjectRepository creteProjectRepository(DataSource dataSource) {
        return new ProjectRepository(dataSource);
    }

    @Bean
    public ActivityRepository createActivityRepository(DataSource dataSource) {
        return new ActivityRepository(dataSource);
    }

    @Bean
    public TrackingRepository createTrackingRepository(DataSource dataSource) {
        return new TrackingRepository(dataSource);
    }

    @Bean
    public ProjectService createProjectService(ProjectRepository projectRepository) {
        return new ProjectService(projectRepository);
    }

    @Bean
    public TrackingService createTrackingService(TrackingRepository trackingRepository,
                                                 ActivityRepository activityRepository,
                                                 ProjectRepository projectRepository) {
        return new TrackingService(trackingRepository, activityRepository, projectRepository);
    }

    @Bean
    public ActivityService createActivityService(ActivityRepository activityRepository) {
        return new ActivityService(activityRepository);
    }
}