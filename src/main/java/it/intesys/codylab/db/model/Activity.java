package it.intesys.codylab.db.model;

import java.time.LocalDate;

public class Activity {

    private Long id;
    private String name;
    private Integer estimatedHours;
    private LocalDate createDate;
    private LocalDate updateDate;
    private Long projectId;

    public Activity() {
    }

    public Long getId() {
        return id;
    }

    public Activity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Activity setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getEstimatedHours() {
        return estimatedHours;
    }

    public Activity setEstimatedHours(Integer estimatedHours) {
        this.estimatedHours = estimatedHours;
        return this;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public Activity setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
        return this;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public Activity setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
        return this;
    }

    public Long getProjectId() {
        return projectId;
    }

    public Activity setProjectId(Long projectId) {
        this.projectId = projectId;
        return this;
    }
}