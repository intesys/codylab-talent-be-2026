package it.intesys.codylab.service;

import it.intesys.codylab.db.model.Activity;
import it.intesys.codylab.db.model.Project;
import it.intesys.codylab.db.model.ProjectStatus;
import it.intesys.codylab.db.model.Tracking;
import it.intesys.codylab.db.repository.ActivityRepository;
import it.intesys.codylab.db.repository.ProjectRepository;
import it.intesys.codylab.db.repository.TrackingRepository;

import java.time.LocalDate;
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

    public List<Tracking> findAll() {
        return trackingRepository.findAll();
    }

    public Optional<Tracking> findById(long id) {
        return trackingRepository.findById(id);
    }

    public long insertTrack(Tracking tracking) {
        validateActivityAndProject(tracking.getActivityId());

        if (tracking.getCreateDate() == null) {
            tracking.setCreateDate(LocalDate.now());
        }

        return trackingRepository.insert(tracking);
    }

    public boolean update(long id, Tracking tracking) {
        validateActivityAndProject(tracking.getActivityId());

        tracking.setUpdateDate(LocalDate.now());
        return trackingRepository.update(id, tracking) > 0;
    }

    public boolean deleteById(long id) {
        return trackingRepository.deleteById(id) > 0;
    }

    // Controllo di business: l'activity deve esistere e il progetto deve essere aperto
    private void validateActivityAndProject(long activityId) {
        Optional<Activity> activityOptional = activityRepository.findById(activityId);
        if (activityOptional.isEmpty()) {
            throw new IllegalArgumentException("Activity not found");
        }
        Activity activity = activityOptional.get();
        Optional<Project> projectOptional = projectRepository.findById(activity.getProjectId());
        if (projectOptional.isEmpty()) {
            throw new IllegalArgumentException("Project not found");
        }
        Project project = projectOptional.get();
        if (List.of(ProjectStatus.CLOSED, ProjectStatus.COMPLETED).contains(project.getStatus())) {
            throw new IllegalArgumentException("Project is already closed");
        }
    }
}