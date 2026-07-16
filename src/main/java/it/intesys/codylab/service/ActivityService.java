package it.intesys.codylab.service;

import it.intesys.codylab.db.model.Activity;
import it.intesys.codylab.db.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        System.out.println("---- Chiamato costruttore di ActivityService");
        this.activityRepository = activityRepository;
    }

    public List<Activity> findAll() {
        return activityRepository.findAll();
    }

    public Optional<Activity> findById(Long activityId) {
        return activityRepository.findById(activityId);
    }

    public long insert(Activity activity) {
        return activityRepository.insert(activity);
    }

    public void update(Long activityId, Activity activity) {
        activityRepository.update(activityId, activity);
    }

    public void delete(Long activityId) {
        activityRepository.deleteById(activityId);
    }
}