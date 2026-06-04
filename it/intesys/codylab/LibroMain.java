package it.intesys.codylab;

import it.intesys.codylab.libro.Libro;
import it.intesys.codylab.libro.LibroService;
import it.intesys.codylab.libro.Lingua;

import java.util.List;
import java.util.Optional;

public class LibroMain {
    public static void main(String[] args) {
        gestioneLibri();
    }

    public static void gestioneLibri() {
        System.out.println("=== Gestione Libri ===");
        LibroService libroService = new LibroService();

        List<Libro> libri = libroService.getLibri();
        if (libri.isEmpty()) {
            System.out.println("La lista libri è vuota.");
        } else {
            System.out.println("La lista contiene già " + libri.size() + " libri.");
        }

        libroService.addLibro("Il nome della rosa", "Umberto Eco", Lingua.IT);
        libroService.addLibro("1984", "George Orwell", Lingua.EN);
        libroService.addLibro("La coscienza di Zeno", "Italo Svevo", Lingua.IT);

        libri = libroService.getLibri();
        System.out.println("\nLibri presenti:");
        for (Libro libro : libri) {
            System.out.println(libro);
        }

        libroService.updateLibro(2, "Nineteen Eighty-Four", "George Orwell", Lingua.EN);
        System.out.println("\nDopo aggiornamento del libro 2:");
        libri = libroService.getLibri();
        for (Libro libro : libri) {
            System.out.println(libro);
        }

        libroService.deleteLibro(1);
        System.out.println("\nDopo cancellazione del libro 1:");
        libri = libroService.getLibri();
        for (Libro libro : libri) {
            System.out.println(libro);
        }

        Optional<Libro> libroCancellato = libroService.getLibroById(1);
        if (libroCancellato.isPresent()) {
            System.err.println("La cancellazione del libro 1 non è andata a buon fine");
        } else {
            System.out.println("Libro 1 cancellato correttamente.");
        }
    }
}