package edu.cuny.csi.csc330.lab4;

import java.util.ArrayList;
import java.util.HashMap;

import edu.cuny.csi.csc330.util.Randomizer;

/*
 * 
 */

public class Cactus {
	
	public enum Growth{YOUNG, TEENAGER, ADULT};
	final static int MAX_WATER_CAPACITY = 100;
	
	
	public static int cactusAmount; 
	private int daysAlive;
	private double waterPercentage;
	private String cactusName;
	private int spikeAmount;
	private int height;
	private int drought;
	private boolean isDying; 
	private String currLook;
	public Growth stage = Growth.YOUNG;
	private ArrayList<String> cactusItems = new ArrayList<String>();
	private HashMap<Integer, String> cactusLook = new HashMap<Integer, String>();
	
	
	
	public Cactus() {
		init();
	}
	
	public Cactus(String cactus) {
		init();
		setCactusName(cactus);
	} 
	
	private void init () {
		
		cactusLook.put(0,  " _\\_\\/ \r\n"
						  + "-( / )-\r\n"
						  + "  \\_/ \r\n"
						  + "  @@@ ");
		
		
		cactusLook.put(20, "   *-*,\r\n"
				+ "       ,*\\/|`| \\\r\n"
				+ "       \\'  | |'| *,\r\n"
				+ "        \\ `| | |/ )\r\n"
				+ "         | |'| , /\r\n"
				+ "         |'| |, /\r\n"
				+ "       __|_|_|_|__\r\n"
				+ "      [___________]\r\n"
				+ "       |         |\r\n"
				+ "       |         |\r\n"
				+ "       |         |\r\n"
				+ "       |_________|");
		
		
		cactusLook.put(80, "      /|\\\r\n"
				+ "     |||||\r\n"
				+ "     |||||\r\n"
				+ " /\\  |||||\r\n"
				+ "|||| |||||\r\n"
				+ "|||| |||||  /\\\r\n"
				+ "|||| ||||| ||||\r\n"
				+ " \\|`-'|||| ||||\r\n"
				+ "  \\__ |||| ||||\r\n"
				+ "     ||||`-'|||\r\n"
				+ "     |||| ___/\r\n"
				+ "     |||||\r\n"
				+ "     |||||");
		
		/* Temp Current Look */
		
		setCurrLook(cactusLook.get(0));
		setDaysAlive(0);
		setDying(false);
		setHeight(10);
		setSpikeAmount(5);
		setWaterPercentage(20);
		cactusAmount++;
	}
	
	

	public int getDrought() {
		return drought;
	}



	public void setDrought(int drought) {
		this.drought = drought;
	}
	
	


	public int getDaysAlive() {
		return daysAlive;
	}



	public void setDaysAlive(int daysAlive) {
		this.daysAlive = daysAlive;
	}



	public double getWaterPercentage() {
		return waterPercentage;
	}



	public void setWaterPercentage(double waterPercentage) {
		this.waterPercentage = waterPercentage;
	}



	public String getCactusName() {
		return cactusName;
	}



	public void setCactusName(String cactusName) {
		this.cactusName = cactusName;
	}



	public int getSpikeAmount() {
		return spikeAmount;
	}



	public void setSpikeAmount(int spikeAmount) {
		this.spikeAmount = spikeAmount;
	}



	public int getHeight() {
		return height;
	}



	public void setHeight(int height) {
		this.height = height;
	}



	public boolean isDying() {
		return isDying;
	}



	public void setDying(boolean isDying) {
		this.isDying = isDying;
	}



	public ArrayList<String> getCactusItems() {
		return cactusItems;
	}

	public void pushCactusItem(String cactusItem) {
		cactusItems.add(cactusItem);
	}

	public String getCurrLook() {
		return currLook;
	}

	public void setCurrLook(String currLook) {
		this.currLook = currLook;
	}
	
	public void printVitals() {
		System.out.println(getCurrLook());
		System.out.printf("|Days Alive:       %20d|\n", getDaysAlive());
		System.out.printf("|Water Percentage: %20.2f|\n", getWaterPercentage());
		System.out.printf("|Drought Level:    %20d|\n", getDrought());
		System.out.printf("|Danger of Dying:  %20s|\n", isDying() ? "Vitals Critical" : "Vitals Halthy");
		System.out.printf("|Spike Amount:     %20d|\n", getSpikeAmount());
		System.out.printf("|Items on Cactus:  %20s|\n\n\n", getCactusItems());
		
		//printing the results

		System.out.format("|---------The Cactus: %s-------------------|%n", getCactusName());
		System.out.format("+----------------------+---------+---------+---------+%n");
		System.out.format("| Total Plants:%d       | Created | by      |  Adam   |%n", cactusAmount);
		System.out.format("+----------------------+---------+---------+---------+%n\n\n\n\n");


	}
	
	public void waterCactus(double waterAmount) {
		if(getWaterPercentage() >= MAX_WATER_CAPACITY) {
			System.out.println("Your water percentage is at maximum capacity");
			return;
		}
		
		setWaterPercentage(waterAmount);
		System.out.printf("~ You have added %d amount of water, your new total is: %d ~", waterAmount, waterPercentage);;
	}
	
	public void stickToCactus(String item) {
		
		if(getSpikeAmount() <= 0) {
			System.out.println("You have used all of your spikes!");
		}
		
		switch(item) {
		case "person":
			System.out.println("Don't put people onto the cactus!");
			break;
		case "dog":
			System.out.println("Don't put dogs onto the cactus!");
			break;
		default: 
			pushCactusItem(item);
		}
		
		
	}	
	
	public void displayContents() {
		
		System.out.println("Current cactus items: ");
		for(String item: getCactusItems()) {
			System.out.printf("%3s\n", item);
		}
		
	}
	
	public void waitDays(int days) {
		
		
		if(days > 365 || days < 0) {
			System.out.println("Please input a number between 0 to 365");
		}
		
		
		for(int i = 0; i < days; i++) {
			int chanceOfRain = Randomizer.generateInt(1, 4);
			if(chanceOfRain == 3) {
				setWaterPercentage(getWaterPercentage() + 5);
				setDrought(getDrought() - 2);
			} else {
				int droughtSeverity = getDrought() > 10 ? 2 : 1;
				setDrought(getDrought() + droughtSeverity);
				setWaterPercentage(getWaterPercentage() + -2);
				if(getDrought() > 10) {
					this.isDying = true;
				}
			}
							
		}
		
				
		setDaysAlive(getDaysAlive() + days);
		changeAge();
		System.out.printf("%d days have passed. Total amount of days your cactus has been alive: %d \n\n", days, getDaysAlive());



	}
	
	private void changeAge() {
		
		if(getDaysAlive() >= 20 && getDaysAlive() <= 80) {
			currLook = cactusLook.get(20);
		}
		
		if(getDaysAlive() > 80) {
			currLook = cactusLook.get(80);
		}
		
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		printVitals();
		
		return "";
	}

	
	public static int getCactusAmount() {
		return cactusAmount;
	}

	public static void setCactusAmount(int cactusAmount) {
		Cactus.cactusAmount = cactusAmount;
	}





	

	
}
