package com.adamstraub.tonsoftacos.tonsoftacos.entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;


import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "order_pk")
    private Integer orderId;

    @Column(name = "customer_fk")
    private Integer customerId;

    @Column (name = "created")
    @CreationTimestamp
    private Timestamp created;

    @Column (name = "order_total")
    private Double orderTotal;

    @Column (name = "order_uuid")
    private String orderUuid;
//order id is a reference to the reference in the other class that connects to desired column
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderId")
    private Set<OrderItem> orderItems = new HashSet<>();

    @ManyToOne
    private Customer customer_fk;

}
