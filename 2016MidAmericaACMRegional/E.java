import java.util.Scanner;

public class E {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int x = scanner.nextInt();
		int y = scanner.nextInt();
		int n = scanner.nextInt();
		int caseNumber = 1;
		while (x != 0 && y != 0 && n != 0) {
			System.out.println("Case " + caseNumber + ":");

			for (int i = 1; i <= n; i++) {
				if (i % x == 0 && i % y == 0) {
					System.out.println("FizzBuzz");
				} else if (i % x == 0) {
					System.out.println("Fizz");
				} else if (i % y == 0) {
					System.out.println("Buzz");
				} else {
					System.out.println(i);
				}
			}

			x = scanner.nextInt();
			y = scanner.nextInt();
			n = scanner.nextInt();
			caseNumber++;
		}

		scanner.close();
	}

}
