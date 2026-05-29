package it.intesys.codylab.libro;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibroService {
    private List<Libro> libri = new ArrayList<>();
    private int contatoreId = 0;

    public Optional<Libro> getLibroById(int id) {
        for (Libro l : libri) {
            if (l.getId() == id) {
                return Optional.of(l);
            }
        }
        return Optional.empty();
    }

    public boolean addLibro(String titolo, String autore, Lingua lingua) {
        Libro nuovo = new Libro(++contatoreId, titolo, autore, lingua);
        return libri.add(nuovo);
    }

    public boolean updateLibro(int id, String titolo, String autore, Lingua lingua) {
        Optional<Libro> opt = getLibroById(id);
        if (opt.isPresent()) {
            Libro libro = opt.get();
            libro.setTitolo(titolo);
            libro.setAutore(autore);
            libro.setLingua(lingua);
            return true;
        }
        return false;
    }

    public List<Libro> getLibri() {
        return libri;
    }

    public boolean deleteLibro(int id) {
        Optional<Libro> daEliminare = getLibroById(id);
        if (daEliminare.isPresent()) {
            return libri.remove(daEliminare.get());
        }
        return false;
    }
}