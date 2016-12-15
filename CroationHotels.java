/**
 * @author Troy Nguyen
 * ~unknown date, prior to October 2016
 * This program finds the maximum amount of consecutive hotels an individual can buy based on their prices and the amount of prize money won
 */

import java.util.Scanner;

public class CroationHotels {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int numberOfHotels = input.nextInt();
		int lotteryMoney = input.nextInt();

		int[] hotelCosts = new int[numberOfHotels];

		for (int i = 0; i < numberOfHotels; i++) {
			hotelCosts[i] = input.nextInt();
		}

		int max = 0;

		for (int j = 0; j < hotelCosts.length; j++) {
			int sum = 0;
			for (int k = j; k < hotelCosts.length; k++) {
				if (sum == lotteryMoney) {
					break;
				}
				if (sum + hotelCosts[k] <= lotteryMoney) {
					sum += hotelCosts[k];
				}
			}

			max = Math.max(max, sum);
			if(max == lotteryMoney) {
				break;
			}
		}

		System.out.println(max);
	}
}
