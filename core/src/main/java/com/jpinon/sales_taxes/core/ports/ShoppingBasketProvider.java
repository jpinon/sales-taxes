package com.jpinon.sales_taxes.core.ports;

import java.util.List;

public interface ShoppingBasketProvider {

  List<String> getShoppingBasketOutput(List<String> input);
}
