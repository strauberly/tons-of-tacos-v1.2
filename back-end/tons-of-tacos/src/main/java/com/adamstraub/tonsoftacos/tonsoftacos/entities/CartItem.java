package com.adamstraub.tonsoftacos.tonsoftacos.entities;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Builder
@Table(name = "order_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_pk")
    private Integer orderItemId;

    @ManyToOne()
    @JoinColumn(name = "item_fk")
    private MenuItem itemId;


    @Column(name = "cart_uuid")
    private String cartUuid;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "total")
    private Double total;

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemId=" + orderItemId +
                ", id=" + itemId +
                ", cartUuid='" + cartUuid + '\'' +
                ", quantity=" + quantity +
                ", total=" + total +
                '}';
    }
}