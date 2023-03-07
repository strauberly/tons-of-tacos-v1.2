package com.adamstraub.tonsoftacos.tonsoftacos.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Timestamp;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "order_pk")
    private Integer orderId;

    @Column(name = "customer_fk")
    private Integer customerId;
//has default value
    @Column (name = "created")
    @CreationTimestamp
    private Timestamp created;

    @Column (name = "order_total")
    private Double orderTotal;

    @Column (name = "order_uuid")
    private String orderUuid;
//order id is a reference to the reference in the other class that connects to desired column

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderId")
//    private Set<CartItem> orderItems = new HashSet<>();

//    @ManyToOne
//    private Customer customer_fk;
//

    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", customerId=" + customerId +
                ", created=" + created +
                ", orderTotal=" + orderTotal +
                ", orderUuid='" + orderUuid + '\'' +
                '}';
    }
}
