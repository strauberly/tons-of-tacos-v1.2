package com.adamstraub.tonsoftacos.tonsoftacos.entities;

import javax.persistence.*;

import lombok.*;

@Entity
@Builder
@Table(name = "order_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_pk")
    private Integer orderItemId;

    @Column(name = "item_fk")
    private Integer itemId;

    @Column(name = "order_uuid")
    private String orderUuid;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "total")
    private Double total;

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemId=" + orderItemId +
                ", itemId=" + itemId +
                ", orderUuid='" + orderUuid + '\'' +
                ", quantity=" + quantity +
                ", total=" + total +
                '}';
    }

    ////        @ManyToOne
////    MenuItem menuItem;
}