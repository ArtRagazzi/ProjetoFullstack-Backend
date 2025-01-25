package com.artragazzi.projetofullstack.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Getter
@Setter

public class ExceptionResponseBody {

    private HttpStatus status;
    private String message;
    private String date;


    public ExceptionResponseBody(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        this.date = formatDate(LocalDateTime.now());
    }

    public String formatDate(LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return dateTime.format(formatter);

    }
}
