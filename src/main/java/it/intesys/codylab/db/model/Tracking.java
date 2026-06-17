package it.intesys.codylab.db.model;

import java.time.LocalDate;

public class Tracking {

    private long id;
    private String description;
    private int durationMinutes;
    private long activityId;
    private long userId;
    private LocalDate createDate;
    private LocalDate updateDate;

    // Getters and Setters matching the fluent pattern
    public long getId() {
        return id;
    }

    public Tracking setId(long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Tracking setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public Tracking setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
        return this;
    }

    public long getActivityId() {
        return activityId;
    }

    public Tracking setActivityId(long activityId) {
        this.activityId = activityId;
        return this;
    }

    public long getUserId() {
        return userId;
    }

    public Tracking setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public Tracking setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
        return this;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public Tracking setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
        return this;
    }
}