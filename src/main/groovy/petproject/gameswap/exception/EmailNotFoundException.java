package petproject.gameswap.exception;

public class EmailNotFoundException extends BusinessLogicException{
    public EmailNotFoundException(String message) {
        super("EMAIL_NOT_FOUND", message);
    }
}
