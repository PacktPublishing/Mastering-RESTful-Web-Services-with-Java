package com.packt.ordermanagementapi.adapter.outbound.database.entity;

import com.packt.ordermanagementapi.domain.CompanyCustomer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_COMPANY_CUSTOMER")
public class CompanyCustomerEntity extends CustomerEntity {

    @Column(name = "COMPANY_NAME")
    private String companyName;

    @Column(name = "VAT_ID")
    private String vatId;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getVatId() {
        return vatId;
    }

    public void setVatId(String vatId) {
        this.vatId = vatId;
    }

    public static CustomerEntity fromCustomer(CompanyCustomer companyCustomer) {
        var companyCustomerEntity = new CompanyCustomerEntity();
        companyCustomerEntity.setCompanyName(companyCustomer.getCompanyName());
        companyCustomerEntity.setVatId(companyCustomer.getVatId());
        companyCustomerEntity.setStreetAddress(companyCustomer.getStreetAddress());
        companyCustomerEntity.setCity(companyCustomer.getCity());
        companyCustomerEntity.setPostalCode(companyCustomer.getPostalCode());
        return companyCustomerEntity;
    }
}
