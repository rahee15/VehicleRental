package com.rahi.VehicalRental.service.executor;

import com.rahi.VehicalRental.service.executor.command.CommandExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class ExecutorServiceImpl {

  @Autowired private CommandExecutorService commandExecutorService;

  public void execute(String filePath) {

    try {
      // Reading file using given filePath
      File file = new File(filePath);
      InputStream inputStream = new FileInputStream(file);

      try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
        String command;
        while ((command = br.readLine()) != null) {
          // Executing each command
          commandExecutorService.execute(command.split(" "));
        }
      }

    } catch (IOException e) {
      throw new RuntimeException("Issue in reading input file");
    }
  }
}
