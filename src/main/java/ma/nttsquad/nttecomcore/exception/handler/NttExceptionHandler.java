package ma.nttsquad.nttecomcore.exception.handler;


import ma.nttsquad.nttecomcore.exception.NttBadRequestException;
import ma.nttsquad.nttecomcore.exception.NttNotFoundException;
import ma.nttsquad.nttecomcore.exception.records.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class NttExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NttNotFoundException.class})
    public ResponseEntity<ErrorResponse> NttNotFoundHandler(NttNotFoundException e) {
        HttpStatus noContent = HttpStatus.NOT_FOUND;
        ErrorResponse notFoundException = new ErrorResponse(e.getMessage(), noContent, LocalDateTime.now());

        return new ResponseEntity<>(notFoundException, noContent);

    }

    @ExceptionHandler(value = {NttBadRequestException.class})
    public ResponseEntity<ErrorResponse> NttBadRequestException(NttBadRequestException e) {
        HttpStatus noContent = HttpStatus.BAD_REQUEST;
        ErrorResponse badRequestException = new ErrorResponse(e.getMessage(), noContent, LocalDateTime.now());

        return new ResponseEntity<>(badRequestException, noContent);

    }

}
