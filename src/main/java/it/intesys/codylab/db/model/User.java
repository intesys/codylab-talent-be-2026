package it.intesys.codylab.db.model;

import java.time.LocalDate;

public class User {

    private Long id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private LocalDate createDate;
    private LocalDate updateDate;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public User setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public User setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
        return this;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public User setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
        return this;
    }
}