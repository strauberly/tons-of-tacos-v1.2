package com.adamstraub.tonsoftacos.tonsoftacos.entities;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "order_item")
@Getter
@Setter

public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;

    @Column (name = "menu_item_id")
    private Integer menu_item_id;

    @Column (name = "menu_item_name")
    private String menu_item_name;

    @Column (name = "order_uuid")
    private String order_uuid;

    @Column (name = "quantity")
    private Integer quantity;

    @Column (name = "total")
    private BigDecimal total;
    }
