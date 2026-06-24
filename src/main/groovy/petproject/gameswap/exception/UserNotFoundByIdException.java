package petproject.gameswap.exception;

public class UserNotFoundByIdException extends BusinessLogicException{
    public UserNotFoundByIdException(String message) {
        super("USER_NOT_FOUND_BY_ID", message);
    }
}
