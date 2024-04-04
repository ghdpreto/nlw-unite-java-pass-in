package br.com.ghdpreto.nlwunitejavapassin.config;


import br.com.ghdpreto.nlwunitejavapassin.domain.event.exceptions.EventNotFoundException;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// classe para captura de erros domain/controller
@ControllerAdvice
public class ExceptionEntityHandler {


    // esse trata excecoe do tipo EventNotFoundException
    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity handleEventNotFound(EventNotFoundException event) {
        return ResponseEntity.notFound().build();
    }
}
