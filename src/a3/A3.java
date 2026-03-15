package a3;
import java.util.*;
import java.io.*;

public class A3{
    
    static Scanner sc = new Scanner(System.in);
    static ArrayList<String> students = new ArrayList<>();
    static Map<String, double[]> studentGrades = new HashMap<>();
    static int menuChoice;
    static final String FILE_NAME = "student_data.txt";
    
    public static void main (String [] args) {
        
        loadData();
        do {
            System.out.println("""
                           Choose an option:
                           1.) Add Student
                           2.) Remove Student
                           3.) Encode Grades
                           4.) Search Student
                           5.) Display All Students Data
                           6.) Save & Exit
                           """);
        System.out.print("Enter your choice (1-6): ");
        menuChoice = getIntInput();
        
        switch(menuChoice) {
            case 1 ->  addStudents();
            case 2 ->  removeStudents();
            case 3 ->  encodeGrades();
            case 4 ->  searchStudent();
            case 5 ->  displayAllStudents();
            case 6 ->  saveAndExit();
            default ->  System.out.println(menuChoice + " is not an option");
        }
      }
        while(menuChoice !=6);
    }
    
    // MENU INPUT VALIDATION
    public static int getIntInput() {
    while (true) {
        String input = sc.nextLine().trim(); 
        
        if (input.isEmpty()) {
            System.out.println("-------------------------------------------------------");
            System.out.println("Input cannot be empty! Please enter a number.");
            System.out.print("> ");
            continue; 
        }

        try{
            return Integer.parseInt(input);
        } 
        
        catch (NumberFormatException e) {
            System.out.println("-------------------------------------------------------");
            System.out.println("'" + input + "' is not a valid number!");
            System.out.println("Please Try Again!");
            System.out.print("> ");
        }
    }
}
   // ADD STUDENTS
   static void addStudents() {
    if (students.size() >= 10) {
        System.out.println("");
        System.out.println("Maximum students reached (10/10)!");
        return;
    }
   
     Collections.sort(students);
        
        System.out.println("--- Current Student List ---");
        for (int i = 0; i < students.size(); i++) {
            String prefix = (i + 1) + ".)";
            System.out.printf("%-3s %s%n", prefix, students.get(i));
        }
        
    System.out.println("");
    System.out.println("--- Enrolling Students ---");
    System.out.println("");
    for (int i = students.size(); i < 10; i++) {
        System.out.print("Enter name for Student #" + (i + 1) + " or type \"exit\": ");
        String name = sc.nextLine().trim();

        while (true) {
            
            if (name.isEmpty()) {
                System.out.println("Name cannot be empty!");
            }
            
             if (name.equalsIgnoreCase("exit")) {
                 System.out.println("");
                return;
            }
            
            else {
                boolean exists = false;
                for (String s : students) {
                    if (s.equalsIgnoreCase(name)) {
                        exists = true;
                        break;
                    }
                }
   
                if (exists) {
                    System.out.println("Error: " + name + " is already enrolled!");
                } 
                
                else {
                    break;
                }
            }
            
            System.out.print("Enter name for Student #" + (i + 1) + ": ");
            name = sc.nextLine().trim();
        }

        students.add(name);
        System.out.println(">> " + name + " has been enrolled!");
    }
    
       System.out.println("");
       System.out.println("-------------------------------------------------------");
       System.out.println("Registration complete. All 10 slots are full.");
       System.out.println("-------------------------------------------------------");
       System.out.println("");
}
    
   // REMOVE STUDENTS
   static void removeStudents() {
    while (true) {

        if (students.isEmpty()) {
            System.out.println("");
            System.out.println("-------------------------------------------------------");
            System.out.println("The list is now empty! Returning to main menu...");
            System.out.println("-------------------------------------------------------");
            System.out.println("");
            return; 
        }
        
        Collections.sort(students);
        
        System.out.println("--- Current Student List ---");
        
        for (int i = 0; i < students.size(); i++) {
            String prefix = (i + 1) + ".)";
            System.out.printf("%-3s %s%n", prefix, students.get(i));
        }
        
        System.out.println("");
        System.out.println("--- Remove a Student ---");
        System.out.println("(Type 'exit' to go back to main menu)");
        System.out.print("Enter name to remove or type \"exit\": ");
        
        String nameToRemove = sc.nextLine().trim();

        if (nameToRemove.isEmpty()) {
            System.out.println(">> Error: Input cannot be empty! Please try again.");
            continue; 
        }

        if (nameToRemove.equalsIgnoreCase("exit")) {
            System.out.println("Sayonara~");
            System.out.println("----------------------------");
            System.out.println("");
            break; 
        }

        boolean found = false;
        for (int i = 0; i < students.size(); i++) {
        if (students.get(i).equalsIgnoreCase(nameToRemove)) {
        String removedName = students.remove(i);
        
        studentGrades.remove(removedName.toLowerCase());
        
        System.out.println(">> " + removedName + " has been removed from the class and their grades were cleared!");
        found = true;
        break;
        }
     }

        if (!found) {
            System.out.println(">> Error: student named '" + nameToRemove + "' cannot found.");
         }
      }
   }
   
