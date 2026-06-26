package com.ERPs.MeuERP.service.exceptions;

public class DatabaseException extends RuntimeException {

    // Gera uma exceção com essa mensagem que eu estarei enviando no parâmetro
    public DatabaseException(String message) {
        super(message);
    }
}
