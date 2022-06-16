//Chetan Goyal
import java.util.*;
public class Complexity {

    public static void method1(int n) {
        int sampleArray[] = new int[n];
        int counter = 0;
        for (int i = 0; i < sampleArray.length; i++) {
            for (int j = 0; j < sampleArray.length; j++) {
                counter++;
                System.out.println("Operation " + counter);
            }
        }
    }

    public static void method2(int n) {
        int sampleArray[] = new int[n];
        int counter = 0;
        for (int i = 0; i < sampleArray.length; i++) {
            for (int j = 0; j < sampleArray.length; j++) {
                for (int k = 0; k < sampleArray.length; k++) {
                    counter++;
                    System.out.println("Operation " + counter);
                }
            }
        }
    }

    public static void method3(int n) {
        double sampleArray[] = new double[n];
        int counter = 0;
        for (int i = 0; i < sampleArray.length; i++) {
            sampleArray[i] = (Math.random() * 10) + 51;
        }
        int high = n-1;
        int low = 0;
        int mid = 0;
        while (high >= low) {
            mid = (low + high) / 2;
            if (sampleArray[mid] > 50) {
                low = mid + 1;
                counter++;
                System.out.println("Operation " + counter);
            }
            else if (sampleArray[mid] < 50) {
                high = mid - 1;
                counter++;
                System.out.println("Operation " + counter);
            }
            else {
                counter++;
                System.out.println("Operation " + counter);
                break;
            }
        }
    }

    public static void method4(int n) {
        double sampleArrayForN[] = new double[n];
        double sampleArrayForLogn[] = new double[n];
        int counter = 0;
        for (int i = 0; i < n; i++) {
            sampleArrayForLogn[i] = (Math.random() * 10) + 51;
            sampleArrayForN[i] = (Math.random() * 10);
        }
        int high = n-1;
        int low = 0;
        int mid = 0;
        while (high >= low) {
            mid = (low + high) / 2;
            if (sampleArrayForLogn[mid] > 50) {
                for (int i = 0; i < sampleArrayForN.length; i++) {
                    counter++;
                    System.out.println("Operation " + counter);
                }
                low = mid + 1;
                counter++;
                System.out.println("Operation " + counter);
            }
            else if (sampleArrayForLogn[mid] < 50) {
                for (int i = 0; i < sampleArrayForN.length; i++) {
                    counter++;
                    System.out.println("Operation " + counter);
                }
                high = mid - 1;
                counter++;
                System.out.println("Operation " + counter);
            }
            else {
                for (int i = 0; i < sampleArrayForN.length; i++) {
                    counter++;
                    System.out.println("Operation " + counter);
                }
                counter++;
                System.out.println("Operation " + counter);
                break;
            }
        }
    }

    public static void method5(int n) {
        double sampleArray[] = new double[n];
        int counter = 0;
        for (int i = 0; i < sampleArray.length; i++) {
            sampleArray[i] = (Math.random() * 10);
        }
        int high = n-1;
        int low = 0;
        int mid = 0;
        while (high >= low) {
            mid = (int) Math.pow((low + high), 0.5);
            if (sampleArray[mid] > 50) {
                low = mid + 1;
                counter++;
                System.out.println("Operation " + counter);
            }
            else if (sampleArray[mid] < 50) {
                high = mid - 1;
                counter++;
                System.out.println("Operation " + counter);
            }
            else {
                counter++;
                System.out.println("Operation " + counter);
                break;
            }
        }
    }

    public static int counter = 0; //declaring counter for method 6 implementation
    public static int method6(int n) {
        if (n == 1) {
            counter++;
            System.out.println("Operation " + counter);
            return counter;
        }
        counter++;
        System.out.println("Operation " + counter);
        method6(n-1);
        method6(n-1);
        return counter;
    }
/*
    public static void main(String[] args) {
        Complexity object = new Complexity();
        object.method1(5);
        object.method2(5);
        object.method3(5);
        object.method4(5);
        object.method5(5);
        object.method6(5);
    }
*/
}