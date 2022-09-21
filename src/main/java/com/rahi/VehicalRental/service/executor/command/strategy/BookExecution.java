package com.rahi.VehicalRental.service.executor.command.strategy;

import org.springframework.stereotype.Service;

@Service
public class BookExecution implements CommandExecutionStrategyService {
    @Override
    public void executeCommand(String[] operands) {
        System.out.println("Book Executing");
    }
}
