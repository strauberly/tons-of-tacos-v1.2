package com.adamstraub.tonsoftacos.tonsoftacos.entities;

import javax.persistence.*;

import lombok.*;


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
    @Column(name = "id")
    private Integer id;

    @Column (name = "category")
    private String category;

    @Column(name = "description")
    private String description;

    @Column(name = "item_name")
    private String item_name;

    @Column (name = "item_size")
    private String item_size;
    @Column(name = "img_url")
    private String img_url;

    @Column(name = "unit_price")
    private Double unit_price;

}
