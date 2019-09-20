package com.jpinon.sales_taxes.bag_of_words_adapter_yaml.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.jpinon.sales_taxes.core.application.BagOfWords;

@SpringBootTest
@ContextConfiguration(classes = BagOfWordsAdapter.class)
public class BagOfWordsAdapterTest {

  @Test
  public void wordsAreNotNull() {
    assertNotNull(BagOfWords.getInstance().getWords());
  }

  @Test
  public void wordsAreRetrievedCorrectly() {
    List<String> expectedWords = Arrays.asList("chocolate", "book", "headache pill");
    List<String> words = BagOfWords.getInstance().getWords();

    assertEquals(expectedWords, words);
  }

}
