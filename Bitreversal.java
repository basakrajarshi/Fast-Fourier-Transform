
/**
 * Write a description of Bitreversal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Bitreversal {
    public static void main (String[] args) throws java.lang.Exception {
        System.out.println(reverser(1));
    }
    public static int reverser(int given) {
        int input = given;
        int temp = 0;
        int output = 0;
        while(input > 0){
            output = output << 1;
            temp = input & 1;
            input = input >> 1;
            output = output | temp;
        }
        return output;
    }
}
