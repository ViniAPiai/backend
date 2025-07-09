package com.vini.piai.backend.security.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleInvalidArgument(MethodArgumentNotValidException exception) throws JsonProcessingException {
        Map<String, String> map =  new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(map);
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, json);
        return ResponseEntity.status(problemDetail.getStatus()).body(problemDetail);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UniqueException.class)
    public ResponseEntity<ProblemDetail> uniqueException(final UniqueException e) {
        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDetail);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ProblemDetail> notBelongException(final ForbiddenException e) {
        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorDetail);
    }


}
