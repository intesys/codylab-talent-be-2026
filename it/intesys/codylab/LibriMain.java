package it.intesys.codylab;

import it.intesys.codylab.prodotto.LibroService;
import it.intesys.codylab.prodotto.Libro;

import java.util.List;
import java.util.Optional;

public class LibriMain {

    public static void main(String[] args) {
        tipodilibri();
    }

    public static void tipodilibri() {
        System.out.println("Inizio");
        LibroService libroService = new LibroService();

        libroService.addLibro("Leonardo", "Italiano");
        libroService.addLibro("Marco", "Inglese");
        libroService.addLibro("Ludivico", "Spagnolo");

        List<Libro> libri = libroService.getLibri();
        System.out.println("Trovati i prodotti:");
        for (Libro libro : libri) {
            System.out.println(libro);
        }

        libroService.updateLibro(1, "Francesco", "Italiano");
        libri = libroService.getLibri();
        for (Libro libro : libri) {
            System.out.println(libro);
        }

        libroService.deleteLibro(2);
        libri = libroService.getLibri();
        System.out.println("Trovati i prodotti:");
        for (Libro libro : libri) {
            System.out.println(libro);
        }

        Optional<Libro> prodottoCancellato = libroService.getLibroById(2);
        if (prodottoCancellato.isPresent()) {
            System.err.println("La cancellazione non è andata a buon fine");
        } else {
            System.out.println("Prodotto 2 cancellato correttamente");
        }
    }
}