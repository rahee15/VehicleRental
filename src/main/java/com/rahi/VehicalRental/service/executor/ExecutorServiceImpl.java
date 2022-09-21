package com.rahi.VehicalRental.service.executor;

import com.rahi.VehicalRental.service.executor.command.CommandExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExecutorServiceImpl {

    @Autowired
    private CommandExecutorService commandExecutorService;

    public void execute() {
        String command = "ADD_BRANCH B1 CAR,BIKE,VAN";
        commandExecutorService.execute(command.split(" "));
        command = "ADD_VEHICLE B1 CAR V1 500";
        commandExecutorService.execute(command.split(" "));
        command = "ADD_VEHICLE B1 CAR V2 1000";
        commandExecutorService.execute(command.split(" "));
        command = "ADD_VEHICLE B1 BIKE V3 250";
        commandExecutorService.execute(command.split(" "));
        command = "ADD_VEHICLE B1 BIKE V4 300";
        commandExecutorService.execute(command.split(" "));
        command = "ADD_VEHICLE B1 BUS V5 2500";
        commandExecutorService.execute(command.split(" "));
        command = "BOOK B1 VAN 1 5";
        commandExecutorService.execute(command.split(" "));
        command = "BOOK B1 CAR 1 3";
        commandExecutorService.execute(command.split(" "));
        command = "BOOK B1 BIKE 2 3";
        commandExecutorService.execute(command.split(" "));
        command = "BOOK B1 BIKE 2 5";
        commandExecutorService.execute(command.split(" "));
        command = "DISPLAY_VEHICLES B1 1 5";
        commandExecutorService.execute(command.split(" "));



    }
}
