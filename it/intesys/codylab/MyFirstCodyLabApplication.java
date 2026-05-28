package it.intesys.codylab;

import it.intesys.codylab.messaggio.Messaggio;
import it.intesys.codylab.messaggio.MessaggioStatico;
import it.intesys.codylab.messaggio.MessaggioFormaGeometrica;
import it.intesys.codylab.formageometrica.Quadrato;

public class MyFirstCodyLabApplication {

    public static void main(String[] args) {
        System.out.println("Ciao!");
        stampa();
    }

    private static void stampa() {
        // Messaggio mess = new MessaggioStatico("Betty");
        // Messaggio mess = new MessaggioFormaGeometrica(new Cerchio(5.0f));
        // Messaggio mess = new MessaggioFormaGeometrica(new Cerchio(6.0f));
        Messaggio mess = new MessaggioFormaGeometrica(new Quadrato(5.0f));
        System.out.println(mess.messaggio());
    }
}