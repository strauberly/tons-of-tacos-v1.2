package com.adamstraub.tonsoftacos.tonsoftacos.entities;


import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column (name = "name")
    private String name;

    @Column (name = "phone_number")
    private String phone_number;

    @Column (name = "email")
    private String email;
}
