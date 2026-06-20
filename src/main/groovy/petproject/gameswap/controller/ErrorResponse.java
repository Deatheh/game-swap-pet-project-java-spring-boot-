package petproject.gameswap.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String code;
    private String message;
    private List<String> details;
    private LocalDateTime timestamp;

    public ErrorResponse(String code, String message) {
        this(code, message, null, LocalDateTime.now());
    }

    public ErrorResponse(String code, String message, List<String> details) {
        this(code, message, details, LocalDateTime.now());
    }

}
