package it.intesys.codylab.prodotto;

public class Libro {

    private int id;
    private String titolo;
    private String autore;
    private String lingua;

    public Libro(int id, String autore, String lingua) {
        this.id = id;
        this.autore = autore;
        this.lingua = lingua;
    }

    public int getId() {
        return id;
    }

    public Libro setId(int id) {
        this.id = id;
        return this;
    }

    public String getAutore() {
        return autore;
    }

    public Libro setAutore(String autore) {
        this.autore = autore;
        return this;
    }

    public String getLingua() {
        return lingua;
    }

    public Libro setLingua(String lingua) {
        this.lingua = lingua;
        return this;
    }

    public String getTitolo() {
        return titolo;
    }

    public Libro setTitolo(String titolo) {
        this.titolo = titolo;
        return this;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", autore='" + autore + '\'' +
                ", lingua='" + lingua + '\'' +
                '}';
    }
}