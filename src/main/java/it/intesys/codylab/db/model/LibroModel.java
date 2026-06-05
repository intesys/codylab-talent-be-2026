package it.intesys.codylab.db.model;

import java.time.LocalDateTime;

public class LibroModel {

    private Long id;
    private String titolo;
    private String autore;
    private String lingua;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public LibroModel() {
    }

    public LibroModel(Long id, String titolo, String autore, String lingua, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.titolo = titolo;
        this.autore = autore;
        this.lingua = lingua;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public LibroModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitolo() {
        return titolo;
    }

    public LibroModel setTitolo(String titolo) {
        this.titolo = titolo;
        return this;
    }

    public String getAutore() {
        return autore;
    }

    public LibroModel setAutore(String autore) {
        this.autore = autore;
        return this;
    }

    public String getLingua() {
        return lingua;
    }

    public LibroModel setLingua(String lingua) {
        this.lingua = lingua;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LibroModel setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LibroModel setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @Override
    public String toString() {
        return "LibroModel{" +
                "id=" + id +
                ", titolo='" + titolo + '\'' +
                ", autore='" + autore + '\'' +
                ", lingua='" + lingua + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

