package it.intesys.codylab;

import it.intesys.codylab.messaggio.Messaggio;
import it.intesys.codylab.messaggio.MessaggioConAutoreImpl;
import it.intesys.codylab.messaggio.MessaggioImpl;
import it.intesys.codylab.messaggio.MessaggioStatico;

public class MySecondCodyLabApp {

    public static void main(String[] args) {
        MessaggioStatico msg = new MessaggioStatico("Dania");
        System.out.println(msg.messaggio());
        primoMetodoDiStampa();
    }

    private static void primoMetodoDiStampa() {
        MessaggioStatico messaggioStatico1 =
                new MessaggioStatico("Betty");
        MessaggioStatico messaggioStatico2 =
                new MessaggioStatico("Betty");
        boolean equals = messaggioStatico1.equals(messaggioStatico2);
        System.out.println("messaggioStatico1 " + messaggioStatico1.toString());
        System.out.println("messaggioStatico2 " + messaggioStatico2.toString());
        System.out.println("m1 e m2 sono uguali? " + equals);
    }
}
