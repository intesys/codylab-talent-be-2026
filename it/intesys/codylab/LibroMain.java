package it.intesys.codylab;

import it.intesys.codylab.libro.Libro;
import it.intesys.codylab.libro.LibroService;
import it.intesys.codylab.libro.Lingua;

import java.util.List;
import java.util.Optional;

public class LibroMain {
    public static void main(String[] args) {
        gestioneCatalogoLibri();
    }

    public static void gestioneCatalogoLibri() {
        System.out.println("=== Gestione catalogo libri ===");
        LibroService servizioLibri = new LibroService();

        List<Libro> elencoLibri = servizioLibri.getLibri();
        if (elencoLibri.isEmpty()) {
            System.out.println("Il catalogo libri è vuoto.");
        } else {
            System.out.println("Il catalogo contiene " + elencoLibri.size() + " libri.");
        }

        servizioLibri.addLibro("I promessi sposi", "Alessandro Manzoni", Lingua.IT);
        servizioLibri.addLibro("The Great Gatsby", "F. Scott Fitzgerald", Lingua.EN);
        servizioLibri.addLibro("Il fu Mattia Pascal", "Luigi Pirandello", Lingua.IT);

        System.out.println("\nCatalogo dopo inserimenti:");
        elencoLibri = servizioLibri.getLibri();
        for (Libro libro : elencoLibri) {
            System.out.println(libro);
        }

        servizioLibri.updateLibro(2, "Gatsby il magnifico", "Francis Scott Fitzgerald", Lingua.IT);
        System.out.println("\nDopo aggiornamento del libro con id=2:");
        elencoLibri = servizioLibri.getLibri();
        for (Libro libro : elencoLibri) {
            System.out.println(libro);
        }

        servizioLibri.deleteLibro(1);
        System.out.println("\nDopo eliminazione del libro con id=1:");
        elencoLibri = servizioLibri.getLibri();
        for (Libro libro : elencoLibri) {
            System.out.println(libro);
        }

        Optional<Libro> eliminato = servizioLibri.getLibroById(1);
        if (eliminato.isPresent()) {
            System.err.println("Errore: il libro 1 non è stato eliminato.");
        } else {
            System.out.println("Libro 1 eliminato con successo.");
        }
    }
}
