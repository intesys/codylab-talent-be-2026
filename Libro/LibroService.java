package it.intesys.codylab.prodotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibroService {

    private List<Libro> libri = new ArrayList<>();
    private int currentLibroId = 0;

    public Optional<Libro> getLibroById(int id) {
        return libri.stream().filter(p -> p.getId() == id).findFirst();
    }

    public boolean addLibro(String autore, String lingua) {
        Libro libro = new Libro(++currentLibroId, autore, lingua);
        return libri.add(libro);
    }

    public boolean updateLibro(int id, String autore, String lingua) {
        Optional<Libro> libroOpt = getLibroById(id);
        if (libroOpt.isPresent()) {
            libroOpt.get()
                    .setAutore(autore)
                    .setLingua(lingua);
            return true;
        }
        return false;
    }

    public List<Libro> getLibri() {
        return libri;
    }

    public boolean deleteLibro(int id) {
        Optional<Libro> libro = getLibroById(id);
        if (libro.isPresent()) {
            libri.remove(libro.get());
            return true;
        }
        return false;
    }
}