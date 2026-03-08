package a3;
import java.util.*;

public class A3 {
    
    // MAIN METHOD
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // NAMES
         String[] names = {
            "Russel Arcega", 
            "Dwight Dejy Del Coro", 
            "Christian Dolotallas", 
            "Joseph Angelo Dreu", 
            "Enzo Elijah Esteban", 
            "Seanjay Macabales",
            "William Chester Pacana",
            "George Paul Padil",
            "John Mark Ezekiel Talotalo",
            "Kyle Zedrick Villamor"
        };
         
         // GRADES
         double[][] grades = {
             {89,90,92}, // Arcega
             {90,94,97}, // Del Coro
             {94,90,95}, // Dolotallas
             {91,90,92}, // Dreu
             {88,90,92}, // Esteban
             {90,90,92}, // Macabales
             {90,90,92}, // Pacana
             {91,90,92}, // Padil
             {90,95,94}, // Talotalo
             {90,95,95}, // Villamor
         };
         
         // BUBBLE SORT
        bubbleSort(names, grades);

        // DISPLAY NAMES & GRADES
        displayAllStudents(names, grades);
         
        // LOOP
        String choice;
        do {
            System.out.print("\nDo you want to search for a student? (yes/no): ");
            choice = sc.nextLine().trim().toLowerCase();

            if (choice.equals("yes")) {
                performSearch(sc, names, grades);
            } else if (!choice.equals("no")) {
                System.out.println("Invalid input. Please type 'yes' or 'no'.");
            }

        } while (!choice.equals("no"));

        System.out.println("Bye-bye!");
    }

    // FUNCTIONS
    public static void bubbleSort(String[] names, double[][] grades) {
        int n = names.length;
        for(int i = 0; i < n - 1; i++) {
            for(int j = 0; j < n - i - 1; j++) {
                double avg1 = (grades[j][0] + grades[j][1] + grades[j][2]) / 3.0;
                double avg2 = (grades[j+1][0] + grades[j+1][1] + grades[j+1][2]) / 3.0;
                
                if(avg1 > avg2) {
                    // Swap names
                    String tempName = names[j];
                    names[j] = names[j + 1];
                    names[j + 1] = tempName;
                    
                    // Swap grades
                    double[] tempGradeRow = grades[j];
                    grades[j] = grades[j + 1];
                    grades[j + 1] = tempGradeRow;
                }
            }
        }
    }

    public static void displayAllStudents(String[] names, double[][] grades) {
        System.out.printf("%-30s | %-10s | %-15s | %n", "NAME", "GRADES", "AVERAGE");
        System.out.println("--------------------------------------------------------------|");
        for (int i = 0; i < names.length; i++) {
            double avg = (grades[i][0] + grades[i][1] + grades[i][2]) / 3.0;
            System.out.printf("%-30s | %.0f, %.0f, %.0f | average = %.2f | %n", 
                             names[i], grades[i][0], grades[i][1], grades[i][2], avg);
        }
    }

    public static void performSearch(Scanner sc, String[] names, double[][] grades) {
        System.out.print("Enter name to search (min. 3 chars): ");
        String search = sc.nextLine().trim().toLowerCase();

        if (search.length() < 3) {
            System.out.println("Error: Too short.");
            return; 
        }

        boolean isFound = false;
        for (int k = 0; k < names.length; k++) {
            if (names[k].toLowerCase().contains(search)) {
                double avg = (grades[k][0] + grades[k][1] + grades[k][2]) / 3.0;
                System.out.printf("\nMatch found!%n");
                System.out.printf("Name:    %s (List No. %d)%n", names[k], (k + 1));
                System.out.printf("Grades:  %.0f, %.0f, %.0f%n", grades[k][0], grades[k][1], grades[k][2]);
                System.out.printf("Average: %.2f%n", avg);
                isFound = true;
                break; 
            }
        }

        if (!isFound) {
            System.out.println("No matching student found.");
        }
    }
}