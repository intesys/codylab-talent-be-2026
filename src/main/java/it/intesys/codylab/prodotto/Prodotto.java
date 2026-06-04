package it.intesys.codylab.prodotto;

public class Prodotto {

    private int id;
    private String descrizione;
    private float prezzo;

    public Prodotto(int id, String descrizione, float prezzo) {
        this.id = id;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
    }

    public int getId() {
        return id;
    }

    public Prodotto setId(int id) {
        this.id = id;
        return this;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public Prodotto setDescrizione(String descrizione) {
        this.descrizione = descrizione;
        return this;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public Prodotto setPrezzo(float prezzo) {
        this.prezzo = prezzo;
        return this;
    }

    @Override
    public String toString() {
        return "Prodotto{" +
                "id=" + id +
                ", descrizione='" + descrizione + '\'' +
                ", prezzo=" + prezzo +
                '}';
    }

}
