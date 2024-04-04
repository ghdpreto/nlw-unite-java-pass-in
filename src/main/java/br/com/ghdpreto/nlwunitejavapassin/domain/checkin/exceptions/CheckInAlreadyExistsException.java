package br.com.ghdpreto.nlwunitejavapassin.domain.checkin.exceptions;

public class CheckInAlreadyExistsException extends RuntimeException{
    public CheckInAlreadyExistsException(String message) {
        super(message);
    }
}
