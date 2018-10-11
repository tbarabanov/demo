package hello.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GreetingNotFoundException extends RuntimeException {

    public GreetingNotFoundException() {
    }

    public GreetingNotFoundException(String message) {
        super(message);
    }

    public GreetingNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public GreetingNotFoundException(Throwable cause) {
        super(cause);
    }

    public GreetingNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
