package com.batikanugur.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorType {

    ABC_BULUNAMADI  (1003, "Aradığınız abc sistemde kayıtlı değil", HttpStatus.NOT_FOUND),
    ABC_EKLEME_HATASI (2001, "Ekleme başarısız oldu", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_PARAMETER (3001, "Geçersiz parametre", HttpStatus.BAD_REQUEST),

    REGISTER_PARAMETER_MISMATCH (4001, "Girilen parola uyuşmadı", HttpStatus.BAD_REQUEST),
    REGISTER_USERNAME_EXISTS (5001, "Bu kullanıcı adı sistemde mevcut", HttpStatus.BAD_REQUEST),
    DOLOGIN_USERNAME_OR_PASSWORD (6001, "Kullanıcı adı veya parola hatalı", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN (7001, "Geçersiz token", HttpStatus.BAD_REQUEST),
    BAD_REQUEST (8001, "Geçersiz istek", HttpStatus.BAD_REQUEST),

    INTERNAL_SERVER_ERROR(9001,"SUnucu hatası", HttpStatus.INTERNAL_SERVER_ERROR);


    private int code;
    private String message;
    private HttpStatus httpStatus;

}
