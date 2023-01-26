package com.adamstraub.tonsoftacos.tonsoftacos.entities;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Entity
@Table(name = "menu_item")
@Getter
@Setter
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column (name = "category_type")
    private String category_type;

    @Column(name = "description")
    private String description;

    @Column(name = "img_url")
    private String img_url;

    @Column(name = "item_name")
    private String item_name;

    @Column (name = "item_size")
    private String item_size;

    @Column(name = "unit_price")
    private Double unit_price;

    public MenuItem(String category_type, String description, String img_url, String item_name, String item_size, Double unit_price) {
        this.category_type = category_type;
        this.description = description;
        this.img_url = img_url;
        this.item_name = item_name;
        this.item_size = item_size;
        this.unit_price = unit_price;
    }
}
