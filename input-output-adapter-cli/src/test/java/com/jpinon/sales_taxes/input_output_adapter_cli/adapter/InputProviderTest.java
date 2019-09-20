package com.jpinon.sales_taxes.input_output_adapter_cli.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class InputProviderTest {

  private InputStream inputStream;
  private InputProvider inputProvider;
  private String userInput;

  @Test
  public void singleUserInputIsRetrievedCorrectly() {
    userInput = "This is a test input\n";
    inputStream = new ByteArrayInputStream(userInput.getBytes(Charset.defaultCharset()));
    inputProvider = new InputProvider(inputStream);

    assertEquals("This is a test input", inputProvider.waitForUserInput());
  }

  @Test
  public void multipleUserInputIsRetrievedCorrectly() {
    userInput = "This is a test input\n"
        + "This is another test input\n";
    inputStream = new ByteArrayInputStream(userInput.getBytes(Charset.defaultCharset()));
    inputProvider = new InputProvider(inputStream);

    assertEquals("This is a test input", inputProvider.waitForUserInput());
    assertEquals("This is another test input", inputProvider.waitForUserInput());
  }

  @Test
  public void emptyFreestyleList() {
    userInput = "\n";
    inputStream = new ByteArrayInputStream(userInput.getBytes(Charset.defaultCharset()));
    inputProvider = new InputProvider(inputStream);

    assertEquals(new ArrayList<>(), inputProvider.getFreestyleInput());
  }

  @Test
  public void freestyleListIsCorrectlyPopulated() {
    userInput = "This is a test input\n"
        + "This is another test input\n"
        + "This is the last test input\n"
        + "\n";
    inputStream = new ByteArrayInputStream(userInput.getBytes(Charset.defaultCharset()));
    inputProvider = new InputProvider(inputStream);

    List<String> expectedList = new ArrayList<>(
        Arrays.asList("This is a test input", "This is another test input", "This is the last test input"));

    assertEquals(expectedList, inputProvider.getFreestyleInput());
  }
}
