package com.adamstraub.tonsoftacos.tonsoftacos.entities;

import jakarta.persistence.*;
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
    private Long id;

    @Column (name = "menu_item_id")
    private Long menu_item_id;

    @Column (name = "order_id")
    private Long order_id;

    @Column (name = "quantity")
    private Long quantity;

    @Column (name = "total")
    private BigDecimal total;
    }
