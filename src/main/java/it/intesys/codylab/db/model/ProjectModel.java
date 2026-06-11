package it.intesys.codylab.db.model;

import java.time.LocalDate;

public class ProjectModel {

    private Long id;
    private String title;
    private String description;
    private Integer estimatedHours;
    private ProjectStatus status;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate createDate;
    private LocalDate updateDate;

    public Long getId() {
        return id;
    }

    public ProjectModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ProjectModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProjectModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getEstimatedHours() {
        return estimatedHours;
    }

    public ProjectModel setEstimatedHours(Integer estimatedHours) {
        this.estimatedHours = estimatedHours;
        return this;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public ProjectModel setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public ProjectModel setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public ProjectModel setStatus(ProjectStatus status) {
        this.status = status;
        return this;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public ProjectModel setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
        return this;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public ProjectModel setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
        return this;
    }

    @Override
    public String toString() {
        return "ProjectModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}