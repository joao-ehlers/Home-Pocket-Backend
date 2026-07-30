package com.homepocket.exception;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(Long id) {
        super("Item with the id: " + id + " was not found");
    }
}
