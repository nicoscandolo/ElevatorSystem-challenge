package com.kinda.elevator.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.TreeSet;

@Builder
public class FreightElevator extends Elevator {
    public FreightElevator() {
        maxWeight = 3000;
    }

    public void addRequest(Request request) {
        if ((currentWeight + request.getInternalRequest().getWeight()) <= maxWeight) {
            currentWeight = currentWeight + request.getInternalRequest().getWeight();
            System.out.println("Accepted: New Request from source floor number: "
                    + request.getExternalRequest().getSourceFloor() + " and destination floor number: "
                    + request.getInternalRequest().getDestinationFloor());
            super.addRequest(request);
        } else {
            System.out.println("Denied: New Request for destination floor number: " + request.getInternalRequest().getDestinationFloor()
                    + " : weight exceeds the maximum weight. Maximum weight allow for Freight Elevator is: " + maxWeight + "KG");
        }
    }

    //check weight
    //check: do we need to move floor by floor or do we need only to indicate the final floor
    //check current floor
    //check max floor
    //check subir, bajar, subir sobre lo subido, bajar sobre lo bajado
}
