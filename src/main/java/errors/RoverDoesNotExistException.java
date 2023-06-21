package errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Rover Not Found")
public class RoverDoesNotExistException extends RuntimeException {
    public RoverDoesNotExistException(String errorMessage) {
        super(errorMessage);
    }
}
