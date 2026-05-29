package it.intesys.codylab.messaggio;

public class MessaggioConAutoreImplFactory {

    private MessaggioConAutoreImplFactory() {}

    public static MessaggioConAutoreImpl getInstance(String autore) {
        return new MessaggioConAutoreImpl(autore);
    }
}
