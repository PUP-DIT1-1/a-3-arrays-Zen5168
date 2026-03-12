package a3;
import java.util.*;

public class A3 {
    
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int numStudents = 10;
        String[] names = new String[numStudents];
        double[][] grades = new double[numStudents][3];
        String[] subjects = {"Mathematics", "English", "Science"};

        System.out.println("--- Enter Details for " + numStudents + " Students ---");
        System.out.println("----------------------------------------");
        System.out.println("     The grading system is 0 - 100   "); 
        System.out.println("----------------------------------------\n");
        
        for (int i = 0; i < numStudents; i++) {
            System.out.print("Enter name for student " + (i + 1) + ": ");
            names[i] = sc.nextLine();
      
            for (int j = 0; j < 3; j++) {
                grades[i][j] = getValidatedGrade(subjects[j], names[i]);
            }
            System.out.println();
        }

        bubbleSort(names, grades);
        displayAllStudents(names, grades);

        String choice;
        do {
            System.out.print("\nDo you want to search for a student? (yes/no): ");
            choice = sc.nextLine().trim().toLowerCase();

            if (choice.equals("yes")) {
                Search(sc, names, grades);
            } else if (!choice.equals("no")) {
                System.out.println("Invalid input. Please type 'yes' or 'no'.");
            }          
        } while (!choice.equals("no"));
        
        System.out.println("Bye-bye!");
    }

    // METHOD FOR GRADE VALIDATION
    public static double getValidatedGrade(String subject, String studentName) {
        double grade;
        do {
            System.out.print("Enter grade in " + subject + " for " + studentName + ": ");
            grade = getDoubleInput();

            if (grade > 100) {
                System.out.println("Grade can't be greater than 100. Try again.");
            } else if (grade < 0) {
                System.out.println("Grade can't be less than 0. Try again.");
            }
        } while (grade < 0 || grade > 100);
        
        return grade;
    }

    // INPUT VALIDATION
    static double getDoubleInput(){
        while(true){
            try{
                double input = sc.nextDouble();
                sc.nextLine(); 
                return input;
            } catch (InputMismatchException e) {
                String badInput = sc.nextLine();
                System.out.println("-------------------------------------------------------");
                System.out.println(badInput + " is not a number!");
                System.out.println("Please Try Again!");
                System.out.print("> "); 
            }   
        }
    }

    // BUBBLE SORT
    public static void bubbleSort(String[] names, double[][] grades) {
        int n = names.length;
        for(int i = 0; i < n - 1; i++) {
            for(int j = 0; j < n - i - 1; j++) {
                double avg1 = (grades[j][0] + grades[j][1] + grades[j][2]) / 3.0;
                double avg2 = (grades[j+1][0] + grades[j+1][1] + grades[j+1][2]) / 3.0;
                if(avg1 > avg2) {
                    String tempName = names[j]; 
                    names[j] = names[j + 1]; 
                    names[j + 1] = tempName;
                    double[] tempGradeRow = grades[j]; 
                    grades[j] = grades[j + 1]; 
                    grades[j + 1] = tempGradeRow;
                }
            }
        }
    }

    public static void displayAllStudents(String[] names, double[][] grades) {
        System.out.printf("\n%-35s | %-10s | %-15s | %n", "NAME", "GRADES", "AVERAGE");
        System.out.println("--------------------------------------------------------------|");
        for (int i = 0; i < names.length; i++) {
            double avg = (grades[i][0] + grades[i][1] + grades[i][2]) / 3.0;
            System.out.printf("%-35s | %.0f, %.0f, %.0f | average = %.2f | %n", 
                             names[i], grades[i][0], grades[i][1], grades[i][2], avg);
        }
    }
    
    // LINEAR SEARCH
    public static void Search(Scanner sc, String[] names, double[][] grades) {
        System.out.print("Enter name to search (min. 3 chars): ");
        String search = sc.nextLine().trim().toLowerCase();
        if (search.length() < 3) { System.out.println("Too short! Please try again."); return; }

        boolean isFound = false;
        for (int k = 0; k < names.length; k++) {
            
            if (names[k].toLowerCase().contains(search)) {
                double avg = (grades[k][0] + grades[k][1] + grades[k][2]) / 3.0;
                System.out.printf("\nStudent Found!%n" );
                System.out.printf("Name:    %s (List No. %d)%n", names[k], (k + 1));
                System.out.printf("Grades:  %.0f, %.0f, %.0f%n", grades[k][0], grades[k][1], grades[k][2]);
                System.out.printf("Average: %.2f%n", avg);
                System.out.printf("Rank: %d %n", (names.length - k));
                isFound = true;
                break; 
            }
        }

        if (!isFound) {
            System.out.println("No matching student found.");
        }
    }
}