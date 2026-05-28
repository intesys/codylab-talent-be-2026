package it.intesys.codylab;

import it.intesys.codylab.messaggio.Messaggio;
import it.intesys.codylab.messaggio.MessaggioConAutoreImpl;

public class MySecondCodyLabApp {

    public static void main(String[] args) {
        Messaggio message = new MessaggioConAutoreImpl("Nicola");
        System.out.println(message.messaggio());
    }

    private static void primoMetodoDiStampa() {
    }
}
