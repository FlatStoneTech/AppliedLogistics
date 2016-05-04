package tech.flatstone.appliedlogistics.api.exceptions;

public class ApiVersionMismatch extends RuntimeException{
    public ApiVersionMismatch(final String message) { super(message);}
}
