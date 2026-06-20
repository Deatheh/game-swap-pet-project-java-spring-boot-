package petproject.gameswap.exception;

public class EmailAlreadyExistsException extends BusinessLogicException{
    public EmailAlreadyExistsException(String message) {
        super("EMAIL_ALREADY_EXISTS", message);
    }
}
