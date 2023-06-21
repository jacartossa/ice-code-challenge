package errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.EXPECTATION_FAILED, reason = "Rover Placed Out of Bounds")
public class RoverOutOfBoundsException extends RuntimeException {
    public RoverOutOfBoundsException(String errorMessage) {
        super(errorMessage);
    }
}
