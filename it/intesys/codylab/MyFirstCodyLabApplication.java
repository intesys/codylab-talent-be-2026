package it.intesys.codylab;

import it.intesys.codylab.messaggio.Messaggio;
import it.intesys.codylab.messaggio.MessaggioFormaGeometrica;
import it.intesys.codylab.formageometrica.Rettangolo;

public class MyFirstCodyLabApplication {

    public static void main(String[] args) {
        System.out.println("Ciao!");
        stampa();
    }

    private static void stampa() {
        Messaggio mess = new MessaggioFormaGeometrica(new Rettangolo(4.0f, 6.0f));
        System.out.println(mess.messaggio());
    }
}