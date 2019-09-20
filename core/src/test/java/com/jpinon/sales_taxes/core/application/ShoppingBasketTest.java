package com.jpinon.sales_taxes.core.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.jpinon.sales_taxes.core.adapter_mock.BagOfWordsAdapterMock;

public class ShoppingBasketTest {

  private static Sales sales;

  private static List<String> firstScenarioInput;
  private static List<String> secondScenarioInput;
  private static List<String> thirdScenarioInput;

  private static List<String> firstScenarioOutput;
  private static List<String> secondScenarioOutput;
  private static List<String> thirdScenarioOutput;

  @BeforeAll
  public static void beforeAll() {

    BagOfWords.getInstance().setBagOfWordsProvider(new BagOfWordsAdapterMock());

    sales = new Sales();

    firstScenarioInput = new ArrayList<>(
        Arrays.asList("1 book at 12.49", "1 music CD at 14.99", "1 chocolate bar at 0.85"));
    secondScenarioInput = new ArrayList<>(
        Arrays.asList("1 imported box of chocolates at 10.00", "1 imported bottle of perfume at 47.50"));
    thirdScenarioInput = new ArrayList<>(Arrays
        .asList("1 imported bottle of perfume at 27.99", "1 bottle of perfume at 18.99",
            "1 packet of headache pills at 9.75", "1 box of imported chocolates at 11.25"));

    firstScenarioOutput = new ArrayList<>(Arrays
        .asList("1 book: 12.49", "1 music CD: 16.49", "1 chocolate bar: 0.85", "Sales Taxes: 1.50", "Total: 29.83"));
    secondScenarioOutput = new ArrayList<>(Arrays
        .asList("1 imported box of chocolates: 10.50", "1 imported bottle of perfume: 54.65", "Sales Taxes: 7.65",
            "Total: 65.15"));
    thirdScenarioOutput = new ArrayList<>(Arrays
        .asList("1 imported bottle of perfume: 32.19", "1 bottle of perfume: 20.89", "1 packet of headache pills: 9.75",
            "1 imported box of chocolates: 11.80", "Sales Taxes: 6.65", "Total: 74.63"));
  }

  @Test
  public void firstScenario() {
    List<String> shoppingBasketOutput = sales.getShoppingBasketOutput(firstScenarioInput);

    assertEquals(firstScenarioOutput, shoppingBasketOutput);
  }

  @Test
  public void secondScenario() {
    List<String> shoppingBasketOutput = sales.getShoppingBasketOutput(secondScenarioInput);

    assertEquals(secondScenarioOutput, shoppingBasketOutput);
  }

  @Test
  public void thirdScenario() {
    List<String> shoppingBasketOutput = sales.getShoppingBasketOutput(thirdScenarioInput);

    assertEquals(thirdScenarioOutput, shoppingBasketOutput);
  }
}
