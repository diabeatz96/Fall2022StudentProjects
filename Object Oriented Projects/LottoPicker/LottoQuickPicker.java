/**
 * LAB 3 -  Lotto QuickPicker Game 
 */
package edu.cuny.csi.csc330.lab3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;

import edu.cuny.csi.csc330.util.Randomizer;


public class LottoQuickPicker {
	
	// constants  specific to current game - BUT NOT ALL GAMES 
	public final static int DEFAULT_GAME_COUNT = 1; 
	private final static String GAME_NAME = "Lotto"; 
	private final static int SELECTION_POOL_SIZE = 59; 
	private final static int SELECTION_COUNT = 6;
	private final static String STORE_NAME = "Adam's Always Winning Lotto";
	private ArrayList<ArrayList<Integer>> lottoNumbers;
    private final Calendar currentTime = Calendar.getInstance();
    
    // Old date member but leaving for explanation on improved logic. see line 128 & 158
	private HashMap<Integer, BigDecimal> checked = new HashMap<Integer, BigDecimal>();



	public LottoQuickPicker() {
		init(DEFAULT_GAME_COUNT); 
	}
	
	public LottoQuickPicker(int games) {
		init(games); 
	}
  
	private void loadLottoNumbers(int games) {
		
		lottoNumbers = new ArrayList<ArrayList<Integer>>();
		
		for(int i = 0; i < games; i++) {
			ArrayList<Integer> game = new ArrayList<Integer>(); 
			this.lottoNumbers.add(game);
			for(int j = 0; j < SELECTION_COUNT; j++) {
				int randomNum = Randomizer.generateInt(1, SELECTION_POOL_SIZE);
				if (!lottoNumbers.get(i).contains(randomNum)) {
					this.lottoNumbers.get(i).add(randomNum);
				} else {
					int newNum = Randomizer.generateInt(1, SELECTION_POOL_SIZE);
					while(newNum == randomNum) {
						newNum = Randomizer.generateInt(1, SELECTION_POOL_SIZE);
					}
					this.lottoNumbers.get(i).add(newNum);
				}
			}
		}	
	}
	
	private void init(int games) {
		
		/**
		 * 
		 * Now what ... START FROM HERE 
		 * What additional methods do you need?
		 * What additional data properties/members do you need? 
		 */
		
		loadLottoNumbers(games);
	}
	
	
	public void displayNumbers() {
		
		for(int i = 0; i < lottoNumbers.size(); i++) {
			System.out.printf("(%d) ", i + 1);
			for(int j = 0; j < SELECTION_COUNT; j++) {
				Collections.sort(lottoNumbers.get(i));
				System.out.printf("%02d ", lottoNumbers.get(i).get(j));
			}
			System.out.println("     |");
		}
	}
	/**
	 * 
	 */
	public void displayTicket() {
		
		/**
		 * display heading 
		 * 
		 * for i in gameCount 
		 * 		generate selectionCount number of unique random selections in ascending order 
		 * 
		 * display footer 
		 */
		
		
		// display ticket heading 
		displayHeading(); 
		
		// displays numbers, designed for consistency and look
		displayNumbers();
		
		// display ticket footer 
		displayFooter(); 
		
		return;
	}
	
	protected void displayHeading() {
		
		System.out.print("~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n");
		System.out.printf("~~~~~~~~~ %s ~~~~~~~~~~~|\n",GAME_NAME);
		System.out.println(currentTime.getTime() + "\n");
	}
	
	protected void displayFooter() {
		
		System.out.format("\nYour chances are 1 in %,d \n", calculateOdds());
		System.out.printf("Brought to you by: %s", STORE_NAME);
		
	}
	
	
	/**
	 *  
	 * @return
	 */
	private long calculateOdds() {
		int startLotto = SELECTION_POOL_SIZE - SELECTION_COUNT + 1;
		return calculateCombinations(startLotto);
	}
	

	/*
	 * By doing the factorial of the max selection pool size selection_count times (simply doing 54 * 55 * 56 * 57 * 58 * 59)
	 * divided by the factorial of the selection count of numbers.
	 * We are able to determine a clean rough estimate of possible combinations. 
	 * 
	 *  A version of the logic found here: https://homework.study.com/explanation/how-many-combinations-are-possible-with-6-numbers-between-1-59.html#:~:text=Answer%20and%20Explanation%3A,1%20and%2059%20is%2045%2C057%2C474.
	 *  
	 *  This solution is improved because we do not need to waste o(N) space storing a hash table,
	 *  rather we can just do a simple o(n) time complexity question to solve this lab.
	 */
	
	
	private long calculateCombinations(int combinations) {
			long numerator = combinations;
			long denominator = 1;
			long calculation = 0;
			
			for(int i = combinations + 1; i <= SELECTION_POOL_SIZE; i++) {
					numerator *= i;	
				} 
			
			for(int i = 1; i <= SELECTION_COUNT; i++) {
					denominator *= i;	
				} 
			
			calculation = numerator / denominator;
			
			return calculation;
	}
	
/* This is my old factorial method that works, but, when numbers become too large the calculation gets out of hand.
 * Even for a size of 60 possible combinations, the digits are well above the 30 digit limit of a BigDecimal output
 * It is unrealistic, however, I a better way has been form with the function both.
 * 
 * I utilized recursive sequence along with calculating it via a hash table, so it does not have to do an insane amount of calculations.
 */
	
	private BigDecimal calculateFactorial(int num) {
		
		if(!checked.containsKey(num)) {
		if(BigDecimal.ONE.equals(BigDecimal.valueOf(num))) 
			checked.put(num, (BigDecimal.ONE));
		else {
			checked.put(num, BigDecimal.valueOf(num).multiply(BigDecimal.valueOf(calculateFactorial(num - 1).longValue())));
		}
		return checked.get(num);
	}
		return checked.get(num);

	}
/*
  

	/**
	 * @param args 
	 */
	public static void main(String[] args) {
		// takes an optional command line parameter specifying number of QP games to be generated
		//  By default, generate 1  
		int numberOfGames  = DEFAULT_GAME_COUNT; 
		
		if(args.length > 0) {  // if user provided an arg, assume it to be a game count
			numberOfGames = Integer.parseInt(args[0]);  // [0] is the 1st element!
		}
		
		LottoQuickPicker lotto = new LottoQuickPicker(numberOfGames);
		 
		lotto.displayTicket(); 
		

	}

}
