package it.intesys.codylab;

import it.intesys.codylab.prodotto.Prodotto;
import it.intesys.codylab.prodotto.ProdottoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class ProdottiMain {

    private static final Logger logger = LoggerFactory.getLogger(ProdottiMain.class);

    public static void main(String[] args) {
        catalogoProdotti();
    }

    public static void catalogoProdotti() {
        logger.debug("Inizio");
        ProdottoService prodottoService = new ProdottoService();
        prodottoService.addProdotto("Mouse", 30.0f);
        prodottoService.addProdotto("Tastiera", 15.0f);
        prodottoService.addProdotto("Cuffie", 25.0f);

        List<Prodotto> prodotti = prodottoService.getProdotti();
        logger.info("*** Trovati prodotti: ");
        for (Prodotto prodotto : prodotti) {
            logger.info("**" + prodotto);
        }

        prodottoService.updateProdotto(1, "Mouse wireless", 45.0f);
        prodotti = prodottoService.getProdotti();
        System.out.println("Trovati i prodotti:");
        for (Prodotto prodotto : prodotti) {
            System.out.println(prodotto);
        }

        prodottoService.deleteProdotto(2);
        prodotti = prodottoService.getProdotti();
        System.out.println("Trovati i prodotti:");
        for (Prodotto prodotto : prodotti) {
            System.out.println(prodotto);
        }
        Optional<Prodotto> prodottoCancellato = prodottoService.getProdottoById(2);
        if (prodottoCancellato.isPresent()) {
            logger.error("La cancellazione non è andata a buon fine");
        } else {
            System.out.println("Prodotto 2 cancellato correttamente");
        }

    }
}
