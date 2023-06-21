package errors;

public class InvalidRotationException extends RuntimeException {
    public InvalidRotationException(String errorMessage) {
        super(errorMessage);
    }
}
