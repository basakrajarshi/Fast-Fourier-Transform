/**
 * Write a description of FFT2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.lang.*;
import java.util.*;

import javax.swing.plaf.FontUIResource;

//import edu.duke.*;
import java.math.*;

public class FFT2 {
	public static long fourier(double[] signal) {
        long startTime = System.currentTimeMillis();
	    int l = signal.length; //Length of the input signal array which should be a power of 2
        Complex[] signalcomp = new Complex[l];//Creates a new array 'signalcomp' of length l by calling the java class Complex
        for (int i=0; i<l; i++){
           signalcomp[i] = new Complex(signal[i],0);//Assigns the values of the input array 'signal' to the real parts of the Comlpex array 'signalcomp', the imaginary parts are all zero
        }
        Complex[] output = new Complex[l];//Creates a new array 'output' by calling thr java class Complex to store the fourier transform of the input array 
        for (int i=0; i<l; i++){// Initializes thehe values of the 'output' array to zeros (both the real and imaginary parts)
           output[i]=new Complex(0,0);
        }
        double st1 = Math.log(l);
        double st2 = Math.log(2);
        double sta = st1/st2; //Calculates and stores the number of stages in the butterfly proceedure for calculation of the fft
        int st = (int) sta; //Stores the number of stages in the variable 'st'
        int j = 0;
        for (int i = st; i >= 1; i--) {// i runs from 1 to number of stages, backwards
            double xes = Math.pow(2,(i - 1));
            int xs = (int) xes;// variable 'xs' stores the number of butterflies for the ith stage
            j=0;
            while (j <= l-1) {// j runs through the length of the signal (from 0 to l-1)
                for (int k = 0; k <= xs - 1; k++) {//k runs through the number of butterflies for the ith stage as long j is less than the length of the input signal
                    output[j].update(signalcomp[j].plus(signalcomp[j+xs]));//updates the jth entry of the 'output' array to signal[j] + signal[j + xs];
                    Complex c = new Complex(Math.cos(-2*3.1416*(k)*Math.pow(2,st-i)/l), Math.sin(-2*3.1416*(k)*Math.pow(2,st-i)/l));//calculates the twiddle factor for the (j + xs)th entry 
                    output[j+xs].update((signalcomp[j].minus(signalcomp[j+xs])).times(c));//updates the (j + xs)th entry of the 'output' array to (signal[j] - signal[j + xs])*(twiddle factor)
                    j++;
                    if (k==xs-1){
                        j = j + xs;//When k == number os xes at each stage, update j to j + number of xs 
                    }
                } 
            } 
            for (int s = 0; s < l; s++) {
                signalcomp[s].update(output[s]);//Updates the 'signalcomp' array to the 'output' array at the end of each stage
            }
        }
        /*System.out.println("The FFT of the input signal is");
        int maxBase = st;
        for (int s = 0; s < l; s++) {//reverses the bits of the binary representation of the 's'th index of the 'output' array
        		String binaryStringS = Integer.toBinaryString(s);//Converts the integer s to its binary equivalent and stores it in the string binaryStringS
        		String paddedString = padWithZeros(maxBase,binaryStringS);//pads the string binaryStringS with the requisite number of zeros by calling the method padWtihZeros
        		String reversedBinaryS = new StringBuilder(paddedString).reverse().toString();//reverses the bits of paddedString and stores it in string reversedBinaryS 
        		int reversedS = Integer.parseInt(reversedBinaryS, 2);//converts the string reversedBinaryS to the decimal representation and stores it in the integer reversedS
        		System.out.println(signalcomp[reversedS]);//prints the output signal
        }*/
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        return totalTime;
    }
    public static String padWithZeros(int m, String inString){//pads the string binaryStringS with the requisite number of zeros
		String resString = "";
		for (int i= inString.length(); i < m ; i++)
			resString += "0";
		return resString+inString;
	}
	
	public static double[] randominputgen(int length, int times) {
	    long start = System.currentTimeMillis();
	    long grandtime = 0;
	    int i = 0;
	    int j = 0;
	    int n = 0;
	    int k = 0;
	    double st1 = Math.log(length);
        double st2 = Math.log(2);
        double sta = st1/st2; //Calculates and stores the number of stages in the butterfly proceedure for calculation of the fft
        int st = (int) sta;
        int maxBase = st;
	    Random rand = new Random();
	    double[] input = new double[length];
	    Complex[] fourierout = new Complex[length];
	    for (i = 0; i < times; i++) {
	        for (j = 0; j < length; j++) {
	            n = rand.nextInt(1000) + 1;
	            //System.out.println(n);
	            input[j] = n;
	        }
	        //fourierout = fourier(input);
	        grandtime = grandtime + fourier(input);
	        /*for (k = 0; k < length; k++) {
	            String binaryStringS = Integer.toBinaryString(k);//Converts the integer s to its binary equivalent and stores it in the string binaryStringS
        		String paddedString = padWithZeros(maxBase,binaryStringS);//pads the string binaryStringS with the requisite number of zeros by calling the method padWtihZeros
        		String reversedBinaryS = new StringBuilder(paddedString).reverse().toString();//reverses the bits of paddedString and stores it in string reversedBinaryS 
        		int reversedS = Integer.parseInt(reversedBinaryS, 2);//converts the string reversedBinaryS to the decimal representation and stores it in the integer reversedS
        		System.out.println(fourierout[reversedS]);
	            //System.out.println(fourierout[k]);
	        }*/
	    }
	    
	    
	    //long end   = System.currentTimeMillis();
	    //long totalTime = end - start;
	    //double totalt = totalTime/1000;
	    System.out.println(grandtime/times + "milliseconds");
	    System.out.println(grandtime + "milliseconds");
	    //for () {
	    //}
	    /*for (int k = 0; k < length; k++)  {
	        System.out.println(input[k]);
	        
	    }*/
	    return input;
	}
	
	//public static 
}
