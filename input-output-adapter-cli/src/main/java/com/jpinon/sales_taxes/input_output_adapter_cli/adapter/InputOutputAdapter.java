package com.jpinon.sales_taxes.input_output_adapter_cli.adapter;

import java.io.InputStream;
import java.util.List;

import com.jpinon.sales_taxes.core.ports.ShoppingBasketProvider;

public class InputOutputAdapter {

  private final ShoppingBasketProvider shoppingBasketProvider;

  private final InputProvider inputProvider;

  public InputOutputAdapter(ShoppingBasketProvider shoppingBasketProvider) {
    this(shoppingBasketProvider, System.in);
  }

  public InputOutputAdapter(ShoppingBasketProvider shoppingBasketProvider, InputStream inputStream) {
    this.shoppingBasketProvider = shoppingBasketProvider;
    inputProvider = new InputProvider(inputStream);
  }

  public void startUserInterface() {
    String userInput;
    do {
      printMenu();
      userInput = inputProvider.waitForUserInput();
      decideAction(userInput);
    } while (!userInput.equals("5"));
  }

  private void printMenu() {
    System.out.println();
    System.out.println("What do you want to do?");
    System.out.println();
    System.out.println("1. Launch scenario 1");
    System.out.println("2. Launch scenario 2");
    System.out.println("3. Launch scenario 3");
    System.out.println("4. Freestyle");
    System.out.println("5. Leave");
    System.out.println();
  }

  private void printFreestyleMenu() {
    System.out.println();
    System.out.println("This option is in Alpha yet. Please follow the next pattern and nobody will get hurt:");
    System.out.println("<quantity> <imported*> <product-name> at <price>");
    System.out.println("* Imported parameter is optional. Write 'imported' or let it empty");
    System.out.println();
    System.out.println("Once you are finished, please enter an empty line");
    System.out.println();
  }

  private void decideAction(String action) {
    switch (action) {
      case "1":
        printStringList(shoppingBasketProvider.getShoppingBasketOutput(inputProvider.getFirstScenarioInput()));
        break;
      case "2":
        printStringList(shoppingBasketProvider.getShoppingBasketOutput(inputProvider.getSecondScenarioInput()));
        break;
      case "3":
        printStringList(shoppingBasketProvider.getShoppingBasketOutput(inputProvider.getThirdScenarioInput()));
        break;
      case "4":
        printFreestyleMenu();
        printStringList(shoppingBasketProvider.getShoppingBasketOutput(inputProvider.getFreestyleInput()));
        break;
      case "5":
        System.out.println("Leaving... See you soon!");
        break;
      default:
        System.out.println("It seems you miss clicked :)");
        break;
    }
    System.out.println();
  }

  private void printStringList(List<String> listToPrint) {
    listToPrint.stream().forEach(System.out::println);
  }

}
