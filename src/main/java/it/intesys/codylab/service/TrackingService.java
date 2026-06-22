package it.intesys.codylab.service;

import it.intesys.codylab.db.model.Activity;
import it.intesys.codylab.db.model.Project;
import it.intesys.codylab.db.model.ProjectStatus;
import it.intesys.codylab.db.model.Tracking;
import it.intesys.codylab.db.repository.ActivityRepository;
import it.intesys.codylab.db.repository.ProjectRepository;
import it.intesys.codylab.db.repository.TrackingRepository;

import java.util.List;
import java.util.Optional;

public class TrackingService {

    private final TrackingRepository trackingRepository;
    private final ActivityRepository activityRepository;
    private final ProjectRepository projectRepository;

    public TrackingService(TrackingRepository trackingRepository, ActivityRepository activityRepository, ProjectRepository projectRepository) {
        this.trackingRepository = trackingRepository;
        this.activityRepository = activityRepository;
        this.projectRepository = projectRepository;
    }

    public void insertTrack(Tracking tracking) {

        //controllo se il progetto è attivo
        long activityId = tracking.getActivityId();
        Optional<Activity> activityOptional = activityRepository.findById(activityId);
        if (activityOptional.isEmpty()) {
            throw new IllegalArgumentException("Activity not found");
        }
        Activity activity = activityOptional.get();
        long projectId = activity.getProjectId();
        Optional<Project> projectOptional = projectRepository.findById(projectId);
        if (projectOptional.isEmpty()) {
            throw new IllegalArgumentException("Project not found");
        }
        Project project = projectOptional.get();
        if (List.of(ProjectStatus.CLOSED, ProjectStatus.COMPLETED).contains(project.getStatus())) {
            throw new IllegalArgumentException("Project is already closed");
        }

        trackingRepository.insert(tracking);
    }
}
