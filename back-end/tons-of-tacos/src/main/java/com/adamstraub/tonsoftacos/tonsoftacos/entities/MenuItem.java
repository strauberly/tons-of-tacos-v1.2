package com.adamstraub.tonsoftacos.tonsoftacos.entities;

import javax.persistence.*;

import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Builder
@Table(name = "menu_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_pk")
    private Integer id;


    @Column (name = "category")
    private String category;

    @Column(name = "description")
    private String description;

    @Column(name = "item_name")
    private String itemName;

    @Column (name = "item_size")
    private String itemSize;
    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "unit_price")
    private Double unitPrice;


    //    @ManyToOne
//    OrderItem orderItems;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemId")
//    private Set<OrderItem> orderItems = new HashSet<>();
}
