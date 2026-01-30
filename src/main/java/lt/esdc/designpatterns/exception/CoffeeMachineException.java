package lt.esdc.designpatterns.exception;

public class CoffeeMachineException extends RuntimeException {
    public CoffeeMachineException(String message, Throwable cause) {
        super(message, cause);
    }
}