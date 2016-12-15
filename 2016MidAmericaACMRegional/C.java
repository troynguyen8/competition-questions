import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class C {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		String[] firstLine = scanner.nextLine().trim().split("\\s");
		int relationships = Integer.parseInt(firstLine[0]);
		int invitations = Integer.parseInt(firstLine[1]);
		int numCases = 1;

		while (relationships != 0 && invitations != 0) {
			System.out.print("Case " + numCases + ":");

			Set<String> firstTeam = new HashSet<String>();
			Set<String> secondTeam = new HashSet<String>();
			List<Set<String>> temps = new ArrayList<Set<String>>();

			for (int i = 0; i < relationships; i++) {
				String[] line = scanner.nextLine().trim().split("\\s");
								
				String friendOrFoe = line[0];
				int numNames = Integer.parseInt(line[1]);

				if (friendOrFoe.equals("e")) {
					String name = line[2];
					String secondName = line[3];
					// check for either name in either set
					if (firstTeam.contains(name)) {
						secondTeam.add(secondName);
					} else if (secondTeam.contains(name)) {
						firstTeam.add(secondName);
					} else if (firstTeam.contains(secondName)) {
						secondTeam.add(name);
					} else if (secondTeam.contains(secondName)) {
						firstTeam.add(name);
					}

					// if not in either set yet
					if (firstTeam.isEmpty() && secondTeam.isEmpty()) {
						firstTeam.add(name);
						secondTeam.add(secondName);
					} else {
						Set<String> oneTemp = new HashSet<String>();
						oneTemp.add(name);
						Set<String> twoTemp = new HashSet<String>();
						twoTemp.add(secondName);

						temps.add(oneTemp);
						temps.add(twoTemp);
					}

				} else if (friendOrFoe.equals("f")) {
					// read input
					Set<String> names = new HashSet<String>();
					for (int j = 0; j < numNames; j++) {
						String tempName = line[j+2];
						names.add(tempName);
					}

					boolean hasAddedNames = false;

					// if either team has one of the names in this line
					for (String thisName : names) {
						if (firstTeam.contains(thisName)) {
							firstTeam.addAll(names);
							hasAddedNames = true;
							break;
						} else if (secondTeam.contains(thisName)) {
							secondTeam.addAll(names);
							hasAddedNames = true;
							break;
						}
					}

					// if both sets are empty
					if (firstTeam.isEmpty() && secondTeam.isEmpty()) {
						firstTeam.addAll(names);
					}
					// at this point neither team already contains these names
					// store in temps
					if (!hasAddedNames) {
						temps.add(names);
					}
				} // end check for friend or foe

				// attempt to merge temps if there are any
				if (!temps.isEmpty()) {
					for (Set<String> set : temps) {
						for (String thisName : set) {
							if (firstTeam.contains(thisName)) {
								firstTeam.addAll(set);
								set.clear();
								break;
							} else if (secondTeam.contains(thisName)) {
								secondTeam.addAll(set);
								set.clear();
								break;
							}
						}
					}
				}

			} // end relationships

			System.out.println("TEAM 1: " + firstTeam.toString());
			System.out.println("TEAM 2: " + secondTeam.toString());
			
			for (int i = 0; i < invitations; i++) {
				String[] line = scanner.nextLine().trim().split("\\s");
				int numInvites = Integer.parseInt(line[0]);
				Set<String> theseNames = new HashSet<String>();

				for (int j = 0; j < numInvites; j++) {
					String thisName = line[j+1];
					theseNames.add(thisName);
				}

				if (firstTeam.containsAll(theseNames) || secondTeam.containsAll(theseNames)) {
					System.out.print(" yes");
				} else {
					System.out.print(" no");
				}
			} // end invites

			String[] relationLine = scanner.nextLine().trim().split("\\s");
			
			relationships = Integer.parseInt(relationLine[0]);
			invitations = Integer.parseInt(relationLine[1]);
			numCases++;
		} // end cases
		
		scanner.close();
	}

}
