package it.intesys.codylab.formageometrica;

public class Quadrato extends Quadrilatero {

    private final float lato;

    public Quadrato(float lato) {
        super(lato, lato, lato, lato);
        this.lato = lato;
    }

    @Override
    public float area() {
        return lato * lato;
    }

    @Override
    public String toString() {
        return "Quadrato con lato " + lato;
    }
}
