package com.kinda.elevator;

import com.kinda.elevator.entities.Elevator;
import com.kinda.elevator.entities.FreightElevator;
import com.kinda.elevator.entities.PublicElevator;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class ExecuteElevator implements Runnable {

    private Elevator elevator;

    @SneakyThrows
    @Override
    public void run() {
        Thread.sleep(200);
        elevator.startElevator();
    }
}
