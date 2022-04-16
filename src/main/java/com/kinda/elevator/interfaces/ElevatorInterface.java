package com.kinda.elevator.interfaces;


import com.kinda.elevator.entities.Request;

public interface ElevatorInterface {
    int maxFloor = 50;
    int MinFloor = -1;

    void startElevator() throws InterruptedException;

    void addRequest(Request request);

    void addRequestToPendingRequests(Request request);

    boolean checkIfRequest();

    void processUpRequest(Request request) throws InterruptedException;

    //add methods
}
