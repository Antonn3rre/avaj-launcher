package simulation.exceptions;

import java.lang.RuntimeException;

public class InvalidCoordinates extends RuntimeException {
    public InvalidCoordinates(String errorMessage) {
        super("The coordinates in the scenario are invalid: " + errorMessage);
    }
}
