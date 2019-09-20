package com.jpinon.sales_taxes.core.adapter_mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jpinon.sales_taxes.core.ports.BagOfWordsProvider;

public class BagOfWordsAdapterMock implements BagOfWordsProvider {

  List<String> words;

  public BagOfWordsAdapterMock() {
    words = new ArrayList<>(Arrays.asList("chocolate", "book", "headache pill"));
  }

  public List<String> getWords() {
    return words;
  }
}
