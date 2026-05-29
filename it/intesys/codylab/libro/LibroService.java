package it.intesys.codylab.libro;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibroService {
    private List<Libro> libri = new ArrayList<>();
    private int currentId = 0;

    public Optional<Libro> getLibroById(int id) {
        return libri.stream()
                .filter(l -> l.getId() == id)
                .findFirst();
    }

    public boolean addLibro(String titolo, String autore, Lingua lingua) {
        Libro libro = new Libro(++currentId, titolo, autore, lingua);
        return libri.add(libro);
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
        Optional<Libro> opt = getLibroById(id);
        if (opt.isPresent()) {
            return libri.remove(opt.get());
        }
        return false;
    }
}