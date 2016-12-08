package tech.flatstone.appliedlogistics.api.exceptions;

public class InvalidItemStack extends RuntimeException {
    public InvalidItemStack(final String message) {
        super(message);
    }
}
