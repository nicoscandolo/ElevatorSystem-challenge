package com.kinda.elevator;

import com.kinda.elevator.entities.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ElevatorApplication {

    private static final Scanner keyboard = new Scanner(System.in);


    public static void main(String[] args) throws InterruptedException {
        Elevator publicElevator = new PublicElevator();
        Elevator freightElevator = new FreightElevator();
        ExecuteElevator executePublicElevator = new ExecuteElevator(publicElevator);
//		ExecuteElevator executeFreightElevator = new ExecuteElevator(freightElevator);
        int userChoiceFloor = 0;
        int userChoiceElevator = 0;
        int currentFloor = 0;


//		Thread for starting the elevator
        Thread Thread1 = new Thread(executePublicElevator);
//		Thread Thread2 = new Thread(executeFreightElevator);
        Thread1.start();
//		Thread2.start();
        Thread.sleep(3000);

        System.out.println("after thread1 start");

//		example para public elevator(???
        ExternalRequest er = new ExternalRequest(Direction.UP, 0);
        InternalRequest ir = new InternalRequest(5, 500);
        Request request = new Request(ir, er);

//		example para public elevator(???
        ExternalRequest er2 = new ExternalRequest(Direction.UP, 3);
        InternalRequest ir2 = new InternalRequest(15, 400);
        Request request2 = new Request(ir2, er2);

//		example para public elevator(???
        ExternalRequest er3 = new ExternalRequest(Direction.UP, 0);
        InternalRequest ir3 = new InternalRequest(2, 600);
        Request request3 = new Request(ir3, er3);

//		add request to the elevator
        System.out.println("before thread add request elevator start");
        new Thread(new AddRequestElevator(publicElevator, request)).start();
        Thread.sleep(3000);
        new Thread(new AddRequestElevator(publicElevator, request3)).start();
        Thread.sleep(3000);
        new Thread(new AddRequestElevator(publicElevator, request2)).start();
        Thread.sleep(3000);


//		//add do while or while til user exit
//		userChoiceElevator = mainMenu();
//
//		switch (userChoiceElevator){
//			case 1:
//				System.out.println("Welcome to Public Elevator.");
//				userChoiceFloor = floorMenu();
////				currentFloor = publicElevator.move(userChoiceFloor);
//				//person wants to go in up direction from source floor 0
//				ExternalRequest er = new ExternalRequest(Direction.UP, 0);
//
//				//the destination floor is 5
//				InternalRequest ir = new InternalRequest(userChoiceFloor);
//				Request request1 = new Request(ir, er);
//
//				System.out.println("Current floor is:" + currentFloor);
//				break;
//
//			case 2:
//				System.out.println("Welcome to Freight Elevator.");
//				userChoiceFloor = floorMenu();
//				currentFloor = freightElevator.move(userChoiceFloor);
//				System.out.println("Current floor is:" + currentFloor);
//				break;
//
//			case 3:
//			System.out.println("Goodbye");
//			break;
//		}


    }

    private static int floorMenu() {
        int option = -3;
        while (option <= -1 || option >= 50) {
            System.out.println("Which floor do you want to go to? Enter the number");
            try {
                option = Integer.parseInt(keyboard.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Choose a floor between -1 and 50");
            }
        }
        return option;
    }

    private static int mainMenu() {
        int option = 0;
        while (option < 1 || option > 3) {
            System.out.println("Welcome. Choose an elevator to move:");
            System.out.println("[1] - Public Elevator.");
            System.out.println("[2] - Freight Elevator.");
            System.out.println("[3] - Exit program.");
            try {
                option = Integer.parseInt(keyboard.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Choose the values mentioned on the menu");
            }
        }
        return option;
    }
}
