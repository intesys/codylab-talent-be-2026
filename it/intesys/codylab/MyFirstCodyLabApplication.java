package it.intesys.codylab;

import it.intesys.codylab.messaggio.Messaggio;
import it.intesys.codylab.messaggio.MessaggioStatico;

public class MyFirstCodyLabApplication {


    public static void main(String[] args) {
        System.out.println("Ciao!");
    }

    private void stampa() {
        Messaggio mess = new MessaggioStatico("Betty");
        // Messaggio messaggio = new MessaggioFormaGeometrica(new Cerchio(5.0f));
        // Messaggio messaggio = new MessaggioFormaGeometrica(new Cerchio(6.0f));
        System.out.println(mess.messaggio());
    }
}
