package br.com.handrei.exception;

public class PasswordInvalidException extends RuntimeException {
    public PasswordInvalidException() {
        super("Password incorrect!");
    }
}
