package it.intesys.codylab;

import it.intesys.codylab.messaggio.Messaggio;
import it.intesys.codylab.messaggio.MessaggioStatico;
import it.intesys.codylab.formageometrica.Cerchio;
import it.intesys.codylab.messaggio.MessaggioFormaGeometrica;
import it.intesys.codylab.formageometrica.Quadrato;
import it.intesys.codylab.formageometrica.Rettangolo;

public class MyFirstCodyLabApplication {


    //public static void main(String[] args) {
    //    System.out.println("Ciao!");}

    public static void main(String[] args) {
        stampa();
    }

    private static void stampa() {
        //Messaggio mess = new MessaggioStatico("Betty");
        // Messaggio messaggio = new MessaggioFormaGeometrica(new Cerchio(5.0f));
        // Messaggio messaggio = new MessaggioFormaGeometrica(new Cerchio(6.0f));
        //Messaggio messaggio = new MessaggioFormaGeometrica(new Quadrato(5.0f));
        //Messaggio messaggio = new MessaggioFormaGeometrica(new Rettangolo(4.0f, 6.0f));
        //System.out.println(mess.messaggio());
        System.out.println(messaggio.messaggio());
    }
}