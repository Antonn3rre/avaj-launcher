package simulation.exceptions;

import java.lang.RuntimeException;

public class InvalidArguments extends RuntimeException {
    public InvalidArguments(String errorMessage) {
        super("Invalid arguments: " + errorMessage);
    }
	public InvalidArguments() {
		super("Invalid arguments: expected line is \"[string: flyableType] [string: id] [int: longitude] [int: latitude] [int: height]\"");
	}
}
