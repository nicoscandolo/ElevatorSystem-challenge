package com.kinda.elevator.entities;

import com.kinda.elevator.interfaces.ElevatorInterface;

import java.util.TreeSet;

public abstract class Elevator implements ElevatorInterface {
    protected int currentFloor = 0;
    protected Direction currentDirection = Direction.UP;
    protected State currentState = State.WAITING;
    protected int currentWeight = 0;
    protected int maxWeight;
    protected TreeSet<Request> currentRequests = new TreeSet<>();
    protected TreeSet<Request> upPendingRequests = new TreeSet<>();
    protected TreeSet<Request> downPendingRequests = new TreeSet<>();


    public void startElevator() throws InterruptedException {
        System.out.println("The Elevator has started functioning");
        while (true) {

            if (checkIfRequest()) {
                if (currentDirection == Direction.UP) {
                    Request request = currentRequests.pollFirst();
                    processUpRequest(request);
                    if (currentRequests.isEmpty()) {
                        addPendingDownRequestsToCurrentRequests();
                    }
                }

                if (currentDirection == Direction.DOWN) {
                    Request request = currentRequests.pollLast();
                    processDownRequest(request);
                    if (currentRequests.isEmpty()) {
                        addPendingUpRequestsToCurrentRequests();
                    }
                }
            }
        }
    }

    public void addRequest(Request request) {
        if (currentState == State.WAITING) {
            currentState = State.MOVING;
            currentDirection = request.getExternalRequest().getDirectionToGo();
            currentRequests.add(request);
        } else if (currentState == State.MOVING) {
            if (request.getExternalRequest().getDirectionToGo() != currentDirection) {
                addRequestToPendingRequests(request);
            } else if (request.getExternalRequest().getDirectionToGo() == currentDirection) {
                if (request.getInternalRequest().getDestinationFloor() < currentFloor && currentDirection == Direction.UP) {
                    addRequestToPendingRequests(request);

                } else if (request.getInternalRequest().getDestinationFloor() > currentFloor && currentDirection == Direction.DOWN) {
                    addRequestToPendingRequests(request);

                } else {
                    currentRequests.add(request);
                }
            }
        }

    }

    public void addRequestToPendingRequests(Request request) {
        if (request.getExternalRequest().getDirectionToGo() == Direction.UP) {
            System.out.println("Adding request to UP pending request");
            upPendingRequests.add(request);
        } else {
            System.out.println("Adding request to DOWN pending request");
            downPendingRequests.add(request);
        }
    }

    public boolean checkIfRequest() {
        return !currentRequests.isEmpty();
    }

    public void processUpRequest(Request request) throws InterruptedException {
        goToSourceFloor(request);
        currentWeight = currentWeight + request.getInternalRequest().getWeight();
        int startFloor = currentFloor;
        for (int i = startFloor + 1; i <= request.getInternalRequest().getDestinationFloor(); i++) {
            Thread.sleep(1000);
            System.out.println("↑ We have reached floor number: " + i);
            currentFloor = i;

            if (checkIfNewRequestCanBeProcessed(request)) {
                break;
            }
        }
        //el ascensor llego al piso que el user selecciono como destino
        System.out.println("Reached Source Floor-----opening door in floor: " + currentFloor);
        currentWeight = currentWeight - request.getInternalRequest().getWeight();
    }

    public void processDownRequest(Request request) throws InterruptedException {
        goToSourceFloor(request);
        currentWeight = currentWeight + request.getInternalRequest().getWeight();
        int startFloor = currentFloor;
        for (int i = startFloor - 1; i >= request.getInternalRequest().getDestinationFloor(); i--) {
            Thread.sleep(1000);
            System.out.println("↓ We have reached floor number: " + i);
            currentFloor = i;

            if (checkIfNewRequestCanBeProcessed(request)) {
                break;
            }
        }
        //el ascensor llego al piso que el user selecciono como destino
        System.out.println("Reached Source Floor-----opening door in floor: " + currentFloor);
        currentWeight = currentWeight - request.getInternalRequest().getWeight();
    }

    protected void goToSourceFloor(Request request) throws InterruptedException {
        int startFloor = currentFloor;
        if (startFloor < request.getExternalRequest().getSourceFloor()) {
            for (int i = startFloor + 1; i <= request.getExternalRequest().getSourceFloor(); i++) {
                Thread.sleep(1000);
                System.out.println("↑ We have reached floor number: " + i);
                currentFloor = i;
            }
        } else if (startFloor > request.getExternalRequest().getSourceFloor()) {
            for (int i = startFloor - 1; i >= request.getExternalRequest().getSourceFloor(); i--) {
                Thread.sleep(1000);
                System.out.println("↓ We have reached floor number: " + i);
                currentFloor = i;
            }
        }
        //el ascensor llego al piso que el user pidio para bajar. EL User baja y va al piso que quiere
        System.out.println("Reached Source Floor-----opening door in floor: " + currentFloor);
    }

    protected boolean checkIfNewRequestCanBeProcessed(Request currentRequest) {
        if (checkIfRequest()) {
            if (currentDirection == Direction.UP) {
                Request request = currentRequests.pollLast();
                if (request.getInternalRequest().getDestinationFloor() < currentRequest.getInternalRequest().getDestinationFloor()
                        && currentFloor <= request.getExternalRequest().getSourceFloor()) {
                    currentRequests.add(request);
                    currentRequests.add(currentRequest);
                    return true;
                }
                currentRequests.add(request);
            }

            if (currentDirection == Direction.DOWN) {
                Request request = currentRequests.pollFirst();
                if (request.getInternalRequest().getDestinationFloor() > currentRequest.getInternalRequest().getDestinationFloor()
                        && currentFloor >= request.getExternalRequest().getSourceFloor()) {
                    currentRequests.add(request);
                    currentRequests.add(currentRequest);
                    return true;
                }
                currentRequests.add(request);
            }
        }
        return false;
    }

    protected void addPendingDownRequestsToCurrentRequests() {
        if (!downPendingRequests.isEmpty()) {
            System.out.println("Adding down pending request and execute it by adding to current requests");
            currentRequests = downPendingRequests;
            currentDirection = Direction.DOWN;
        } else {
            currentState = State.WAITING;
            System.out.println("Elevator is waiting for new requests");
        }
    }

    protected void addPendingUpRequestsToCurrentRequests() {
        if (!upPendingRequests.isEmpty()) {
            System.out.println("Adding up pending request and execute it by adding to current requests");
            currentRequests = upPendingRequests;
            currentDirection = Direction.UP;
        } else {
            currentState = State.WAITING;
            System.out.println("Elevator is waiting for new requests");
        }
    }
}
