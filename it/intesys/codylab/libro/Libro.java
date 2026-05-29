package it.intesys.codylab.libro;

public class Libro {
    private int id;
    private String titolo;
    private String autore;
    private Lingua lingua;

    public Libro(int id, String titolo, String autore, Lingua lingua) {
        this.id = id;
        this.titolo = titolo;
        this.autore = autore;
        this.lingua = lingua;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public Lingua getLingua() {
        return lingua;
    }

    public void setLingua(Lingua lingua) {
        this.lingua = lingua;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titolo='" + titolo + '\'' +
                ", autore='" + autore + '\'' +
                ", lingua=" + lingua +
                '}';
    }
}