package com.jpinon.sales_taxes.input_output_adapter_cli.adapter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputProvider {

  private final List<String> firstScenarioInput;
  private final List<String> secondScenarioInput;
  private final List<String> thirdScenarioInput;

  private final Scanner terminalInput;

  public InputProvider(InputStream inputStream) {
    terminalInput = new Scanner(inputStream);
    firstScenarioInput = new ArrayList<>(
        Arrays.asList("1 book at 12.49", "1 music CD at 14.99", "1 chocolate bar at 0.85"));
    secondScenarioInput = new ArrayList<>(
        Arrays.asList("1 imported box of chocolates at 10.00", "1 imported bottle of perfume at 47.50"));
    thirdScenarioInput = new ArrayList<>(Arrays
        .asList("1 imported bottle of perfume at 27.99", "1 bottle of perfume at 18.99",
            "1 packet of headache pills at 9.75", "1 box of imported chocolates at 11.25"));
  }

  public List<String> getFirstScenarioInput() {
    return firstScenarioInput;
  }

  public List<String> getSecondScenarioInput() {
    return secondScenarioInput;
  }

  public List<String> getThirdScenarioInput() {
    return thirdScenarioInput;
  }

  public List<String> getFreestyleInput() {
    List<String> freestyleInput = new ArrayList<>();

    do {
      freestyleInput.add(waitForUserInput());
    } while (!freestyleInput.get(freestyleInput.size() - 1).equals(""));

    freestyleInput.remove(freestyleInput.size() - 1);

    return freestyleInput;
  }

  public String waitForUserInput() {
    return terminalInput.nextLine();
  }

}
