package it.intesys.codylab;

import it.intesys.codylab.libro.Libro;
import it.intesys.codylab.libro.LibroService;
import it.intesys.codylab.libro.Lingua;

import java.util.List;
import java.util.Optional;

public class LibriMain {

    public static void main(String[] args) {
        System.out.println("--- INIZIO TEST LIBRO ---");
        catalogoLibri();
    }

    public static void catalogoLibri() {
        LibroService libroService = new LibroService();

        // 1. Create - Aggiungiamo i libri
        libroService.addLibro("Il Gattopardo", "Giuseppe Tomasi di Lampedusa", Lingua.IT);
        libroService.addLibro("Hamlet", "William Shakespeare", Lingua.EN);
        libroService.addLibro("Divina Commedia", "Dante Alighieri", Lingua.IT);

        // 2. Read - Mostriamo la lista iniziale
        List<Libro> libri = libroService.getLibri();
        System.out.println("Lista iniziale dei libri:");
        for (Libro libro : libri) {
            System.out.println(libro);
        }

        // 3. Update - Modifichiamo il titolo del libro con ID 1
        libroService.updateLibro(1, "Il Gattopardo - Edizione Speciale", "Giuseppe Tomasi di Lampedusa", Lingua.IT);
        System.out.println("\nDopo l'aggiornamento del libro 1:");
        for (Libro libro : libroService.getLibri()) {
            System.out.println(libro);
        }

        // 4. Delete - Cancelliamo il libro con ID 2 (Hamlet)
        libroService.deleteLibro(2);
        System.out.println("\nDopo la cancellazione del libro 2:");
        for (Libro libro : libroService.getLibri()) {
            System.out.println(libro);
        }

        // Verifica finale della cancellazione
        Optional<Libro> libroCancellato = libroService.getLibroById(2);
        if (libroCancellato.isPresent()) {
            System.err.println("Errore: Il libro 2 è ancora presente!");
        } else {
            System.out.println("\nLibro 2 cancellato correttamente!");
        }
    }
}