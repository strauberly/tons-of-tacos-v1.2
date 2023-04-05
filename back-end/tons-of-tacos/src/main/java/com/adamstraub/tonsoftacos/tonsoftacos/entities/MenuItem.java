package com.adamstraub.tonsoftacos.tonsoftacos.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
//    @JsonIgnore
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
    @JsonIgnore
    private String imgUrl;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Override
    public String toString() {
        return "MenuItem{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemSize='" + itemSize + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
