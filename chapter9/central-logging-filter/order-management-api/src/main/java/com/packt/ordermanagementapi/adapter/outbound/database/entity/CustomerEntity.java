package com.packt.ordermanagementapi.adapter.outbound.database.entity;

import com.packt.ordermanagementapi.domain.Customer;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class CustomerEntity extends Customer {

    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    @Column(name = "STREET_ADDRESS")
    @Override
    public String getStreetAddress() {
        return super.getStreetAddress();
    }

    @Column(name = "CITY")
    @Override
    public String getCity() {
        return super.getCity();
    }

    @Column(name = "POSTAL_CODE")
    @Override
    public String getPostalCode() {
        return super.getPostalCode();
    }

    public void setId(Long id) {
        this.id = id;
    }
}
