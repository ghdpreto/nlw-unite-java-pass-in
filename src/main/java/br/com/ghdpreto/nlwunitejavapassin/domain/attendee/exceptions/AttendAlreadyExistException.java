package br.com.ghdpreto.nlwunitejavapassin.domain.attendee.exceptions;

public class AttendAlreadyExistException extends RuntimeException{

    public AttendAlreadyExistException(String message) {
        super(message);
    }
}
