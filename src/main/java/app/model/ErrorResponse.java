package app.model;

import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ErrorResponse {
    private LocalDateTime timestamp;
    private HttpStatus status;
    private Integer code;
    private String message;

    public ErrorResponse(LocalDateTime timestamp, HttpStatus status, Integer code, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
