package it.intesys.codylab.messaggio;

public class MessaggioConAutoreImpl extends MessaggioBaseConAutore {

    public MessaggioConAutoreImpl(String autore) {
        this.autore = autore;
    }

    public String messaggio() {
        return "Benvenuto in CodyLab da parte di " + autore();
    }
}