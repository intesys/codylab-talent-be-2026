package it.intesys.codylab.db.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProdottoModel {

    private Long id;
    private String descrizione;
    private BigDecimal prezzo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ProdottoModel() {
    }

    public ProdottoModel(Long id, String descrizione, BigDecimal prezzo, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public ProdottoModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public ProdottoModel setDescrizione(String descrizione) {
        this.descrizione = descrizione;
        return this;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public ProdottoModel setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public ProdottoModel setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public ProdottoModel setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @Override
    public String toString() {
        return "ProdottoModel{" +
                "id=" + id +
                ", descrizione='" + descrizione + '\'' +
                ", prezzo=" + prezzo +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

