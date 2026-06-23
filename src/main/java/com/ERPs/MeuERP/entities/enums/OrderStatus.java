package com.ERPs.MeuERP.entities.enums;

public enum OrderStatus {

    WAITING_PAYMENT(1),
    PAID(2),
    SHIPPED(3),
    DELIVERED(4),
    CANCELED(5);

    private int code; // Código do tipo enumerado

    private OrderStatus(int code) { // Construtor do tipo enumerado
        this.code = code;
    }

    // Precisamos de um metodo para acessar o código do tipo enumero
    public int getCode() {
        return code;
    }

    // Metodo para transformar um valor numérico em tipo enumerado
    public static OrderStatus valueOf(int code) {
        for (OrderStatus value : OrderStatus.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus code");
    }
}
