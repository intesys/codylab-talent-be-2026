package it.intesys.codylab.libro;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibroService {

    private List<Libro> libri = new ArrayList<>();
    private int currentLibroId = 0;

    // C - Create (Aggiungere un libro)
    public boolean addLibro(String titolo, String autore, Lingua lingua) {
        Libro libro = new Libro(++currentLibroId, titolo, autore, lingua);
        return libri.add(libro);
    }

    // R - Read (Prendere tutti i libri)
    public List<Libro> getLibri() {
        return libri;
    }

    // R - Read (Trovare un libro per ID)
    public Optional<Libro> getLibroById(int id) {
        return libri.stream().filter(l -> l.getId() == id).findFirst();
    }

    // U - Update (Aggiornare un libro esistente)
    public boolean updateLibro(int id, String titolo, String autore, Lingua lingua) {
        Optional<Libro> libroTrovato = getLibroById(id);
        if (libroTrovato.isPresent()) {
            libroTrovato.get()
                    .setTitolo(titolo)
                    .setAutore(autore)
                    .setLingua(lingua);
            return true;
        }
        return false;
    }

    // D - Delete (Cancellare un libro tramite l'ID)
    public boolean deleteLibro(int id) {
        Optional<Libro> libroTrovato = getLibroById(id);
        if (libroTrovato.isPresent()) {
            Libro l = libroTrovato.get();
            return libri.remove(l);
        }
        return false;
    }
}