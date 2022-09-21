package com.rahi.VehicalRental.service.executor.command.strategy;

import org.springframework.stereotype.Service;

@Service
public class DisplayVehiclesExecution implements CommandExecutionStrategyService {
    @Override
    public void executeCommand(String[] operands) {
        System.out.println("Display Executing");
    }
}
