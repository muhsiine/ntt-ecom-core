package ma.nttsquad.nttecomcore.exception.handler;


import ma.nttsquad.nttecomcore.exception.NttNotFoundException;
import ma.nttsquad.nttecomcore.exception.records.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class NttExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NttNotFoundException.class})
    public ResponseEntity<NotFoundException> NttNotFoundHandler(NttNotFoundException e) {
        HttpStatus noContent = HttpStatus.NOT_FOUND;
        NotFoundException notFoundException = new NotFoundException(e.getMessage(), noContent, LocalDateTime.now());

        return new ResponseEntity<>(notFoundException, noContent);

    }
}
