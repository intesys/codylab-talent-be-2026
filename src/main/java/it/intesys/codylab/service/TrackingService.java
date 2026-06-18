package it.intesys.codylab.service;

import com.zaxxer.hikari.HikariDataSource;
import it.intesys.codylab.db.config.HikariDataSourceProvider;
import it.intesys.codylab.db.model.Activity;
import it.intesys.codylab.db.model.Project;
import it.intesys.codylab.db.model.ProjectStatus;
import it.intesys.codylab.db.model.Tracking;
import it.intesys.codylab.db.repository.ActivityRepository;
import it.intesys.codylab.db.repository.ProjectRepository;
import it.intesys.codylab.db.repository.TrackingRepository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class TrackingService {

    public void insertTrack(Tracking tracking) {
        DataSource dataSource = HikariDataSourceProvider.getDataSource();

        //controllo se il progetto è attivo
        long activityId = tracking.getActivityId();
        ActivityRepository activityRepository = new ActivityRepository(dataSource);
        Optional<Activity> activityOptional = activityRepository.findById(activityId);
        if (activityOptional.isEmpty()) {
            throw new IllegalArgumentException("Activity not found");
        }
        Activity activity = activityOptional.get();
        long projectId = activity.getProjectId();
        ProjectRepository projectRepository = new ProjectRepository(dataSource);
        Optional<Project> projectOptional = projectRepository.findById(projectId);
        if (projectOptional.isEmpty()) {
            throw new IllegalArgumentException("Project not found");
        }
        Project project = projectOptional.get();
        if (List.of(ProjectStatus.CLOSED, ProjectStatus.COMPLETED).contains(project.getStatus())) {
            throw new IllegalArgumentException("Project is already closed");
        }

        TrackingRepository trackingRepository = new TrackingRepository(dataSource);
        trackingRepository.insert(tracking);
    }
}
