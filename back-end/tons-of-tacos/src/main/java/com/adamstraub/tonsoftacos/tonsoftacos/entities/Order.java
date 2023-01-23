package com.adamstraub.tonsoftacos.tonsoftacos.entities;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.JSONArray;

import java.sql.Timestamp;

@Entity
@Table
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column (name = "UUID")
    private String uuid;

    @Column (name = "customer_id")
    private Long customer_id;

    @Column (name = "order_data")
    private JSONArray order_data;

    @Column (name = "order_total")
    private Long order_total;

    @Column (name = "status")
    private String status;

    @Column (name = "created_on")
    private Timestamp created_on;


}
