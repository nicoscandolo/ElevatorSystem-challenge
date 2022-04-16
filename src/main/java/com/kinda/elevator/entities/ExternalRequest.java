package com.kinda.elevator.entities;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExternalRequest {
    private Direction directionToGo;
    private int sourceFloor;
}
