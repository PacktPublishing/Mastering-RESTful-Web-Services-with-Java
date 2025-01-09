package com.packt.ordermanagementapi.usecase;

import com.packt.ordermanagementapi.domain.Customer;
import com.packt.ordermanagementapi.domain.Product;

import java.util.List;

public record OrderRequest(Customer customer, List<Product> products) {

}
