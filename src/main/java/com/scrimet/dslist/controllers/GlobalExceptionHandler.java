package com.scrimet.dslist.controllers;

import com.scrimet.dslist.dto.ErrorResponseDTO;
import com.scrimet.dslist.exceptions.GameNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GameNotFoundException.class)

    public ResponseEntity<ErrorResponseDTO> handleGameNotFound(

            GameNotFoundException ex,

            HttpServletRequest request) {

        ErrorResponseDTO error = new ErrorResponseDTO(

                ex.getMessage(),

                HttpStatus.NOT_FOUND.value(),

                System.currentTimeMillis(),

                request.getRequestURI()

        );

        return ResponseEntity

                .status(HttpStatus.NOT_FOUND)

                .body(error);

    }

// Captura qualquer outra exceção (500)

    @ExceptionHandler(Exception.class)

    public ResponseEntity<ErrorResponseDTO> handleGeneralException(

            Exception ex,

            HttpServletRequest request) {

        ErrorResponseDTO error = new ErrorResponseDTO(

                "Internal server error",

                HttpStatus.INTERNAL_SERVER_ERROR.value(),

                System.currentTimeMillis(),

                request.getRequestURI()

        );

        return ResponseEntity

                .status(HttpStatus.INTERNAL_SERVER_ERROR)

                .body(error);

    }

}

