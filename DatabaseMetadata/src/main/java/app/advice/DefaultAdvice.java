package app.advice;

import app.exception.DataNotFoundException;
import app.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class DefaultAdvice {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDataNotFoundException(DataNotFoundException ex) {
        return new ResponseEntity<>(
                new ErrorResponse(
                        LocalDateTime.now(),
                        HttpStatus.NOT_FOUND,
                        404,
                        ex.getMessage()
                ),
                HttpStatus.NOT_FOUND
        );
    }
}
