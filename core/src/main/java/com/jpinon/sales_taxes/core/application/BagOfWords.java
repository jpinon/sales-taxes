package com.jpinon.sales_taxes.core.application;

import java.util.ArrayList;
import java.util.List;

import com.jpinon.sales_taxes.core.ports.BagOfWordsProvider;

public final class BagOfWords {

  private static BagOfWords instance;
  private BagOfWordsProvider bagOfWordsProvider;

  private BagOfWords() {
  }

  public static BagOfWords getInstance() {
    return instance == null ? instance = new BagOfWords() : instance;
  }

  public List<String> getWords() {
    return bagOfWordsProvider != null ? bagOfWordsProvider.getWords() : new ArrayList<>();
  }

  public void setBagOfWordsProvider(BagOfWordsProvider bagOfWordsProvider) {
    this.bagOfWordsProvider = bagOfWordsProvider;
  }
}
