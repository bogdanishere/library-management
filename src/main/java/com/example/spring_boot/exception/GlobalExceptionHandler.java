package com.example.spring_boot.exception;


import com.example.spring_boot.dto.MessageResponseDTO;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Primary
@ControllerAdvice(name = "GlobalExceptionHandler")
public class GlobalExceptionHandler {
    private static final String DEFAULT_ERROR_MESSAGE = "Invalid data or format.";

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<MessageResponseDTO> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String rootMessage = ex.getRootCause() != null ? ex.getRootCause().getMessage() : ex.getMessage();

        if (rootMessage != null && rootMessage.contains("Duplicate entry")) {
            MessageResponseDTO errorResponse = new MessageResponseDTO("Please choose a different name.");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        MessageResponseDTO errorResponse = new MessageResponseDTO(DEFAULT_ERROR_MESSAGE);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MessageResponseDTO> handleIllegalArgumentException(IllegalArgumentException ex) {
        MessageResponseDTO errorResponse = new MessageResponseDTO(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errorMessage = "Invalid data or format.";
        if (!e.getBindingResult().getFieldErrors().isEmpty()) {
            errorMessage = e.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        }
        MessageResponseDTO errorResponse = new MessageResponseDTO(errorMessage);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
