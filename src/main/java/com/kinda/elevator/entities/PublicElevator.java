package com.kinda.elevator.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.TreeSet;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicElevator extends Elevator {
    private final int maxWeight = 1000;
    private boolean keyCardAccess;

    public void addRequest(Request request) {
        if ((currentWeight + request.getInternalRequest().getWeight()) <= maxWeight) {
            if (request.getInternalRequest().getDestinationFloor() >= -1 && request.getInternalRequest().getDestinationFloor() <= 50) {
                System.out.println("Accepted: New Request from source floor number: "
                        + request.getExternalRequest().getSourceFloor() + " and destination floor number: "
                        + request.getInternalRequest().getDestinationFloor());
                super.addRequest(request);
            } else {
                System.out.println("Denied: New Request with destination floor number: " + request.getInternalRequest().getDestinationFloor() + " does not exist");
            }
        } else {
            System.out.println("Denied: New Request for destination floor number: " + request.getInternalRequest().getDestinationFloor()
                    + " : weight exceeds the maximum weight. Maximum weight allow for Public Elevator is: " + maxWeight + "KG");
        }
    }
    //check keycard
    //check max flor and min floor
    //add test
}
