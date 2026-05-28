package it.intesys.codylab;

import it.intesys.codylab.messaggio.MessaggioConAutoreImpl;

public class MySecondCodyLabApp {
    public static void main(String[] args) {
        MessaggioConAutoreImpl msg = new MessaggioConAutoreImpl("Nicolas");
        System.out.println(msg.messaggio());
    }
}