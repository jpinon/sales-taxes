package com.jpinon.sales_taxes.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jpinon.sales_taxes.core.application.Sales;
import com.jpinon.sales_taxes.input_output_adapter_cli.adapter.InputOutputAdapter;

@SpringBootApplication(scanBasePackages = {
    "com.jpinon.sales_taxes"
})
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
    new InputOutputAdapter(new Sales()).startUserInterface();
  }
}
