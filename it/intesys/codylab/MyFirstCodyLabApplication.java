package it.intesys.codylab;

import it.intesys.codylab.formageometrica.Rettangolo;
import it.intesys.codylab.messaggio.Messaggio;
import it.intesys.codylab.messaggio.MessaggioBaseConAutore;
import it.intesys.codylab.messaggio.MessaggioFormaGeometrica;

public class MyFirstCodyLabApplication {

    public static void main(String[] args) {
        System.out.println("Ciao!");
        stampa();
    }

    private static void stampa() {
        //Messaggio mess = new MessaggioStatico("Betty");
        //Messaggio messaggio = new MessaggioFormaGeometrica(new Cerchio(5.0f));//Dato un Cerchio con raggio 5.0 l'area misura 78.53982 e il perimetro 31.415926
        //Messaggio messaggio = new MessaggioFormaGeometrica(new Cerchio(6.0f));//Dato un Cerchio con raggio 6.0 l'area misura 113.097336 e il perimetro 37.699112
        //Messaggio messaggio = new MessaggioFormaGeometrica(new Quadrato(5.0f));//Dato un Quadrato con lato 5.0 l'area misura 25.0 e il perimetro 20.0
        Messaggio messaggio = new MessaggioFormaGeometrica(new Rettangolo(5.0f,6.0f));
        //Messaggio messaggio = new MessaggioBaseConAutore("Leonardo") ;
        System.out.println(messaggio.messaggio());
        //System.out.println(mess.messaggio());
    }
}