package edu.cuny.csi.csc330.lab4;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		boolean exitProgram = false; 
		
	    Scanner readInput = new Scanner(System.in);  // Create a Scanner object
	    System.out.println("Enter your Cactus Name");
	    String cactusName = readInput.nextLine();  // Read user input
		
		Cactus myCactus = new Cactus(cactusName);

		System.out.printf("----- Welcome to the ~Cactus Simulator~  ----- \n\n ");
		System.out.printf("In cactus simulator, you are able to select froms several \n options to water and live in its new digital desert! \n ");
		System.out.println("Please select from the belows options:");
		
		while(!exitProgram)  {
			
			int choice;
		    Scanner input = new Scanner(System.in);  // Create a Scanner object
			System.out.println("1.) Wait a certain amount of days for your cactus");
			System.out.println("2.) Stick an item to the cactus");
			System.out.println("3.) Add Another Cactus to Garden");
			System.out.println("4.) Print vitals of cactus");
			System.out.println("5.) Exit Program\n\n");
			
			choice = readInput.nextInt();

			switch (choice) {
			case 1: {
				int amount;
				System.out.println("How many days would you like to wait? ");
				amount = readInput.nextInt();
				myCactus.waitDays(amount);
				break;
			}
			
			case 2: {
				System.out.println("Please input what item you want to work with");
				String item = input.nextLine();
				myCactus.pushCactusItem(item);
				break;
			}
			
			case 3: {
				Cactus.setCactusAmount(Cactus.getCactusAmount() + 1);
				System.out.printf("Amount of Cactus with your friend %s: %d\n\n", myCactus.getCactusName(), Cactus.getCactusAmount());
				break;
			}
			
			case 4:{
				System.out.println(myCactus);
				break;
			}
			
			case 5: {
				exitProgram = true;
				 break;
			}
			default:
				System.out.println("Please type a valid number");
			}


			
		}
		
		System.out.println("Have a great day!");

		

	}

}
