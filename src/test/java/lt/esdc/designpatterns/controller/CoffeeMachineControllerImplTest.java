package lt.esdc.designpatterns.controller;

import lt.esdc.designpatterns.factory.CoffeeFactory;
import lt.esdc.designpatterns.machine.CoffeeMachineClient;
import lt.esdc.designpatterns.product.Coffee;
import lt.esdc.designpatterns.product.CoffeeDrink;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class CoffeeMachineControllerImplTest {

    private CoffeeMachineClient machine;
    private CoffeeFactory factory;
    private CoffeeMachineControllerImpl controller;

    @BeforeEach
    void setUp() {
        machine = mock(CoffeeMachineClient.class);
        factory = mock(CoffeeFactory.class);
        controller = new CoffeeMachineControllerImpl(machine, factory);
    }

    @Test
    void processOrder_validOrder_shouldSendToMachine() {
        CoffeeDrink coffee = new Coffee.Builder()
                .water(250)
                .coffee(15)
                .milk(200)
                .price(4.0)
                .build();

        when(factory.create("latte")).thenReturn(coffee);

        controller.processOrder(new String[]{
                "latte cream caramel"
        });

        verify(machine).prepare(argThat(command ->
                command.contains("250ml 15g 200ml") &&
                        command.contains("cream") &&
                        command.contains("caramel")
        ));
    }

    @Test
    void processOrder_unknownCoffee_shouldNotCallMachine() {
        when(factory.create("tea"))
                .thenThrow(new IllegalArgumentException());

        controller.processOrder(new String[]{
                "tea"
        });

        verify(machine, never()).prepare(any());
    }

    @Test
    void processOrder_multipleOrders_shouldProcessEachIndependently() {
        CoffeeDrink espresso = new Coffee.Builder()
                .water(60)
                .coffee(16)
                .milk(0)
                .price(2.0)
                .build();

        when(factory.create("espresso")).thenReturn(espresso);

        controller.processOrder(new String[]{
                "espresso",
                "espresso"
        });

        verify(machine, times(2))
                .prepare(espresso.prepareCommand());
    }

    @Test
    void processOrder_invalidFormat_shouldBeIgnored() {
        controller.processOrder(new String[]{
                ""
        });

        verify(machine, never()).prepare(any());
    }
}