   // ENCODE GRADES
   static void encodeGrades() {
      if (students.isEmpty()) {
        
        System.out.println("");
        System.out.println("-------------------------------------------------------------");
        System.out.println("No students enrolled. Please add a student first.");
        System.out.println("-------------------------------------------------------------");
        System.out.println("");
        return;
    }

     double[][] allGrades = new double[10][3];

     for (int i = 0; i < students.size(); i++) {
        
        System.out.println("Encoding grades for: " + students.get(i));
        for (int j = 0; j < 3; j++) {
            while (true) {
                System.out.print("Enter Grade " + (j + 1) + ": ");
                
                try {
                    
                    double grade = Double.parseDouble(sc.nextLine());
                    if (grade < 0 || grade > 100) {
                        System.out.println("Invalid grade! Please enter 0-100.");
                        continue;
                    }
                    
                    allGrades[i][j] = grade;
                    break;
                } 
                
                catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a numeric grade.");
                }
            }
        }
        
        studentGrades.put(students.get(i).toLowerCase(), allGrades[i]);
        System.out.println("Grades saved for " + students.get(i) + ".\n");
    }
}
  
   // LINEAR SEARCH
   static void searchStudent() {
    if (students.isEmpty()) {
        System.out.println("");
        System.out.println("--------------------------------");
        System.out.println("No students enrolled yet.");
        System.out.println("--------------------------------");
        System.out.println("");
        return;
    }

    System.out.print("Enter student name to search: ");
    String target = sc.nextLine().trim();

    int n = students.size();
    String[] names = students.toArray(new String[0]);
    double[] averages = new double[n];

    for (int i = 0; i < n; i++) {
        double[] grades = studentGrades.getOrDefault(names[i].toLowerCase(), new double[]{0, 0, 0});
        double sum = 0;
        for (double g : grades) sum += g;
        averages[i] = sum / 3.0;
    }

    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - i - 1; j++) {
            if (averages[j] < averages[j + 1]) {
                double tempAvg = averages[j];
                averages[j] = averages[j + 1];
                averages[j + 1] = tempAvg;

                String tempName = names[j];
                names[j] = names[j + 1];
                names[j + 1] = tempName;
            }
        }
    }

    boolean found = false;
    System.out.println("\n--- Search Result ---");
    for (int i = 0; i < n; i++) {
        if (names[i].equalsIgnoreCase(target)) {
            System.out.println("Name:    " + names[i]);
            System.out.printf("Average: %.2f%n", averages[i]);
            System.out.println("Rank:    #" + (i + 1)); 
            found = true;
            break;
        }
    }

    if (!found) {
        System.out.println("Student '" + target + "' not found.");
    }
    System.out.println("----------------------\n");
}
   
   // DISPLAY ALL STUDENT DATA
   static void displayAllStudents() {
    if (students.isEmpty()) {
        System.out.println("");
        System.out.println("-----------------------------");
        System.out.println("No students to display.");
        System.out.println("-----------------------------");
        System.out.println("");
        return;
    }

    int n = students.size();
    String[] names = students.toArray(new String[0]);
    double[] averages = new double[n];

    for (int i = 0; i < n; i++) {
        double[] grades = studentGrades.getOrDefault(names[i].toLowerCase(), new double[]{0, 0, 0});
        double sum = 0;
        for (double g : grades) sum += g;
        averages[i] = sum / 3.0;
    }

    // Bubble Sort
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - i - 1; j++) {
            if (averages[j] > averages[j + 1]) {
                
                // SORT AVERAGE
                double tempAvg = averages[j];
                averages[j] = averages[j + 1];
                averages[j + 1] = tempAvg;

                // SORT NAMES TO SYNC THE AVERAGE
                String tempName = names[j];
                names[j] = names[j + 1];
                names[j + 1] = tempName;
            }
        }
    }
     
    // DISPLAY 
    System.out.println("");
    System.out.println("\n--- Students (Sorted by Average Ascending) ---");
    System.out.println("\n-----------------------------------------------------------");
    System.out.printf("\n%-35s | %-15s %n", "NAME", "AVERAGE");
    for (int i = 0; i < n; i++) {
        System.out.printf("%-35s | %.2f%n", names[i], averages[i]);
    }
    
    System.out.println("\n-----------------------------------------------------------");
    System.out.println("");
  }
   
   static void saveAndExit() {
        File file = new File(FILE_NAME);

        if (file.exists()) {
            System.out.print("Data file already exists. Overwrite? (Y/N): ");
            String confirm = sc.nextLine().trim();
            if (!confirm.equalsIgnoreCase("y")) {
                System.out.println("Save cancelled. Exiting program...");
                return;
            }
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (String student : students) {
                double[] grades = studentGrades.getOrDefault(student.toLowerCase(), new double[]{0, 0, 0});
                writer.println(student + "|" + grades[0] + "," + grades[1] + "," + grades[2]);
            }
            System.out.println("Data saved successfully to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
        System.out.println("Exiting...");
    }
   
   // LOAD DATA
   static void loadData() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 2) {
                    String name = parts[0];
                    students.add(name);
                    
                    String[] gradeStrings = parts[1].split(",");
                    double[] grades = new double[3];
                    for (int i = 0; i < 3; i++) {
                        grades[i] = Double.parseDouble(gradeStrings[i]);
                    }
                    studentGrades.put(name.toLowerCase(), grades);
                }
            }
            System.out.println("Previous data loaded successfully.");
        } catch (IOException | NumberFormatException e) {
            System.out.println("No existing data found or error loading file.");
        }
    }
}