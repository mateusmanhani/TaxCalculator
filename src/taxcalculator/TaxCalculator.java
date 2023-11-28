
package taxcalculator;

import java.util.Scanner;

/**
 *
 * @author Mateus Manhani
 */
public class TaxCalculator {

    /**
     * @param args the command line arguments
     */
    private static final double PAYE_CUTOFF = 40000;
    private static final double USC_BAND1 = 12012;
    private static final double USC_BAND2 = 10908;
    private static final double USC_BAND3 = 47198;
    
    public static void main(String[] args) {
        
        double income = getIncome();
        double usc = calculateUSC(income);
        double prsi = calculatePRSI(income);
        double paye = calculatePaye(income);
        double totalTax = usc+prsi+paye;

        System.out.println("The total amount of tax due is: " + totalTax);
    }
    
    public static double getIncome(){
        double income = 0;
        boolean isValidInput = false;
        Scanner sc = new Scanner(System.in);
        
        while(!isValidInput){
            try{
                System.out.println("Please Enter your salary: ");
                income = sc.nextDouble();
                isValidInput = true;
            }catch(Exception e){
                System.out.println("Invalid salary format.");
                sc.next();
            }
        }
        sc.close();
        
        return income;
    }
    
    public static double calculateUSC(double income) {
        double usc = 0;

        if (income < USC_BAND1) {
            usc = income * 0.005;
        } else {
            usc += USC_BAND1 * 0.005;
            income -= USC_BAND1;
        }

        if (income < USC_BAND2) {
            usc += income * 0.02;
        } else {
            usc += USC_BAND2 * 0.02;
            income -= USC_BAND2;
        }

        if (income < USC_BAND3) {
            usc += income * 0.04;
        } else {
            usc += USC_BAND3 * 0.04;
            income -= USC_BAND3;
            usc += income * 0.08;
        }

        return usc;
    }

    public static double calculatePRSI(double income) {
        return income * 0.04;
    }

    public static double calculatePaye(double income) {
        double paye = 0;

        if (income > PAYE_CUTOFF) {
            double splitSalary = income - PAYE_CUTOFF;
            paye = splitSalary * 0.4;
            paye += PAYE_CUTOFF * 0.2;
        } else {
            paye = income * 0.2;
        }

        return paye;
    }
}
