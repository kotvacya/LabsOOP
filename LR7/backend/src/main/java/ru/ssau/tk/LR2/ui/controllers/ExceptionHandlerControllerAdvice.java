package ru.ssau.tk.LR2.ui.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.ssau.tk.LR2.ui.dto.UIExceptionDTO;
import ru.ssau.tk.LR2.ui.exceptions.BaseUIException;

@RestControllerAdvice
public class ExceptionHandlerControllerAdvice{
    @ExceptionHandler(BaseUIException.class)
    public ResponseEntity<UIExceptionDTO> uiException(BaseUIException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UIExceptionDTO(
            HttpStatus.BAD_REQUEST.value(), exception.getClass().getSimpleName(), exception.getMessage()
        ));
    }
}
