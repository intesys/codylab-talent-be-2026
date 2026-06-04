package it.intesys.codylab.messaggio;

public class MessaggioImpl implements Messaggio {

    private static final String MESSAGGIO_DEFAULT = "Ciao";

    @Override
    public String messaggio() {
        return MESSAGGIO_DEFAULT;
    }
}
