/**
 * @author Troy Nguyen
 * 10/15/16
 * Prints the maximum bits used while typing a number into a calculator
 */

import java.util.Scanner;

public class Bits {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int testCount = input.nextInt();
		
		for(int i = 1; i <= testCount; i++) {
			Integer n = new Integer(input.nextInt());
			int max = 0;
			
			while(n != 0) {
				max = Math.max(max, getOnesCount(n));
				n = n / 10;
			}
			
			System.out.println(max);
		}
	}
	
	
	public static int getOnesCount(Integer n) {
		String binaryString = Integer.toBinaryString(n);
		char[] arr = binaryString.toCharArray();
		
		
		int onesCount = 0;
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == '1') {
				onesCount++;
			}
		}
		
		return onesCount;
	}
}
