package com.jpinon.sales_taxes.bag_of_words_adapter_yaml.adapter;

import javax.annotation.PostConstruct;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jpinon.sales_taxes.core.application.BagOfWords;
import com.jpinon.sales_taxes.core.ports.BagOfWordsProvider;

@Component
public class BagOfWordsAdapter implements BagOfWordsProvider {

  @Value("${sales-taxes.bag-of-words.words}")
  private List<String> words;

  @PostConstruct
  private void init() {
    BagOfWords.getInstance().setBagOfWordsProvider(this);
  }

  public List<String> getWords() {
    return words;
  }
}
