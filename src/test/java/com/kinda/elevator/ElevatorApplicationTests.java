package com.kinda.elevator;

import com.kinda.elevator.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ElevatorApplicationTests {

	private Request request;
	Elevator publicElevator;

	@BeforeEach
	public void setUp() throws Exception {
		InternalRequest ir = new InternalRequest(3,60);
		ExternalRequest er = new ExternalRequest(Direction.UP, 0);
		request = new Request(ir,er);
		publicElevator = new PublicElevator();
		ExecuteElevator executePublicElevator = new ExecuteElevator(publicElevator);

	}

	@Test
	void testProcessUpRequest() throws InterruptedException {
		publicElevator.processUpRequest(request);

		assertEquals(3,publicElevator.getCurrentFloor());
		assertEquals(Direction.UP,publicElevator.getCurrentDirection());
		assertEquals(0,publicElevator.getCurrentWeight());
		assertEquals(State.WAITING,publicElevator.getCurrentState());
	}

}
