package com.adamstraub.tonsoftacos.tonsoftacos.entities;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Builder
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    rename
    @Column(name = "order_item_pk")
    private Integer cartItemId;
//    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "item_fk")
    private MenuItem itemId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "total")
    private Double total;

    @ManyToOne()
    @JoinColumn(name = "order_fk")
    private Orders order;

    @JsonBackReference
    public Orders getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemId=" + cartItemId +
                ", id=" + itemId +
                ", order='" + order + '\'' +
                ", quantity=" + quantity +
                ", total=" + total +
                '}';
    }
}