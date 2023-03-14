package com.adamstraub.tonsoftacos.tonsoftacos.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Builder
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "order_pk")
    private Integer orderId;

    @Column(name = "customer_fk")
    private Integer customerId;

    @Column (name = "order_total")
    private Double orderTotal;
    @Column (name = "order_uid")
    private String orderUid;
    @Column (name = "created")
    @CreationTimestamp
    private Timestamp created;

    @Column (name = "ready")
    private String ready = "no";

    @Column (name = "status")
    private String status = "open";


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.LAZY )
    @JsonManagedReference
    private List<OrderItem> orderItems = new ArrayList<>();


    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", customerId=" + customerId +
                ", orderTotal=" + orderTotal +
                ", orderUid='" + orderUid + '\'' +
                ", created=" + created +
                ", ready='" + ready + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
