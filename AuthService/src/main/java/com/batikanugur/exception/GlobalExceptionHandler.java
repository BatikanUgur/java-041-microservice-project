package com.batikanugur.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

// tom controller sınıfları için hata yönetimini devralan anotasyon
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handlerRuntimeException(RuntimeException exception){
        return  ResponseEntity.badRequest().body("Beklenmeyen hata " + exception.getMessage());
    }

    @ExceptionHandler(AuthServiceException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleAuthException(AuthServiceException exception){
        ErrorType errorType = exception.getType();
        HttpStatus httpStatus = errorType.getHttpStatus();

        return new ResponseEntity<ErrorMessage>(createErrorMessage(errorType, exception), httpStatus);
    }

    private ErrorMessage createErrorMessage(ErrorType errorType, AuthServiceException exception) {
        return ErrorMessage.builder()
                .code(errorType.getCode())
                .message(errorType.getMessage())
                .build();
    }

}
