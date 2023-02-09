package com.adamstraub.tonsoftacos.tonsoftacos.entities;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.JSONArray;

import java.sql.Timestamp;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;

    @Column (name = "created")
    private Timestamp created;

    @Column (name = "customer_id")
    private Integer customer_id;

    @Column (name = "order_data")
    private JSONArray order_data;

    @Column (name = "order_total")
    private Integer order_total;

    @Column (name = "status")
    private String status;

    @Column (name = "orders_id")
    private String orderId;
}
