package edu.cuny.csi.csc330.lab2;

import java.text.DecimalFormat;
import java.util.Arrays;

public class NumericAnalyzer {
	
	
	/*
	 * Constructor method when class object is created
	 */
	
	public NumericAnalyzer() {
	}
	
	private int inputs[];
	
	public int[] getInputs() {
		return inputs;
	}

	public void setInputs(String[] args) {
		inputs = new int[args.length];
		
		for(int i = 0; i < args.length; i++) {
			try {
			inputs[i] = Integer.parseInt(args[i]);
			} 
			catch (NumberFormatException e) {
				System.err.printf("Input has the string \"%s\", please use all Integers\n", args[i]);
				System.exit(1);
			}
		}
	}
	
	/*
	 * All methods below will find the values needed
	 * for the following operations:
	 * 1.) Sort the list
	 * 2.) Find size of list
	 * 3.) Find mean value of list
	 * 4.) Find median of list.
	 * 5.) Find Min.
	 * 6.) Find Max.
	 * 7.) Find The Sum.
	 * 8.) Find the Range.
	 * 9.) Find the Variance
	 * 10.) Standard Deviation
	 */
	
	private void sort() {
		Arrays.sort(inputs);
	}
	
	public int getSize() {
		return inputs.length;
	}
	
	public int getAverage(){
		int sum = 0;
		for(int number: inputs) {
			sum += number;
		}
		return sum / inputs.length;	
	}
	
	public int getMax() {
		int max = 0;
		for(int number: inputs) {
			max = Math.max(max, number);
		}
		return max;
	}
	
	
	public int getMin() {
		int min = inputs[0];
		if(inputs.length == 1) {
			return inputs[0];
		}
		for(int number: inputs) {
			min = Math.min(min, number);
		}
		return min;
	}
	
	public double getMedian() {
		double median = 0;
		if(inputs.length % 2 != 0) {
			median = inputs[(int) Math.floor(inputs.length / 2)];
		} else {
			median = (inputs[inputs.length / 2] + inputs[inputs.length/2] - 1) / 2;
		}
		return median;
	}
	
	public int getSum() {
		int sum = 0;
		for(int number: inputs) {
			sum +=  number;
		}
		return sum;
	}
	
	public int getRange() {
		return getMax() - getMin();
	}
	
	public long getVariance() {
		long sum = 0;
		for(int i = 0; i < inputs.length; i++) {
			sum += Math.pow(inputs[i] - getAverage(), 2);
		}
		return sum / inputs.length;
	}
	
	public double getStandardDeviation() {
		return Math.sqrt(getVariance());
	}
	
	public void print() {
		System.out.println("Welcome to the ## ~Numeric Analyzer~ ## \n\n");
		System.out.printf("You're original input was: ");
		
		for(int number: inputs) {
			System.out.printf("%2d ", number);
		}
		
		System.out.printf("\n\n~~~We will now perform analysis ~~~ \n\n\n");
		System.out.printf("Your list sorted is: ");
		
		sort();
		
		for(int number: inputs) {
			System.out.printf("%2d ", number);
		}
		
		System.out.printf("\n\n \t ~ Your Results Are ~ \n");
		
		String formatIntegers = "\n %-30s %3s";
		String formatDecimals = "\n %-30s %3s";
		
	    DecimalFormat numFormat = new DecimalFormat("#.##");
	    numFormat.setGroupingUsed(true);
	    numFormat.setGroupingSize(3);
	       
	       
		System.out.printf(formatIntegers, "Amount of elements is:", numFormat.format(getSize()));
		System.out.printf(formatIntegers, "Minimum is:", numFormat.format(getMin()));
		System.out.printf(formatIntegers, "Maximum is:", numFormat.format(getMax()));
		System.out.printf(formatIntegers, "Range is:", numFormat.format(getRange()));
		System.out.printf(formatIntegers, "Sum is:", numFormat.format(getSum()));
		System.out.printf(formatIntegers, "Mean is:", numFormat.format(getAverage()));
		System.out.printf(formatDecimals, "Median is:", numFormat.format(getMedian()));
		System.out.printf(formatIntegers, "Variance is:", numFormat.format(getVariance()));
		System.out.printf(formatDecimals, "Standard Deviation is :", numFormat.format(getStandardDeviation()));
		
		System.out.printf("\n\n We appreciate you using our Numerical Analysis tool! See you soon");

	}
	
	public static void main(String[] args) {
		if(args.length == 0 ) {
			System.err.println("Please add 1 or more arguments to the Numeric Analyzer.");
			System.exit(1); 
		} else {
			NumericAnalyzer argumentAnalyzer = new NumericAnalyzer();
			argumentAnalyzer.setInputs(args);
			argumentAnalyzer.print();
		}
	}
}

