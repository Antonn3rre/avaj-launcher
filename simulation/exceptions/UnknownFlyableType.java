package simulation.exceptions;

import java.lang.RuntimeException;

public class UnknownFlyableType extends RuntimeException {
    public UnknownFlyableType(String errorMessage) {
        super("Unknown Flyable Type: " + errorMessage);
    }
}
