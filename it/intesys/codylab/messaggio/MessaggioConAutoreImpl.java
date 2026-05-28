package it.intesys.codylab.messaggio;

public class MessaggioConAutoreImpl extends MessaggioBaseConAutore {

    public MessaggioConAutoreImpl(String autore) {
        super(autore);
    }

    @Override
    public String messaggio() {
        return "Benvenuto in CodyLab da parte di " + getAutore();
    }
}