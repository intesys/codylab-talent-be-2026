package it.intesys.codylab.db.model;

import java.time.LocalDate;

public class Project {

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

    public Project setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Project setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Project setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getEstimatedHours() {
        return estimatedHours;
    }

    public Project setEstimatedHours(Integer estimatedHours) {
        this.estimatedHours = estimatedHours;
        return this;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Project setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Project setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public Project setStatus(ProjectStatus status) {
        this.status = status;
        return this;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public Project setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
        return this;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public Project setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
        return this;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", estimatedHours=" + estimatedHours +
                ", status=" + status +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }

}