package com.jpinon.sales_taxes.core.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.jpinon.sales_taxes.core.adapter_mock.BagOfWordsAdapterMock;

public class BagOfWordsTest {

  @BeforeAll
  public static void beforeAll() {
    BagOfWords.getInstance().setBagOfWordsProvider(new BagOfWordsAdapterMock());
  }

  @Test
  public void bagOfWordsIsNotNull() {
    assertNotNull(BagOfWords.getInstance());
  }

  @Test
  public void wordListIsNotNull() {
    assertNotNull(BagOfWords.getInstance().getWords());
  }

  @Test
  public void wordListIsCorrectlyPopulated() {
    assertEquals(BagOfWords.getInstance().getWords(), new BagOfWordsAdapterMock().getWords());
  }
}
