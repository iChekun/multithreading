package by.trjva.chekun.model.state.exception;

public class WrongActionException extends Exception {

    public WrongActionException() {
        super();
    }

    public WrongActionException(String message) {
        super(message);
    }

    public WrongActionException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongActionException(Throwable cause) {
        super(cause);
    }
}
