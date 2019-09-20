package com.jpinon.sales_taxes.core.application;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.jpinon.sales_taxes.core.domain.Product;
import com.jpinon.sales_taxes.core.ports.ShoppingBasketProvider;

public class Sales implements ShoppingBasketProvider {

  private static final double IMPORTED_TAX = 5.0;
  private static final double REGULAR_TAX = 10.0;

  private static String TOTAL_TAXES_STRING = "Sales Taxes: ";
  private static String TOTAL_PRICE_STRING = "Total: ";


  public Sales() {

  }

  public List<String> getShoppingBasketOutput(List<String> input) {
    return toShoppingBasketList(applyTaxes(toProductList(input)));
  }

  private List<Product> toProductList(List<String> stringList) {
    return stringList
        .stream()
        .map(Product::fromString)
        .collect(Collectors.toList());
  }

  private List<Product> applyTaxes(List<Product> productList) {
    productList
        .stream()
        .filter(product -> product.isImported() || !product.containsWord(BagOfWords.getInstance().getWords()))
        .forEach(product -> product.applyTax(!product.isImported() ? REGULAR_TAX :
            !product.containsWord(BagOfWords.getInstance().getWords()) ? REGULAR_TAX + IMPORTED_TAX : IMPORTED_TAX));

    return productList;
  }

  private List<String> toShoppingBasketList(List<Product> productList) {
    List<String> stringList = productList
        .stream()
        .map(Product::toShoppingBasketString)
        .collect(Collectors.toList());

    stringList
        .addAll(Arrays.asList(TOTAL_TAXES_STRING + String.format("%.2f", getTotalTaxes(productList)),
            TOTAL_PRICE_STRING + String.format("%.2f", getTotalPrice(productList))));

    return stringList;
  }

  private double getTotalTaxes(List<Product> productList) {
    return productList
        .stream()
        .map(Product::getTax)
        .collect(Collectors.summingDouble(Double::doubleValue));
  }

  private double getTotalPrice(List<Product> productList) {
    return productList
        .stream()
        .map(Product::getPrice)
        .collect(Collectors.summingDouble(Double::doubleValue));
  }
}
