import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class H {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		String line[] = scanner.nextLine().trim().split("\\s");

		int numGrades = Integer.parseInt(line[0]);
		String gradebook = line[1];

		int numCase = 1;

		while (!(numGrades == 0 && gradebook.equals("0"))) {
			boolean[] isUsed = new boolean[gradebook.length()];
			List<Integer> grades = new ArrayList<Integer>();

			int extraChars = gradebook.length() - numGrades;

			// check for "100" in the gradebook string
			for (int i = 0; i < gradebook.length() - 2; i++) {
				if (gradebook.substring(i, i + 3).equals("100") && extraChars >= 2) {
					isUsed[i] = true;
					isUsed[i + 1] = true;
					isUsed[i + 2] = true;
					grades.add(100);
					extraChars -= 2;
				}
			}

			while (extraChars > 0) {
				int maxIndex = 0;
				int maxNum = 0;

				for (int i = 0; i < gradebook.length() - 1; i++) {
					if (!isUsed[i] && !isUsed[i + 1]) {
						if (gradebook.substring(i, i + 1).equals("0")) {
							continue;
						}
						int numAtIndex = Integer.parseInt(gradebook.substring(i, i + 2));
						if (numAtIndex > maxNum) {
							maxIndex = i;
							maxNum = numAtIndex;
						}
					}
				}

				isUsed[maxIndex] = true;
				isUsed[maxIndex + 1] = true;
				grades.add(maxNum);
				extraChars--;
			}

			// fill grades array with the rest of the characters that are unused
			for (int i = 0; i < gradebook.length(); i++) {
				if (!isUsed[i]) {
					int temp = Integer.parseInt(gradebook.substring(i, i + 1));
					grades.add(temp);
				}
			}

			float sum = 0;
			for (int x : grades) {
				sum += x;
			}
			int average = Math.round(sum / grades.size());

			System.out.println("Case " + numCase + ": " + average);

			line = scanner.nextLine().trim().split("\\s");
			numGrades = Integer.parseInt(line[0]);
			gradebook = line[1];
			numCase++;
		}

		scanner.close();

	}

}
