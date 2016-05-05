package tech.flatstone.appliedlogistics.api.exceptions;

public class IncompatibleApiVersionException extends RuntimeException {
    public IncompatibleApiVersionException(final String message) {
        super(message);
    }
}
