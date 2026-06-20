package petproject.gameswap.exception;

public class UsernameAlreadyExistsException extends BusinessLogicException{
    public UsernameAlreadyExistsException(String message) {
        super("EMAIL_ALREADY_EXISTS", message);
    }
}
