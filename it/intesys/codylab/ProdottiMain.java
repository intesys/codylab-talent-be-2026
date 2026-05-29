package it.intesys.codylab;

import it.intesys.codylab.prodotto.Prodotto;
import it.intesys.codylab.prodotto.ProdottoService;

import java.util.List;
import java.util.Optional;

public class ProdottiMain {

    public static void main(String[] args) {
        catalogoProdotti();
    }

    public static void catalogoProdotti() {
        System.out.println("Inizio");
        ProdottoService prodottoService = new ProdottoService();

        //es 1 check per vedere se la lista è vuota, evita i problemi di static
        List<Prodotto> prodottiIniziali = prodottoService.getProdotti();
        if (prodottiIniziali.isEmpty()) {
            System.out.println("Controllo iniziale: La lista è vuota.");
        } else {
            System.out.println("Controllo iniziale: La lista contiene prodotti.");
        }


        prodottoService.addProdotto("Mouse", 30.0f);
        prodottoService.addProdotto("Tastiera", 15.0f);
        prodottoService.addProdotto("Cuffie", 25.0f);

        List<Prodotto> prodotti = prodottoService.getProdotti();
        System.out.println("Trovati i prodotti:");
        for (Prodotto prodotto : prodotti) {
            System.out.println(prodotto);
        }

        //cambia il prodotto 1 da mouse a mouse wireless
        prodottoService.updateProdotto(1, "Mouse wireless", 45.0f);
        prodotti = prodottoService.getProdotti();
        System.out.println("Trovati i prodotti:");
        for (Prodotto prodotto : prodotti) {
            System.out.println(prodotto);
        }

        //rimuove il secondo prodotto
        prodottoService.deleteProdotto(2);
        prodotti = prodottoService.getProdotti();
        System.out.println("Trovati i prodotti:");
        for (Prodotto prodotto : prodotti) {
            System.out.println(prodotto);
        }
        Optional<Prodotto> prodottoCancellato = prodottoService.getProdottoById(2);
        if (prodottoCancellato.isPresent()) {
            System.err.println("La cancellazione non è andata a buon fine");
        } else {
            System.out.println("Prodotto 2 cancellato correttamente");
        }

    }
}
