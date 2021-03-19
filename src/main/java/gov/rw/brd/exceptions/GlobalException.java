package gov.rw.brd.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Nick on 11/18/2019.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GlobalException extends RuntimeException{
    public GlobalException(String message) {
        super(message);
    }
}
