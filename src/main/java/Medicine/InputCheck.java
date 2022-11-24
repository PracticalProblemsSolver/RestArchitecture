package Medicine;
import java.util.Scanner;

public class InputCheck {
    static int getInt(double leftBorder){
        Scanner scn = new Scanner(System.in);
        int Value;
        if (scn.hasNextInt()){
            Value = scn.nextInt();
            if (leftBorder < Value){
                return Value;
            }
        }
        System.out.println("Enter an integer greater than " + leftBorder);
        return getInt(leftBorder);
    }
    static int getInt(double leftBorder, double rightBorder){
        Scanner scn = new Scanner(System.in);
        int Value;
        if (scn.hasNextInt()){
            Value = scn.nextInt();
            if (leftBorder < Value && Value < rightBorder){
                return Value;
            }
        }
        System.out.println("Enter an integer between " + leftBorder + " and " + rightBorder);
        return getInt(leftBorder, rightBorder);
    }
    static int getInt(){
        Scanner scn = new Scanner(System.in);
        int Value;
        if (scn.hasNextInt()){
            Value = scn.nextInt();
            return Value;
        }
        System.out.println("Enter an integer");
        return getInt();
    }
    static double getDouble(double leftBorder){
        Scanner scn = new Scanner(System.in);
        double Value;
        if (scn.hasNextDouble()) {
            Value = scn.nextDouble();
            if (leftBorder < Value) {
                return Value;
            }
        }
        System.out.println("Enter a floating point number greater than " + leftBorder);
        return getDouble(leftBorder);
    }
    static double getDouble(double leftBorder, double rightBorder){
        Scanner scn = new Scanner(System.in);
        double Value;
        if (scn.hasNextDouble()) {
            Value = scn.nextDouble();
            if (leftBorder < Value && Value < rightBorder) {
                return Value;
            }
        }
        System.out.println("Enter a floating point number between "
                + leftBorder + " and " + rightBorder);
        return getDouble(leftBorder, rightBorder);
    }
    static double getDouble(){
        Scanner scn = new Scanner(System.in);
        double Value;
        if (scn.hasNextDouble()){
            Value = scn.nextDouble();
            return Value;
        }
        System.out.println("Enter a floating point number");
        return getDouble();
    }
    static String getString(){
        Scanner scn = new Scanner(System.in);
        String newString;
        if (scn.hasNextLine()){
            newString = scn.nextLine();
            return newString;
        }
        System.out.println("Enter a string");
        return getString();
    }
}
