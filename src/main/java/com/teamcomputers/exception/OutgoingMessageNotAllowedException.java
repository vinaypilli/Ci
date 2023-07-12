package com.teamcomputers.exception;

public class OutgoingMessageNotAllowedException extends RuntimeException {
    public OutgoingMessageNotAllowedException(String message) {
        super(message);
    }
}

