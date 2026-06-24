package petproject.gameswap.exception;

public class InvaildTokenException extends BusinessLogicException{

    public InvaildTokenException(String message) {
        super("INVALID_TOKEN", message);
    }
}
