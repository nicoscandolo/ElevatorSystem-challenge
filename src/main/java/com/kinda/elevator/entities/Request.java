package com.kinda.elevator.entities;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Request implements Comparable<Request> {
    private InternalRequest internalRequest;
    private ExternalRequest externalRequest;

    @Override
    public int compareTo(Request req) {
        return Integer.compare(this.getInternalRequest().getDestinationFloor(), req.getInternalRequest().getDestinationFloor());
    }
}
