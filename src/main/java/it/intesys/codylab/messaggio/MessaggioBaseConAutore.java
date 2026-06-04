package it.intesys.codylab.messaggio;

public abstract class MessaggioBaseConAutore implements  Messaggio {

    private final String autore;

    protected MessaggioBaseConAutore(String autore) {
        this.autore = autore;
    }

    protected String getAutore() {
        return autore;
    }
}
