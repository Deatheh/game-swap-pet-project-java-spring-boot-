package petproject.gameswap.exception;

public class UnauthorizedException extends BusinessLogicException{
    public UnauthorizedException(String message){
        super("UNAUTHORIZED", message);
    }
}
