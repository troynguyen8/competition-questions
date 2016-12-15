import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class A {

	private final static double CAR_LENGTH = 4.4d;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int carAX = scanner.nextInt();
		int carBX = scanner.nextInt();

		int caseNumber = 1;
		while (!(carAX == 0 && carBX == 0)) {
			Integer previousDistance = null;

			List<Integer> carATimes = new ArrayList<Integer>();
			List<Integer> carBTimes = new ArrayList<Integer>();

			int numberOfCarATimes = scanner.nextInt();
			for (int i = 0; i < numberOfCarATimes; i++) {
				carATimes.add(scanner.nextInt());
			}

			int numberOfCarBTimes = scanner.nextInt();
			for (int i = 0; i < numberOfCarBTimes; i++) {
				carBTimes.add(scanner.nextInt());
			}

			boolean carAMoving = false;
			boolean carBMoving = false;

			for (int time = 0;; time++) {
				if (carAMoving)
					carAX++;
				if (carBMoving)
					carBX++;
				
				if (carATimes.contains(time)) {
					carAMoving = !carAMoving;
					carATimes.remove((Integer) time);
				}

				if (carBTimes.contains(time)) {
					carBMoving = !carBMoving;
					carBTimes.remove((Integer) time);
				}

				// check for collision
				if ((carAX + CAR_LENGTH >= carBX && carAX + CAR_LENGTH <= carBX + CAR_LENGTH)
						|| (carBX + CAR_LENGTH >= carAX && carBX + CAR_LENGTH <= carAX + CAR_LENGTH)) {
					System.out.println("Case " + caseNumber + ": bumper tap at time " + time);
					break;
				}

				if (carATimes.isEmpty() && carBTimes.isEmpty()) {
					if (previousDistance == null) {
						previousDistance = Math.abs(carAX - carBX);
						continue;
					}

					if (Math.abs(carAX - carBX) >= previousDistance) {
						System.out.println("Case " + caseNumber + ": safe and sound");
						break;
					}
				}
			}

			carAX = scanner.nextInt();
			carBX = scanner.nextInt();
			caseNumber++;
		}
		scanner.close();
	}

}
