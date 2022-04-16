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
            System.out.println("Accepted: New Request from source floor number: "
                    + request.getExternalRequest().getSourceFloor() + " and destination floor number: "
                    + request.getInternalRequest().getDestinationFloor());
            super.addRequest(request);
        } else {
            System.out.println("Denied: New Request for destination floor number: " + request.getInternalRequest().getDestinationFloor()
                    + " : weight exceeds the maximum weight. Maximum weight allow for Public Elevator is: " + maxWeight + "KG");
        }
    }


    //check keycard
    //multiple request hay, fijarme que pasa si me agregan una de la nada?
    //check weight
    //check: do we need to move floor by floor or do we need only to indicate the final floor
    //check current floor
    //check max floor
    //add test
    //check subir, bajar, subir sobre lo subido, bajar sobre lo bajado


}
