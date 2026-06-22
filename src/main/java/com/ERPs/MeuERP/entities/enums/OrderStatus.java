package com.ERPs.MeuERP.entities.enums;

public enum OrderStatus {

    WAITING_PAYMENT(1),
    SHIPPED(2),
    CANCELED(3),
    DELIVERED(4);

    private Integer code; // código do tipo enumerado

    private OrderStatus(Integer code) {
        this.code = code;
    }

    public Integer getCode() { // metodo para acessar o código do tipo enumerado
        return code;
    }

    // Metodo para transformar um valor numérico em tipo enumerado
    public static OrderStatus valueOf(int code) {
        for (OrderStatus value : OrderStatus.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid error code");
    }
}

