package it.intesys.codylab.formageometrica;

public class Rettangolo extends Quadrilatero {
    private float base;
    private float altezza;

    public Rettangolo(float base, float altezza) {
        super(base, altezza, base, altezza);
        this.base = base;
        this.altezza = altezza;
    }
    public float area() {
        return base * altezza;
    }
    public String toString() {return "Il rettangolo di base " + base + " e altezza " + altezza;}
}
