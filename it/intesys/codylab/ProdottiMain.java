package it.intesys.codylab.prodotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdottoService {

    private List<Prodotto> prodotti = new ArrayList<Prodotto>();
    private int prossimoId = 0;

    public Optional<Prodotto> getProdottoById(int id) {
        return prodotti.stream().filter(p -> p.getId() == id).findFirst();
    }

    public boolean addProdotto(String descrizione, float prezzo) {
        Prodotto prodotto = new Prodotto(++prossimoId, descrizione, prezzo);
        return prodotti.add(prodotto);
    }

    public boolean updateProdotto(int id, String descrizione, float prezzo) {
        Optional<Prodotto> opt = getProdottoById(id);
        if (opt.isPresent()) {
            opt.get()
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
        Optional<Prodotto> trovato = getProdottoById(id);
        if (trovato.isPresent()) {
            prodotti.remove(trovato.get());
            return true;
        }
        return false;
    }
}