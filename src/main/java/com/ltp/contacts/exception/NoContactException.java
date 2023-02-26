package com.ltp.contacts.exception;

public class NoContactException extends RuntimeException{
    public NoContactException(String id) {
        super(id + " " + "does not exist in our records");
    }
}
