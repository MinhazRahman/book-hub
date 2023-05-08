package com.bookhub.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "order_tracking_number")
    private String orderTrackingNumber;

    @Column(name = "total_quantity")
    private int totalQuantity;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "status")
    private String status;

    @Column(name = "date_created")
    @CreationTimestamp
    private Date dateCreated;

    @Column(name = "last_updated")
    @UpdateTimestamp
    private Date lastUpdated;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    //@JsonManagedReference
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping_address_id", referencedColumnName = "id")
   // @JsonBackReference
   private Address shippingAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "billing_address_id", referencedColumnName = "id")
   // @JsonBackReference
   private Address billingAddress;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
   // @JsonBackReference
    private List<OrderItem> orderItems = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return getTotalQuantity() == order.getTotalQuantity() && Objects.equals(getId(), order.getId()) && Objects.equals(getOrderTrackingNumber(), order.getOrderTrackingNumber()) && Objects.equals(getTotalPrice(), order.getTotalPrice()) && Objects.equals(getStatus(), order.getStatus()) && Objects.equals(getDateCreated(), order.getDateCreated()) && Objects.equals(getLastUpdated(), order.getLastUpdated()) && Objects.equals(getCustomer(), order.getCustomer()) && Objects.equals(getShippingAddress(), order.getShippingAddress()) && Objects.equals(getBillingAddress(), order.getBillingAddress()) && Objects.equals(getOrderItems(), order.getOrderItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOrderTrackingNumber(), getTotalQuantity(), getTotalPrice(), getStatus(), getDateCreated(), getLastUpdated(), getCustomer(), getShippingAddress(), getBillingAddress(), getOrderItems());
    }

    public void add(OrderItem orderItem){
        if (orderItem != null){
            if(orderItems == null){
                orderItems = new ArrayList<>();
            }
            orderItems.add(orderItem);
            orderItem.setOrder(this);
        }
    }


}
