package it.intesys.codylab.db.model;

import java.time.LocalDate;

public class Tracking {

    private Long id;
    private String description;
    private Integer minutesSpent;
    private Long activityId;
    private Long userId;
    private LocalDate createDate;
    private LocalDate updateDate;

    public Tracking() {
    }

    public Long getId() {return id;}

    public Tracking setId(Long id) {this.id = id; return this;}

    public String getDescription() {
        return description;}

    public Tracking setDescription(String description) {
        this.description = description;
        return this;}

    public Integer getMinutesSpent() {
        return minutesSpent;}

    public Tracking setMinutesSpent(Integer minutesSpent) {
        this.minutesSpent = minutesSpent;
        return this;}

    public Long getActivityId() {
        return activityId;}

    public Tracking setActivityId(Long activityId) {
        this.activityId = activityId;
        return this;}

    public Long getUserId() {
        return userId;}

    public Tracking setUserId(Long userId) {
        this.userId = userId;
        return this;}

    public LocalDate getCreateDate() {
        return createDate;}

    public Tracking setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
        return this;}

    public LocalDate getUpdateDate() {
        return updateDate;}

    public Tracking setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
        return this;}
}