package com.rahi.VehicalRental;

import com.rahi.VehicalRental.service.executor.ExecutorServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class VehicalRentalApplication {

  public static void main(String[] args) throws IOException {
    ConfigurableApplicationContext context =
        SpringApplication.run(VehicalRentalApplication.class, args);
    context.getBean(ExecutorServiceImpl.class).execute(args[0]);
  }
}
