package it.intesys.codylab.db.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String surname;

    @Column(nullable = false, unique = true, length = 64)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "create_date", nullable = false)
    private LocalDate createDate;

    @Column(name = "update_date")
    private LocalDate updateDate;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public User setId(Integer id) {
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
