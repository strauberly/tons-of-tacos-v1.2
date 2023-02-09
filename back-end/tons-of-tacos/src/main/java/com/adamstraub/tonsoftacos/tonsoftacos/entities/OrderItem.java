package com.adamstraub.tonsoftacos.tonsoftacos.entities;

import javax.persistence.*;

import lombok.*;

import java.math.BigDecimal;

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
    @Column (name = "id")
    private Integer id;

    @Column (name = "menu_item_id")
    private Integer menuItemId;

    @Column (name = "menu_item_name")
    private String menuItemName;

    @Column (name = "order_item_uuid")
    private String orderUuid;

    @Column (name = "quantity")
    private Integer quantity;

    @Column (name = "total")
    private BigDecimal total;
    }