package com.Home_pocket.exception;

public class ItemNaoEncontradoException extends RuntimeException {
    public ItemNaoEncontradoException(Long id) {
        super("Item da lista nao encontrado para o ID: " + id);
    }
}
