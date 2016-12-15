import java.util.Scanner;

public class F {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int numberOfSwathers = scanner.nextInt();
		int numberOfStages = scanner.nextInt();

		int caseNumber = 1;
		while (!(numberOfSwathers == 0 && numberOfStages == 0)) {
			System.out.print("Case " + caseNumber + ":");

			int totalTime = 0;
			int[][] swathers = new int[numberOfSwathers][numberOfStages];

			// Read in data
			for (int i = 0; i < numberOfSwathers; i++) {
				for (int j = 0; j < numberOfStages; j++) {
					swathers[i][j] = scanner.nextInt();
				}
			}

			while (swathers[numberOfSwathers - 1][numberOfStages - 1] != 0) {
				int minValue = Integer.MAX_VALUE;
				int swathersIndex = 0;

				if (numberOfStages != 1 && numberOfSwathers != 1 && swathersIndex == numberOfSwathers - 1) {
					for (int col = 0; col < numberOfStages; col++) {
						totalTime += swathers[swathersIndex][col];
					}
					break;
				}

				for (int col = numberOfStages - 1; col >= 0; col--) {
					for (int row = 0; row < numberOfSwathers; row++) {
						if ((row - 1 == -1 || swathers[row - 1][col] == 0) && swathers[row][col] != 0
								&& (col - 1 == -1 || swathers[row][col - 1] == 0)) {
							if (swathers[row][col] < minValue) {
								minValue = swathers[row][col];
							}
						}
					}
				}

				totalTime += minValue;

				for (int col = numberOfStages - 1; col >= 0; col--) {
					for (int row = numberOfSwathers - 1; row >= swathersIndex; row--) {
						if ((row - 1 == -1 || swathers[row - 1][col] == 0) && swathers[row][col] != 0
								&& (col - 1 == -1 || swathers[row][col - 1] == 0)) {
							swathers[row][col] -= minValue;

							if (col == numberOfStages - 1 && swathers[row][col] == 0) {
								System.out.print(" " + totalTime);
								swathersIndex++;
							}
						}
					}
				}
			}

			numberOfSwathers = scanner.nextInt();
			numberOfStages = scanner.nextInt();
			caseNumber++;

			System.out.println();
		}

		scanner.close();
	}

}
