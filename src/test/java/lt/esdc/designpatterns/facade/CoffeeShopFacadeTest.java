package lt.esdc.designpatterns.facade;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoffeeShopFacadeTest {

    @Test
    void order_shouldWork_withItalyAndV15() {
        CoffeeShopFacade facade =
                new CoffeeShopFacade("italy", "v15");

        assertDoesNotThrow(() ->
                facade.processOrder("250ml 15g 200ml")
        );
    }

    @Test
    void order_shouldWork_withLithuaniaAndV75() {
        CoffeeShopFacade facade =
                new CoffeeShopFacade("lithuania", "v75");

        assertDoesNotThrow(() ->
                facade.processOrder("250ml 15g 200ml sugar milk")
        );
    }

    @Test
    void constructor_shouldThrowException_forUnknownRegion() {
        IllegalArgumentException ex =
                assertThrows(IllegalArgumentException.class, () ->
                        new CoffeeShopFacade("france", "v15")
                );

        assertTrue(ex.getMessage().contains("Unknown region"));
    }

    @Test
    void constructor_shouldThrowException_forUnknownVersion() {
        IllegalArgumentException ex =
                assertThrows(IllegalArgumentException.class, () ->
                        new CoffeeShopFacade("italy", "v99")
                );

        assertTrue(ex.getMessage().contains("Unknown machine version"));
    }
}