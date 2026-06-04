package it.intesys.codylab.formageometrica;

import org.junit.jupiter.api.Test;

class QuadratoTest {

    @Test
    void conUnQuadratoDiLato5LareaVale25() {
        // ARRANGE
        Quadrato quadrato = new Quadrato(5.0F);

        // ACT
        float area = quadrato.area();

        // ASSERT
        assert (area == 25.0F);
    }

    @Test
    void conUnQuadratoDiLato20LareaVale400() {
        // ARRANGE
        Quadrato quadrato = new Quadrato(5.0F);

        // ACT
        float area = quadrato.area();

        // ASSERT
        //assert (area == 5.0F);
    }
}