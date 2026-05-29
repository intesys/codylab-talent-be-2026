package it.intesys.codylab.messaggio;

import it.intesys.codylab.formageometrica.FormaGeometrica;

public class MessaggioFormaGeometrica implements Messaggio {

    private final FormaGeometrica formaGeometrica;

    public MessaggioFormaGeometrica(FormaGeometrica formaGeometrica) {
        this.formaGeometrica = formaGeometrica;
    }

    @Override
    public String messaggio() {
        return "Dato un %s l'area misura %s e il perimetro %s".formatted(
                formaGeometrica.toString(), formaGeometrica.area(), formaGeometrica.perimetro()
        );
    }
}
