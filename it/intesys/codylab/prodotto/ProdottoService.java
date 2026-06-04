package it.intesys.codylab.prodotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdottoService {

    private List<Prodotto> prodotti = new ArrayList<Prodotto>();
    private int currentProdottoId = 0;

    public Optional<Prodotto> getProdottoById(int id) {
//        for (Prodotto prodotto : prodotti) {
//            if (prodotto.getId() == id) {
//                return Optional.of(prodotto);
//            }
//        }
//        return Optional.empty();
        return prodotti.stream().filter(p -> p.getId() == id).findFirst();
    }

    public boolean addProdotto(String descrizione, float prezzo) {
        Prodotto prodotto = new Prodotto(++currentProdottoId, descrizione, prezzo);
        return prodotti.add(prodotto);
    }

    public boolean updateProdotto(int id, String descrizione, float prezzo) {
        Optional<Prodotto> prodotto = getProdottoById(id);
        if (prodotto.isPresent()) {
            prodotto.get()
                    .setDescrizione(descrizione)
                    .setPrezzo(prezzo);
            return true;
        }
        return false;
    }

    public List<Prodotto> getProdotti() {
        return prodotti;
    }

    public boolean deleteProdotto(int id) {
        // 1. Cerco il prodotto tramite il suo ID
        Optional<Prodotto> prodottoTrovato = getProdottoById(id);

        // 2. Se il prodotto è presente nella lista, lo rimuoviamo
        if (prodottoTrovato.isPresent()) {
            Prodotto p = prodottoTrovato.get();
            return prodotti.remove(p);
        }

        // 3. Se non lo trova, ritorna false
        return false;
    }
}
