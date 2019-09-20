package com.jpinon.sales_taxes.core.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.jpinon.sales_taxes.core.adapter_mock.BagOfWordsAdapterMock;
import com.jpinon.sales_taxes.core.application.BagOfWords;

public class ProductTest {

  @BeforeAll
  public static void beforeAll() {
    BagOfWords.getInstance().setBagOfWordsProvider(new BagOfWordsAdapterMock());
  }

  @Test
  public void importedStringToProduct() {
    String input = "1 imported bottle of perfume at 27.99";

    Product expectedProduct = new Product(1, true, "bottle of perfume", 27.99);
    Product parsedProduct = Product.fromString(input);

    assertEquals(true, expectedProduct.isEqualTo(parsedProduct));
  }

  @Test
  public void importedBetweenNameStringToProduct() {
    String input = "1 bottle of imported perfume at 27.99";
    Product expectedProduct = new Product(1, true, "bottle of perfume", 27.99);
    Product parsedProduct = Product.fromString(input);

    assertEquals(true, expectedProduct.isEqualTo(parsedProduct));
  }

  @Test
  public void notImportedStringToProduct() {
    String input = "1 bottle of perfume at 27.99";
    Product expectedProduct = new Product(1, false, "bottle of perfume", 27.99);
    Product parsedProduct = Product.fromString(input);

    assertEquals(true, expectedProduct.isEqualTo(parsedProduct));
  }

  @Test
  public void importedProductToString() {
    Product product = new Product(1, true, "test name", 1);

    assertEquals("1 imported test name: 1.00", Product.toShoppingBasketString(product));
  }

  @Test
  public void notImportedProductToString() {
    Product product = new Product(1, false, "test name", 1);

    assertEquals("1 test name: 1.00", Product.toShoppingBasketString(product));
  }

  @Test
  public void applyTaxCorrectPointFiveRounding() {
    List<Product> productList = new ArrayList<>();

    IntStream.range(43, 47).forEach(tax -> {
      Product product = new Product(1, false, "testName", 1);
      product.applyTax(tax);
      productList.add(product);
    });

    productList.forEach(product -> assertEquals(1.45, product.getPrice()));
  }

  @Test
  public void applyTaxCorrectPointZeroRounding() {
    List<Product> productList = new ArrayList<>();

    IntStream.range(48, 52).forEach(tax -> {
      Product product = new Product(1, false, "testName", 1);
      product.applyTax(tax);
      productList.add(product);
    });

    productList.forEach(product -> assertEquals(1.50, product.getPrice()));
  }

  @Test
  public void noWordInBagOfWords() {
    Product product = new Product(1, false, "music CD", 1);

    assertEquals(false, product.containsWord(BagOfWords.getInstance().getWords()));
  }

  @Test
  public void fullWordInBagOfWords() {
    Product product = new Product(1, false, "chocolate", 1);

    assertEquals(true, product.containsWord(BagOfWords.getInstance().getWords()));
  }

  @Test
  public void fullWordWithCapitalLettersInBagOfWords() {
    Product product = new Product(1, false, "Chocolate", 1);

    assertEquals(true, product.containsWord(BagOfWords.getInstance().getWords()));
  }

  @Test
  void fullWordInBagOfWordsWithCapitalLetters() {
    Product product = new Product(1, false, "Chocolate", 1);

    assertEquals(true, product.containsWord(
        BagOfWords.getInstance().getWords().stream().map(String::toUpperCase).collect(Collectors.toList())));
  }

  @Test
  public void partialWordInBagOfWords() {
    Product product = new Product(1, false, "box of headache pills", 1);

    assertEquals(true, product.containsWord(BagOfWords.getInstance().getWords()));
  }

  @Test
  public void partialWordWithCapitalLettersInBagOfWords() {
    Product product = new Product(1, false, "Box of headache Pills", 1);

    assertEquals(true, product.containsWord(BagOfWords.getInstance().getWords()));
  }

  @Test
  void partialWordInBagOfWordsWithCapitalLetters() {
    Product product = new Product(1, false, "box of headache pills", 1);

    assertEquals(true, product.containsWord(
        BagOfWords.getInstance().getWords().stream().map(String::toUpperCase).collect(Collectors.toList())));
  }
}
