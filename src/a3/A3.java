package a3;
import java.util.*;

public class A3 {

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
             {88,90,92}, //Esteban
             {90,90,92}, // Macabales
             {90,90,92}, // Pacana
             {91,90,92}, // Padil
             {90,95,94}, // Talotalo
             {90,95,95}, // Villamor
         };
         
         // BUBBLE SORT
         int n = names.length;
         for(int i = 0; i < n -1; i++){
             for(int j = 0; j < n - i - 1; j++){
                 
                double avg1 = (grades[j][0] + grades[j][1] + grades[j][2]) / 3.0;
                double avg2 = (grades[j+1][0] + grades[j+1][1] + grades[j+1][2]) / 3.0;
                
                if(avg1 > avg2){
                    String tempName = names[j];
                    names [j] = names[j + 1];
                    names [j + 1] = tempName;
                    
                    double[] tempGradeRow = grades[j];
                    grades[j] = grades[j + 1];
                    grades[j + 1] = tempGradeRow;
                }
             }
         }
         
         
         double[] avg = new double[names.length];
        for (int i = 0; i < names.length; i++) {
            avg[i] = (grades[i][0] + grades[i][1] + grades[i][2]) / 3.0;
        }
         // DISPLAY NAMES AND GRADES
         for (int i = 0; i < names.length; i++){
             
                 System.out.printf("%-30s | %.0f, %.0f, %.0f | average = %.2f | %n", 
                      names[i], grades[i][0], grades[i][1], grades[i][2], avg[i]);
       }
    }
  }