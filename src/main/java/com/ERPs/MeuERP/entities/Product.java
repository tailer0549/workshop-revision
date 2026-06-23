package com.ERPs.MeuERP.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_product") // Nome da tabela no banco de dados
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;


    @ManyToMany
    @JoinTable(name = "tb_product_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    Set<Category> categories = new HashSet<>(); // Pq usar a coleção SET ? Porque categories não pode ter dois produtos iguais, o set ignora elementos repetidos

    @OneToMany(mappedBy = "id.product") //
    private Set<OrderItem> items = new HashSet<>(); // Pq Set ? Pq eu não quero produtos repetidos em um OrderItem

    public Product() {
    }

    public Product(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    // Eu estou retornando um SetOrder pq é mais interessante retornar os Pedidos em que os produtos estão relacionados em vez dos PedidosItems
    @JsonIgnore // Evita recursão infinita, Order chama OrderItem que chama Product que chama Order que chama orderItem que chama Product etc..
    // Pq estou colocando o JsonIgnore aqui ? Pq na verdade eu quero acessar os Orders e ver os produtos vindo juntos e não o contrario
    public Set<Order> getOrders() {
       Set<Order> set = new HashSet<>();
       for (OrderItem x : items) {
           set.add(x.getOrder());
       }
       return set;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product stock = (Product) o;
        return Objects.equals(getId(), stock.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
