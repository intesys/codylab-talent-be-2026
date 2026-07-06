package it.intesys.codylab.service;

import it.intesys.codylab.db.model.Activity;
import it.intesys.codylab.db.repository.ActivityRepository;

import java.util.List;

public class ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public List<Activity> findAll() {
        return activityRepository.findAll();
    }
}