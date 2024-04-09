package br.com.ghdpreto.nlwunitejavapassin.config;


import br.com.ghdpreto.nlwunitejavapassin.domain.attendee.exceptions.AttendeeAlreadyExistException;
import br.com.ghdpreto.nlwunitejavapassin.domain.attendee.exceptions.AttendeeNotFoundException;
import br.com.ghdpreto.nlwunitejavapassin.domain.checkin.exceptions.CheckInAlreadyExistsException;
import br.com.ghdpreto.nlwunitejavapassin.domain.event.exceptions.EventFullException;
import br.com.ghdpreto.nlwunitejavapassin.domain.event.exceptions.EventNotFoundException;
import br.com.ghdpreto.nlwunitejavapassin.dto.general.ErrorResponseDTO;
import br.com.ghdpreto.nlwunitejavapassin.dto.general.ErrorValidationResponseDTO;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

// classe para captura de erros domain/controller
@ControllerAdvice
public class ExceptionEntityHandler {

    private MessageSource messageSource;

    public ExceptionEntityHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    // esse trata excecoe do tipo EventNotFoundException
    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity handleEventNotFound(EventNotFoundException event) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(AttendeeNotFoundException.class)
    public ResponseEntity handleAttendeeNotFound(AttendeeNotFoundException event) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(AttendeeAlreadyExistException.class)
    public ResponseEntity handleAttendeeAlreadyExist(AttendeeAlreadyExistException event) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @ExceptionHandler(CheckInAlreadyExistsException.class)
    public ResponseEntity handleCheckInAlreadyExist(CheckInAlreadyExistsException event) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }


    @ExceptionHandler(EventFullException.class)
    public ResponseEntity<ErrorResponseDTO> handleEventFull(EventFullException event) {
        return ResponseEntity.badRequest().body(new ErrorResponseDTO(event.getMessage()));
    }
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorResponseDTO> handleDataAccessException(DataAccessException exception) {
        return ResponseEntity.internalServerError().body(new ErrorResponseDTO("Database error"));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> handleRuntimeExceptionException(RuntimeException exception) {
        System.out.println(exception);
        return ResponseEntity.internalServerError().body(new ErrorResponseDTO("Internal server error"));
    }

    //### VALIDACAO DE CAMPOS ###
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorValidationResponseDTO>> handleMethodArgumentNotValidExceptoion(MethodArgumentNotValidException exception) {
        List<ErrorValidationResponseDTO> dto = new ArrayList<ErrorValidationResponseDTO>();

        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            dto.add(new ErrorValidationResponseDTO(fieldError.getField(), fieldError.getDefaultMessage()));
        }

        return ResponseEntity.badRequest().body(dto);
    }


}
