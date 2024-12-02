package com.packt.ordermanagementapi.adapter.mapper;

import com.packt.ordermanagementapi.adapter.inbound.rest.dto.CompanyCustomerDto;
import com.packt.ordermanagementapi.adapter.inbound.rest.dto.CustomerDto;
import com.packt.ordermanagementapi.adapter.inbound.rest.dto.PersonCustomerDto;
import com.packt.ordermanagementapi.adapter.outbound.database.entity.CompanyCustomerEntity;
import com.packt.ordermanagementapi.adapter.outbound.database.entity.PersonCustomerEntity;
import com.packt.ordermanagementapi.domain.CompanyCustomer;
import com.packt.ordermanagementapi.domain.Customer;
import com.packt.ordermanagementapi.domain.PersonCustomer;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class CustomerCustomMapper {

    @Named("customerDtoToCustomer")
    public Customer customerDtoToCustomer(CustomerDto customerDto) {
        if (customerDto == null) {
            return null;
        }

        Customer customer = switch (customerDto.getCustomerType()) {
            case "person" -> {
                PersonCustomer personCustomer = new PersonCustomer();
                var personCustomerDto = (PersonCustomerDto) customerDto;
                personCustomer.setFirstName(personCustomerDto.getFirstName());
                personCustomer.setLastName(personCustomerDto.getLastName());
                yield personCustomer;
            }
            case "company" -> {
                CompanyCustomer companyCustomer = new CompanyCustomer();
                var companyCustomerDto = (CompanyCustomerDto) customerDto;
                companyCustomer.setCompanyName(companyCustomerDto.getCompanyName());
                companyCustomer.setVatId(companyCustomerDto.getVatId());
                yield companyCustomer;
            }
            default -> new Customer();
        };

        customer.setStreetAddress(customerDto.getStreetAddress());
        customer.setCity(customerDto.getCity());
        customer.setPostalCode(customerDto.getPostalCode());

        return customer;
    }

    @Named("customerToCustomerDto")
    public CustomerDto customerToCustomerDto(Customer customer) {
        if (customer == null) {
            return null;
        }

        CustomerDto customerDto = switch (customer) {
            case PersonCustomerEntity personCustomer -> {
                PersonCustomerDto personCustomerDto = new PersonCustomerDto();
                personCustomerDto.setFirstName(personCustomer.getFirstName());
                personCustomerDto.setLastName(personCustomer.getLastName());
                personCustomerDto.setCustomerType("person");
                yield personCustomerDto;
            }
            case CompanyCustomerEntity companyCustomer -> {
                CompanyCustomerDto companyCustomerDto = new CompanyCustomerDto();
                companyCustomerDto.setCompanyName(companyCustomer.getCompanyName());
                companyCustomerDto.setVatId(companyCustomer.getVatId());
                companyCustomerDto.setCustomerType("company");
                yield companyCustomerDto;
            }
            default -> new CustomerDto();
        };
        customerDto.setStreetAddress(customer.getStreetAddress());
        customerDto.setCity(customer.getCity());
        customerDto.setPostalCode(customer.getPostalCode());

        return customerDto;
    }
}
