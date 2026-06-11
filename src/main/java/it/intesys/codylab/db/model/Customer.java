package it.intesys.codylab.db.model;

import java.time.LocalDate;

public class Customer {

    private Long id;
    private String name;
    private LocalDate createDate;
    private LocalDate updateDate;

    public Long getId() {
        return id;
    }

    public Customer setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Customer setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public Customer setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
        return this;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public Customer setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
        return this;
    }
}