package it.intesys.codylab;

import it.intesys.codylab.messaggio.MessaggioStatico;

public class MySecondCodyLabApp {

    public static void main(String[] args) {
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
