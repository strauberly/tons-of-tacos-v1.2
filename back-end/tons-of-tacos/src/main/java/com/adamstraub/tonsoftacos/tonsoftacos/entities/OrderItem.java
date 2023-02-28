package com.adamstraub.tonsoftacos.tonsoftacos.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Builder
@Table(name = "order_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_pk")
    @JsonIgnore
    private Integer orderItemId;


    @ManyToOne()
    @JoinColumn(name = "item_fk")
//    @JsonIgnore
    private MenuItem itemId;


    @Column(name = "order_uuid")
    @JsonIgnore
    private String orderUuid;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "total")
    private Double total;

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemId=" + orderItemId +
                ", id=" + itemId +
                ", orderUuid='" + orderUuid + '\'' +
                ", quantity=" + quantity +
                ", total=" + total +
                '}';
    }
}