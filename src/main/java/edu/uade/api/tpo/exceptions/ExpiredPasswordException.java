package edu.uade.api.tpo.exceptions;

public class ExpiredPasswordException extends Exception {

    public ExpiredPasswordException(String message) {
        super(message);
    }

}
