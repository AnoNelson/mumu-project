package gov.rw.brd.exceptions;

/**
 * Created by Nick on 11/18/2019.
 */
public class ExceptionObject {
    private String message;

    public ExceptionObject(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
