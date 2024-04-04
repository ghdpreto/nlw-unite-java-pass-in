package br.com.ghdpreto.nlwunitejavapassin.domain.attendee.exceptions;

public class AttendeeNotFoundException extends RuntimeException{
    public AttendeeNotFoundException(String message) {
        super(message);
    }
}
