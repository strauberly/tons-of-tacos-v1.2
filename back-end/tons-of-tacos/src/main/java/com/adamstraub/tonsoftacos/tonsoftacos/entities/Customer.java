package com.adamstraub.tonsoftacos.tonsoftacos.entities;


import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name ="customer")
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;
    @Column(name = "phone_number")
    private String phone_number;
}
