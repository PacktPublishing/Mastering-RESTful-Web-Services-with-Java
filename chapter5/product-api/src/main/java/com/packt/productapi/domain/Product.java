package com.packt.productapi.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {

  private String name;

  private String sku;

  private String description;

  private BigDecimal price;

  public Product() {
    super();
  }

  public Product(String name, String sku, String description, BigDecimal price) {
    this.name = name;
    this.sku = sku;
    this.description = description;
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getSku() {
    return sku;
  }

  public void setSku(String sku) {
    this.sku = sku;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return Objects.equals(sku, product.sku);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sku);
  }
}

