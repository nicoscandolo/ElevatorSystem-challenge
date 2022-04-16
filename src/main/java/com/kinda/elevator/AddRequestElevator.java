package com.kinda.elevator;

import com.kinda.elevator.entities.Elevator;
import com.kinda.elevator.entities.Request;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class AddRequestElevator implements Runnable {

    private Elevator elevator;
    private Request request;

    @SneakyThrows
    @Override
    public void run() {
        elevator.addRequest(request);
    }
}
