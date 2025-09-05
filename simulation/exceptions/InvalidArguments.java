package simulation.exceptions;

import java.lang.RuntimeException;

public class InvalidArguments extends RuntimeException {
    public InvalidArguments(String errorMessage) {
        super("Invalid arguments: " + errorMessage);
    }
}
