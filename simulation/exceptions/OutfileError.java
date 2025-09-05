package simulation.exceptions;

import java.lang.RuntimeException;

public class OutfileError extends RuntimeException {
    public OutfileError(String errorMessage) {
        super("Outfile Error: " + errorMessage);
    }
}
