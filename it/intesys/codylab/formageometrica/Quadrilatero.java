package it.intesys.codylab.formageometrica;

public abstract class Quadrilatero implements FormaGeometrica {

    private final float lato1;
    private final float lato2;
    private final float lato3;
    private final float lato4;

    protected Quadrilatero(float lato1, float lato2, float lato3, float lato4) {
        this.lato1 = lato1;
        this.lato2 = lato2;
        this.lato3 = lato3;
        this.lato4 = lato4;
    }

    @Override
    public float perimetro() {
        return lato1 + lato2 + lato3 + lato4;
    }

    @Override
    public String toString() {
        return "Quadrilatero con lati " + lato1 + ", " + lato2 + ", " + lato3 + ", " + lato4;
    }
}
