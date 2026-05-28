package it.intesys.codylab.formageometrica;

public class Rettangolo extends Quadrilatero {
    private float base;
    private float altezza;

    public Rettangolo(float base, float altezza) {
        super(base, altezza, base, altezza); // chiama il costruttore di Quadrilatero con i 4 lati
        this.base = base;
        this.altezza = altezza;
    }

    @Override
    public float area() {
        return base * altezza;
    }
}