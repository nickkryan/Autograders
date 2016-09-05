import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
public class hw0Output {	
	private static int grade = 0;

	public static void main(String[] args) {
		File dir = new File(".");
		File[] files = dir.listFiles();
		for (File f : files) {
			if (!f.getName().contains("Nimbly") && !f.getName().contains("hw0Output")) {
				outputHelper(f);
			}
		}
		System.out.println("Tentative Grade: " + grade);
	}

	private static void outputHelper(File file) {
		//File name check
		System.out.println("Checking file: " + file.getName());
		if ("hw0-output.txt".equals(file.getName())) {
			grade += 10;
		} else {
			grade += 5;
		}
		
		System.out.println("After File Name Check: " + grade);
		List<String> fileContents = fileToList(file);
		fileContentHelper(fileContents);
	}

	private static void fileContentHelper(List<String> fileContents) {
		//Javac version check
		if (fileContents.contains("javac 1.")) {
			grade += 5;
		}
		if (fileContents.contains("javac 1.8.")) {
			grade += 5;
		}
		System.out.println("After Javac Check: " + grade);

		//Java version check
		if (fileContents.contains("java version \"1.")) {
			grade += 5;
		}
		if (fileContents.contains("java version \"1.8.")) {
			grade += 5;
		}
		System.out.println("After Java Check: " + grade);

		// NimblyBimbly Check
		if (fileContents.contains("Meow Meow Meow Meow Meow Meow"
			+ " Meow Meow Meow ...") && fileContents.contains("Meow!")) {
			grade += 40;
		}
		System.out.println("After NimblyBimbly Check: " + grade);
		
		//School Check
		if (fileContents.contains("Compile") || fileContents.contains("Compilation")) {
			grade += 10;
			int lastIndex = fileContents.lastIndexOf("Runtime");
			if (lastIndex > 5) {
				System.out.println("Check their answer"
					+ " for Compile vs Runtime."
					+ " You may need to take points off.");
			}
		}
		System.out.println("After Compile/Runtime Check: " + grade);
		
		if (fileContents.contains("System.out.printf(\"%s > %s%n\"")) {
			grade += 10;
			int index = fileContents.indexOf("System.out.printf");
			if (fileContents.get(index).contains("4")) {
				grade += 10;
			}
		}
		System.out.println("After Error Cause Check: " + grade);
	}

	private static List<String> fileToList(File file) {
		List<String> result = new CaseInsensitiveList();
		try {
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				result.add(scan.nextLine().trim());	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private static class CaseInsensitiveList extends ArrayList<String> {
		@Override
		public boolean contains(Object o) {
			if (!(o instanceof String)) return false;
			String other = (String) o;
			for (String s : this) {
				if (s.contains(other)) return true;
			}
			return false;
		}

		@Override
		public int indexOf(Object o) {
			if (!(o instanceof String)) return -1;
			String other = (String) o;
			for (int i = 0; i < size(); i++) {
				if (get(i).contains(other)) {
					return i;
				}
			}
			return -1;
		}

		@Override
		public int lastIndexOf(Object o) {
			if (!(o instanceof String)) return -1;
			String other = (String) o;
			for (int i = size() - 1; i >= 0; i--) {
				if (get(i).contains(other)) return i;
			}
			return -1;
		}
	}
}
