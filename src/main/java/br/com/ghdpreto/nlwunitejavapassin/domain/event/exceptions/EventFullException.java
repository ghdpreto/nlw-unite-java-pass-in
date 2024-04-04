package br.com.ghdpreto.nlwunitejavapassin.domain.event.exceptions;

public class EventFullException extends RuntimeException {
    public EventFullException(String message) {
        super(message);
    }
}
