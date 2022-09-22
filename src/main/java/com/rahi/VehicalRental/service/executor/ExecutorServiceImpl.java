package com.rahi.VehicalRental.service.executor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rahi.VehicalRental.service.executor.command.CommandExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Scanner;

@Service
public class ExecutorServiceImpl {

  @Autowired private CommandExecutorService commandExecutorService;

  @Autowired private ObjectMapper objectMapper;

  public void execute() {
    System.out.println("Enter the Test File Path");
    Scanner scanner = new Scanner(System.in);
    String filePath = scanner.nextLine();

    try {
      File file = new File(filePath);
      InputStream inputStream = new FileInputStream(file);

      try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
        String command;
        while ((command = br.readLine()) != null) {
          System.out.println(command);
          commandExecutorService.execute(command.split(" "));
        }
      }

    } catch (IOException e) {
      throw new RuntimeException("Issue in reading input file");
    }
  }
}
