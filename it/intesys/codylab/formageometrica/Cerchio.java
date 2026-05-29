package it.intesys.codylab.formageometrica;

public class Cerchio implements FormaGeometrica{

    private final float raggio;

    public Cerchio(float raggio) {
        this.raggio = raggio;
    }

    @Override
    public float perimetro() {
        return Double.valueOf(Math.PI * 2 * raggio).floatValue();
    }

    @Override
    public float area() {
        return Double.valueOf(Math.PI * raggio * raggio).floatValue();
    }

    @Override
    public String toString() {
        return "Cerchio con raggio " + raggio;
    }
}
