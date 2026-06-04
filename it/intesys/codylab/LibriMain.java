package it.intesys.codylab;

import it.intesys.codylab.libro.Libro;
import it.intesys.codylab.libro.LibroService;
import it.intesys.codylab.libro.Lingua;

import java.util.List;
import java.util.Optional;

public class LibriMain {

    public static void main(String[] args) {
        catalogoLibri();
    }

    public static void catalogoLibri() {
        LibroService libroService = new LibroService();
//crea libri
        libroService.addLibro("I Promessi Sposi", "Alessandro Manzoni", Lingua.IT);
        libroService.addLibro("The Great Gatsby", "F. Scott Fitzgerald", Lingua.EN);
        libroService.addLibro("Uno, nessuno e centomila", "Luigi Pirandello", Lingua.IT);
//mostra la lista
        List<Libro> libri = libroService.getLibri();
        System.out.println("Lista iniziale dei libri:");
        for (Libro libro : libri) {
            System.out.println(libro);
        }

        libroService.updateLibro(1, "I Promessi Sposi - Edizione Illustrata", "Alessandro Manzoni", Lingua.IT);
        System.out.println("aggiornato il libro 1:");
        for (Libro libro : libroService.getLibri()) {
            System.out.println(libro);
        }

        libroService.deleteLibro(2);
        System.out.println("cancellato il libro 2:");
        for (Libro libro : libroService.getLibri()) {
            System.out.println(libro);
        }

        // Verifica della cancellazione
        Optional<Libro> libroCancellato = libroService.getLibroById(2);
        if (libroCancellato.isPresent()) {
            System.err.println("Errore: Il libro 2 è ancora presente!");
        } else {
            System.out.println("\nLibro 2 cancellato correttamente!");
        }
    }
}