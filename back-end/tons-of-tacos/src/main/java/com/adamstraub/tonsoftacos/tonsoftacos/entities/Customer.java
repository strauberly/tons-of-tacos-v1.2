package com.adamstraub.tonsoftacos.tonsoftacos.entities;


import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name ="customer")
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "customer_pk")
    private Integer customerId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;
    @OneToMany(mappedBy = "customerId", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
//    @JoinColumn(name="customer_fk")
    private List<Orders> orders = new ArrayList<>();
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer_fk")
//    private List<Orders> orders = new ArrayList<>();

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }


//
//
}
