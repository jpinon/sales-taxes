package com.jpinon.sales_taxes.core.domain;

import java.util.List;
import java.util.Optional;

public class Product {

  private int quantity;
  private boolean isImported;
  private String productName;
  private double price;
  private double tax;

  private Optional<Boolean> containsWord;

  public Product(int quantity, boolean isImported, String productName, double price) {
    this.quantity = quantity;
    this.isImported = isImported;
    this.productName = productName;
    this.price = price;
    this.tax = 0;
    this.containsWord = Optional.empty();
  }

  public static Product fromString(String input) {
    final int quantity = Integer.parseInt(input.substring(0, input.indexOf(' ')));
    input = input.substring(input.indexOf(' '));

    final boolean isImported = input.contains(" imported ");
    input = input.replace("imported ", "");

    final double price = Double.parseDouble(input.substring(input.lastIndexOf(' ')));
    input = input.replace(input.substring(input.lastIndexOf(" at ")), "");

    return new Product(quantity, isImported, input.trim(), price);
  }

  public static String toShoppingBasketString(Product product) {
    return new StringBuilder()
        .append(product.getQuantity())
        .append(" ")
        .append(product.isImported() ? "imported " : "")
        .append(product.getProductName())
        .append(": ")
        .append(String.format("%.2f", product.getPrice())).toString();
  }

  public int getQuantity() {
    return quantity;
  }

  public boolean isImported() {
    return isImported;
  }

  public String getProductName() {
    return productName;
  }

  public double getPrice() {
    return price;
  }

  public double getTax() {
    return tax;
  }

  public boolean isEqualTo(Product product) {
    return product.getQuantity() == quantity && product.isImported() == isImported
        && product.getProductName().equals(productName) && product.getPrice() == price;
  }

  public void applyTax(double tax) {
    this.tax = Math.round(this.price * tax / 100 * 20.0) / 20.0;
    this.price += this.tax;
  }

  public boolean containsWord(List<String> stringList) {
    return containsWord.orElseGet(() -> stringList.stream()
        .anyMatch(value -> productName.toLowerCase().contains(value.toLowerCase())));
  }
}
