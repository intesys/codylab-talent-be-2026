package it.intesys.codylab;

import it.intesys.codylab.messaggio.*;
import it.intesys.codylab.formageometrica.*;

public class MyFirstCodyLabApplication {


    public static void main(String[] args) {
        System.out.println("Ciao!");
        stampa();
    }

// aggiuto static al metodo stampa per poterlo chiamare dal main
    private static void stampa() {
        //Messaggio mess = new MessaggioStatico("Betty");
        //Messaggio mess = new MessaggioFormaGeometrica(new Cerchio(5.0f));
        //Messaggio mess = new MessaggioFormaGeometrica(new Cerchio(6.0f));
        //Messaggio mess = new MessaggioFormaGeometrica(new Quadrato(5.0f));
        Messaggio mess = new MessaggioFormaGeometrica(new Rettangolo(5.0f, 3.0f));
        System.out.println(mess.messaggio());
    }
}
