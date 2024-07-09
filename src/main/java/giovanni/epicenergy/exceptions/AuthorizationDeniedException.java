package giovanni.epicenergy.exceptions;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class AuthorizationDeniedException extends RuntimeException{
    public AuthorizationDeniedException(String message) {
        super(message);
    }
}
