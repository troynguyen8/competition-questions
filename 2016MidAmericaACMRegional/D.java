import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class D {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String[] firstLineElements = scanner.nextLine().split("\\s");
		int commands = Integer.parseInt(firstLineElements[0]);
		int filesToBePrinted = Integer.parseInt(firstLineElements[1]);

		int caseNumber = 1;
		while (!(commands == 0 && filesToBePrinted == 0)) {
			System.out.println("Case " + caseNumber + ":");
			List<File> files = new ArrayList<File>();
			List<String> directories = new ArrayList<String>();

			for (int i = 0; i < commands; i++) {
				String commandLine = scanner.nextLine();
				String[] command = commandLine.split("\\s");

				if (command[0].equals("echo")) {
					String[] commandLineSplitAtQuotes = commandLine.split("\"");
					echo(commandLineSplitAtQuotes[1], commandLineSplitAtQuotes[2].trim(), files, directories);
				} else if (command[0].equals("cp")) {
					cp(command[1], command[2], files, directories);
				} else if (command[0].equals("mv")) {
					mv(command[1], command[2], files, directories);
				} else if (command[0].equals("rm")) {
					rm(command[1], files, directories);
				} else if (command[0].equals("mkdir")) {
					mkdir(command[1], files, directories);
				} else if (command[0].equals("rmdir")) {
					rmdir(command[1], files, directories);
				}
			}

			outer: for (int i = 0; i < filesToBePrinted; i++) {
				String fileToPrint = scanner.nextLine();

				for (File file : files) {
					if ((file.directory + file.name).equals(fileToPrint)) {
						System.out.println("Query " + (i + 1) + ": " + file.content);
						continue outer;
					}
				}

				// If it gets here there was no matching file
				System.out.println("Query " + (i + 1) + ": invalid");
			}

			firstLineElements = scanner.nextLine().split("\\s");
			commands = Integer.parseInt(firstLineElements[0]);
			filesToBePrinted = Integer.parseInt(firstLineElements[1]);

			caseNumber++;
		}

		scanner.close();
	}

	public static void echo(String content, String file, List<File> files, List<String> directories) {
		int directorySplitIndex = file.lastIndexOf("/") + 1;
		String directory;
		String fileName;

		if (directorySplitIndex != -1) {
			directory = file.substring(0, directorySplitIndex);
			fileName = file.substring(directorySplitIndex);
		} else {
			directory = "";
			fileName = file;
		}

		for (File fileInFiles : files) {
			if ((fileInFiles.directory + fileInFiles.name).equals(directory + fileName)) {
				// File exists
				fileInFiles.content = content;
				return;
			}
		}

		// If it gets here, the file doesn't exist; Create the file
		files.add(new File(fileName, directory, content));
	}

	public static void cp(String source, String destination, List<File> files, List<String> directories) {
		for (int j = 0; j < files.size(); j++) {
			File fileInFiles = files.get(j);
			if ((fileInFiles.directory + fileInFiles.name).equals(source)) {
				// Found the source file, lets create the destination file now
				for (int i = 0; i < files.size(); i++) {
					File destinationFile = files.get(i);

					if ((destinationFile.directory + destinationFile.name).equals(destination)) {
						// Found the destination file, copy contents
						destinationFile.content = fileInFiles.content;
						return;
					}
				}

				// If it gets here, we didnt find the destination file, we must
				// create it
				int directorySplitIndex = destination.lastIndexOf("/") + 1;
				String directory;
				String fileName;

				if (directorySplitIndex != -1) {
					directory = destination.substring(0, directorySplitIndex);
					fileName = destination.substring(directorySplitIndex);
				} else {
					directory = "";
					fileName = destination;
				}

				files.add(new File(fileName, directory, fileInFiles.content));
			}
		}
	}

	public static void mv(String source, String destination, List<File> files, List<String> directories) {
		for (int i = 0; i < files.size(); i++) {
			File sourceFile = files.get(i);

			if ((sourceFile.directory + sourceFile.name).equals(source)) {
				int directorySplitIndex = destination.lastIndexOf("/") + 1;
				String directory;
				String fileName;

				if (directorySplitIndex != -1) {
					directory = destination.substring(0, directorySplitIndex);
					fileName = destination.substring(directorySplitIndex);
				} else {
					directory = "";
					fileName = destination;
				}

				sourceFile.directory = directory;
				sourceFile.name = fileName;
			}
		}
	}

	public static void rm(String file, List<File> files, List<String> directories) {
		for (int i = 0; i < files.size(); i++) {
			File sourceFile = files.get(i);

			if ((sourceFile.directory + sourceFile.name).equals(file)) {
				files.remove(sourceFile);
				return;
			}
		}
	}

	public static void mkdir(String directory, List<File> files, List<String> directories) {
		directories.add(directory + "/");
	}

	public static void rmdir(String directory, List<File> files, List<String> directories) {
		directory += "/";
		
		for (int i = 0; i < directories.size(); i++) {
			if (directories.get(i).equals(directory)) {
				for (int j = 0; j < files.size(); j++) {
					File file = files.get(j);

					if (file.directory.contains("/" + directories.get(i)) || file.directory.equals(directories.get(i))
							|| file.directory.startsWith(directories.get(i))) {
						files.remove(j);
						j--;
					}
				}

				directories.remove(i);
				return;
			}
		}
	}

	private static class File {

		public String name;
		public String directory;
		public String content;

		public File(String name, String directory, String content) {
			this.name = name;
			this.directory = directory;
			this.content = content;
		}

	}

}
