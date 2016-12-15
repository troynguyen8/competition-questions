import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class K {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int numberOfCases = Integer.parseInt(scanner.nextLine().trim());
		for (int test = 1; test <= numberOfCases; test++) {
			int operations = 0;
			String word = scanner.nextLine();
			int indexInWord = 0;

			List<Character> stack = new ArrayList<Character>();

			char[] characters = word.toCharArray();
			for (Character character : characters) {

				boolean satisfied = false;
				while (!satisfied) {
					// Push
					if (!stack.contains(character)) {
						operations++;
						stack.add(character);
					}

					// Need to print it
					if (character == stack.get(stack.size() - 1)) {
						operations++;
						satisfied = true;

						if (word.substring(indexInWord).indexOf(character) == -1) {
							operations++;
							stack.remove(stack.size() - 1);
						}
						break;
					}

					// Pop
					if (character != stack.get(stack.size() - 1) && stack.indexOf(character) < indexInWord) {
						operations++;
						stack.remove(stack.size() - 1);
					}
				}

				indexInWord++;
			}

			// Clear the stack
			operations += stack.size();

			System.out.println("Case " + test + ": " + operations);
		}

		scanner.close();
	}

}
