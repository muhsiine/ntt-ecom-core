package ma.nttsquad.nttecomcore.exception.records;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record NotFoundException(String message, HttpStatus httpStatus, LocalDateTime timeStamp) {}
