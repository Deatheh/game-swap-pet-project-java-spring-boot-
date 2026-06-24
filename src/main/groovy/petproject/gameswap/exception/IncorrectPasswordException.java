package petproject.gameswap.exception;

public class IncorrectPasswordException extends BusinessLogicException{
    public IncorrectPasswordException(String message) {
        super("INCORRECT_PASSWORD", message);
    }
}
