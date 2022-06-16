//Name - Chetan Goyal
import java.util.Scanner;
import java.util.Arrays;
import java.lang.*;
public class BinaryNumber {
    //Declaring data and overflow in BinaryNumberClass
    private int data[];
    private boolean overflow = false;

    //Creating a binary number of length- 'length' and consisting only of zeroes
    public BinaryNumber(int length) {
        if (length <= 0) {
            System.out.println("Kindly provide an integral value greater than 0"); //Handling illegal input and terminating the program
            System.exit(-1);
        }
        data = new int[length];
        for (int i = 0; i < length; i++) {
            data[i] = 0;
        }
        //System.out.println(Arrays.toString(data));
    }

    //Converting a string to a binary number
    public BinaryNumber(String str) {
        int stringLength = str.length();
        data = new int[stringLength];
        //System.out.println(str);
        for (int i = 0; i < stringLength; i++) {
            if (str.charAt(i) != '0' && str.charAt(i) != '1') {
                System.out.println("Incorrect String entered. The string should only contain 0 and 1");  //Handling illegal input and terminating the program
                System.exit(-1);
            }
            else data[i] = Character.getNumericValue(str.charAt(i));
        }
    }

    //Operation to get length of the binary number
    public int getLength() {
        return data.length;
    }

    //Operation to get digit via index of a binary number
    public int getDigit(int index) {
        if (index > data.length-1 || index < 0) {
            System.out.println("The index entered is out of bounds.");
            System.exit(-1);
        }
        return data[index];
    }

    //Operation to get decimal value of a binary number
    public int decimal() {
        int decimalValue = 0;
        for (int i = 0; i < data.length; i++){
            decimalValue += data[i] * Math.pow(2, i); 
        }
        return decimalValue;
    }

    //Operation to shift all digits of a binary number to the right by 'amount'
    public void shiftR(int amount) {
        if (amount < 0) {
            System.out.println("Enter a valid non negative integer for shifting the binary number to the right."); //Handling illegal input and then terminating the program
            System.exit(-1);
        }
        int shiftedBinary[] = new int[amount + data.length];
        for (int i = 0; i < amount; i++) {
            shiftedBinary[i] = 0;
        }
        for (int i = 0; i < data.length; i++) {
            shiftedBinary[amount + i] = data[i];
        }
        data = shiftedBinary;
    }

    //Addition of binary numbers
    public void add(BinaryNumber aBinaryNumber) {
        int carry = 0;
        if (this.data.length != aBinaryNumber.getLength()) {
            System.out.println("The length of the two binary numbers is not the same. Please provide similar length Binary Numbers.");  //Handling illegal input and terminating the program
            System.exit(-1);
        }
        int summedBinary[] = new int[aBinaryNumber.getLength()];
        for (int i = 0; i < aBinaryNumber.getLength(); i++) { //checking all the conditions for binary addition and then progressing with creation of added binary number
            if (this.data[i] == 0 && aBinaryNumber.data[i] == 0 && carry == 0) {
                summedBinary[i] = 0;
            }
            else if ((this.data[i] == 0 && aBinaryNumber.data[i] == 0 && carry == 1) || (this.data[i] == 1 && aBinaryNumber.data[i] == 0 && carry == 0) || (this.data[i] == 0 && aBinaryNumber.data[i] == 1 && carry == 0)) {
                summedBinary[i] = 1;
                carry = 0;
            }
            else if ((this.data[i] == 1 && aBinaryNumber.data[i] == 1 && carry == 0) || (this.data[i] == 0 && aBinaryNumber.data[i] == 1 && carry == 1) || (this.data[i] == 1 && aBinaryNumber.data[i] == 0 && carry == 1)) {
                summedBinary[i] = 0;
                carry = 1;
            }
            else if (this.data[i] == 1 && aBinaryNumber.data[i] == 1 && carry == 1) {
                summedBinary[i] = 1;
            }
            else System.out.println("Incorrect binary numbers entered. Please provide binary numbers of same length for addition"); // Handling any incorrect binary number entered
        }
        if (carry == 1) { 
            this.overflow = true; //setting overflow to true when and if overflow happens in above addition
            int binarySizeIncrease[] = new int[summedBinary.length + 1]; // a dummy value to increase the size of array
            for (int i = 0; i < summedBinary.length; i++) { // increasing the size of dummy binary number by 1 so that the overflow can be accomodated
                binarySizeIncrease[i] = summedBinary[i];
            }
            binarySizeIncrease[summedBinary.length] = 1;
            summedBinary = binarySizeIncrease;
        }
        this.data = summedBinary; // changing the binary number of the referenced to the added binary number
    }

    //Eliminating the overflow flag
    public void clearOverflow() {
        this.overflow = false;
    }

    //Converting binary number to a string
    public String toString() {
        if (this.overflow == true) {
            return ("Overflow"); //Printing Overflow when overflow occurs
        }
        String binaryString = "";
        for (int i = 0; i < this.data.length; i++) {
            if (this.data[i] == 0) {
                binaryString += '0';
            }
            else if (this.data[i] == 1) {
                binaryString += '1';
            }
            else System.out.println("Incorrect Binary Number entered"); //Handling possibility of error in binary number during string conversion
        }
        return binaryString;
    }


    //
    public static void main(String[] args) {
        //declaring three binary numbers to check all the methods written
        BinaryNumber binary_one = new BinaryNumber(5);
        BinaryNumber binary_two = new BinaryNumber("10011");
        BinaryNumber binary_three = new BinaryNumber("11111");

        //Used all the methods on the declared binary numbers below
        binary_two.getLength();
        binary_two.getDigit(3);
        binary_two.decimal();
        binary_one.shiftR(3);
        binary_three.add(binary_two);
        binary_three.toString();
        binary_three.clearOverflow();
        binary_three.toString();
    }
}