package petproject.gameswap.exception;

public class UsernameAlreadyExistsException extends BusinessLogicException{
    public UsernameAlreadyExistsException(String message) {
        super("USERNAME_ALREADY_EXISTS", message);
    }
}
