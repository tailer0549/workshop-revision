package com.ERPs.MeuERP.entities;

import com.ERPs.MeuERP.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_order") // Nome da tabela no banco de dados
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;
    private Integer orderStatus;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items = new HashSet<>(); // A coleção Set ignora elementos repetidos

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL) // cascade = Propagação de operação, se deleta pedido, deleta pagamento
    @JoinColumn(name = "payment_id") // Chave estrangeira da tabela payment
    private Payment payment;

    public Order() {
    }

    public Order(Long id, Instant moment, User user, OrderStatus orderStatus) {
        this.id = id;
        this.moment = moment;
        this.client = user;
        setOrderStatus(orderStatus); // PQ setOrderStatus ? Porque precisamos transformar esse OrderStatus em um valor Integer, por isso usamos o setOrderStatus
    }

    // Metodo total
    public Double getTotal() {
        double sum = 0;
        for(OrderItem x : items) {
            sum+= x.getSubTotal();
        }
        return sum;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(orderStatus); // O valueOf converte o tipo enumerado em valor numérico
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        if (orderStatus != null) {
        this.orderStatus = orderStatus.getCode(); // .getCode() -> Retorna o valor numérico do tipo enumerado
        }
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
