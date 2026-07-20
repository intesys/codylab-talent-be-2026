package it.intesys.codylab.controller;

import it.intesys.codylab.controller.api.ActivityControllerApi;
import it.intesys.codylab.controller.dto.ActivityApiDTO;
import it.intesys.codylab.controller.dto.ProblemApiDTO;
import it.intesys.codylab.db.model.Activity;
import it.intesys.codylab.mapper.ActivityMapper;
import it.intesys.codylab.service.ActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class OpenApiActivityController implements ActivityControllerApi {

    private final ActivityService activityService;
    private final ActivityMapper activityMapper;

    public OpenApiActivityController(ActivityService activityService, ActivityMapper activityMapper) {
        this.activityService = activityService;
        this.activityMapper = activityMapper;
    }

    @Override
    public ResponseEntity<List<ActivityApiDTO>> getAllActivities() {
        return ResponseEntity.ok(activityMapper.mapToApiDtoList(activityService.findAll()));
    }

    @Override
    public ResponseEntity<ActivityApiDTO> getActivityById(Long activityId) {
        Optional<Activity> activityOptional = activityService.findById(activityId);
        if (activityOptional.isPresent()) {
            return ResponseEntity.ok(activityMapper.mapToApiDto(activityOptional.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<ActivityApiDTO> createActivity(ActivityApiDTO activityApiDTO) {
        Activity activityData = activityMapper.mapToEntity(activityApiDTO);
        activityData.setCreateDate(LocalDate.now());
        long activityId = activityService.insert(activityData);
        return ResponseEntity.status(201).body(activityMapper.mapToApiDto(activityService.findById(activityId).get()));
    }

    @Override
    public ResponseEntity<ActivityApiDTO> updateActivity(Long activityId, ActivityApiDTO activityApiDTO) {
        Optional<Activity> activityOptional = activityService.findById(activityId);
        if (activityOptional.isPresent()) {
            Activity activityData = activityMapper.mapToEntity(activityApiDTO);
            activityData.setId(activityId);
            activityData.setUpdateDate(LocalDate.now());
            activityService.update(activityId, activityData);

            return ResponseEntity.ok(activityMapper.mapToApiDto(activityService.findById(activityId).get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteActivity(Long activityId) {
        Optional<Activity> activityOptional = activityService.findById(activityId);
        if (activityOptional.isPresent()) {
            activityService.delete(activityId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}