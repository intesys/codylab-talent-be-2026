package it.intesys.codylab.libro;

public class Libro {

    private int id;
    private String titolo;
    private String autore;
    private Lingua lingua;

    // Costruttore
    public Libro(int id, String titolo, String autore, Lingua lingua) {
        this.id = id;
        this.titolo = titolo;
        this.autore = autore;
        this.lingua = lingua;
    }

    // Getter e Setter
    public int getId() {
        return id;
    }

    public Libro setId(int id) {
        this.id = id;
        return this;
    }

    public String getTitolo() {
        return titolo;
    }

    public Libro setTitolo(String titolo) {
        this.titolo = titolo;
        return this;
    }

    public String getAutore() {
        return autore;
    }

    public Libro setAutore(String autore) {
        this.autore = autore;
        return this;
    }

    public Lingua getLingua() {
        return lingua;
    }

    public Libro setLingua(Lingua lingua) {
        this.lingua = lingua;
        return this;
    }

    // Metodo toString per stampare i dati sulla console
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