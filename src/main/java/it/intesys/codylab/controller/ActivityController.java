package it.intesys.codylab.controller;

import it.intesys.codylab.db.model.Activity;
import it.intesys.codylab.service.ActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/old/activities")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        System.out.println("----- Chiamato costruttore ActivityController");
        this.activityService = activityService;
    }

    @GetMapping
    public List<Activity> findAll() {
        System.out.println("Chiamato findAll per le Activities");
        return activityService.findAll();
    }


    @GetMapping("/{activityId}")
    public ResponseEntity<Activity> findById(@PathVariable("activityId") Long activityId) {
        Optional<Activity> activityOptional = activityService.findById(activityId);
        if (activityOptional.isPresent()) {
            System.out.println("Activity " + activityId + " trovata");
            return ResponseEntity.ok(activityOptional.get());
        } else {
            System.out.println("Activity " + activityId + " non trovata");
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Long> insert(@RequestBody Activity activityData) {
        activityData.setCreateDate(LocalDate.now());
        long activityId = activityService.insert(activityData);
        System.out.println("Activity " + activityId + " inserita");
        return ResponseEntity.created(URI.create("/activities/" + activityId)).body(activityId);
    }

    @PutMapping("/{activityId}")
    public ResponseEntity<Void> update(@PathVariable("activityId") Long activityId, @RequestBody Activity activityData) {
        Optional<Activity> activityOptional = activityService.findById(activityId);
        if (activityOptional.isPresent()) {
            Activity existingActivity = activityOptional.get();

            // Aggiorniamo i campi specifici di Activity mappati dal DB
            existingActivity.setName(activityData.getName());
            existingActivity.setEstimatedHours(activityData.getEstimatedHours());
            existingActivity.setProjectId(activityData.getProjectId());
            existingActivity.setUpdateDate(LocalDate.now());

            activityService.update(activityId, existingActivity);
            System.out.println("Activity " + activityId + " aggiornata");
            return ResponseEntity.ok().build();
        } else {
            System.out.println("Activity " + activityId + " non trovata per aggiornamento");
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{activityId}")
    public ResponseEntity<Void> delete(@PathVariable("activityId") Long activityId) {
        Optional<Activity> activityOptional = activityService.findById(activityId);
        if (activityOptional.isPresent()) {
            activityService.delete(activityId);
            System.out.println("Activity " + activityId + " eliminata");
            return ResponseEntity.ok().build();
        } else {
            System.out.println("Activity " + activityId + " non trovata per eliminazione");
            return ResponseEntity.notFound().build();
        }
    }
}
