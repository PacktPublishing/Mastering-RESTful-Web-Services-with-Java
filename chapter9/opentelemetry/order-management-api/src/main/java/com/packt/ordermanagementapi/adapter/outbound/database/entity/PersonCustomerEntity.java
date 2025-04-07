package com.packt.ordermanagementapi.adapter.outbound.database.entity;

import com.packt.ordermanagementapi.domain.PersonCustomer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_PERSON_CUSTOMER")
public class PersonCustomerEntity extends CustomerEntity {

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static CustomerEntity fromCustomer(PersonCustomer personCustomer) {
        var personCustomerEntity = new PersonCustomerEntity();
        personCustomerEntity.setFirstName(personCustomer.getFirstName());
        personCustomerEntity.setLastName(personCustomer.getLastName());
        personCustomerEntity.setStreetAddress(personCustomer.getStreetAddress());
        personCustomerEntity.setCity(personCustomer.getCity());
        personCustomerEntity.setPostalCode(personCustomer.getPostalCode());
        return personCustomerEntity;
    }
}
