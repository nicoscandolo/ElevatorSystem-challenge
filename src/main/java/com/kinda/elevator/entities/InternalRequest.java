package com.kinda.elevator.entities;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InternalRequest {
    private int destinationFloor;
    private int weight;
}
