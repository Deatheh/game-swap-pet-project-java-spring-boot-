package petproject.gameswap.exception;

import lombok.Getter;

@Getter
public class BusinessLogicException extends RuntimeException  {

    private final String code;

    public BusinessLogicException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessLogicException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

}
