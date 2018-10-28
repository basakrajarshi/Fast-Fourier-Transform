
/**
 * Write a description of FFT2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.lang.*;
import java.util.*;
//import edu.duke.*;
import java.math.*;

public class FFT2 {
    public void fourier(double[] signal) {
        //double[] signal = {1,2,3,4,5,6,7,8};
        //System.out.println(signal[1]);
        int l = signal.length; //Length of the input signal array which should be a power of 2
        Complex[] signal_comp = new Complex[l];
        for (int i=0; i<l; i++){
           signal_comp[i] = new Complex(signal[i],0);
        }
        Complex[] output = new Complex[l];
        for (int i=0; i<l; i++){
           output[i]=new Complex(0,0);
        }
        Complex[] output_copy = new Complex[l];
        double st1 = Math.log(l);
        double st2 = Math.log(2);
        double sta = st1/st2; //Calculates and stores the number of stages in the butterfly proceedure for calculation of the fft
        int st = (int) sta; 
        int j = 0;
        for (int i = st; i >= 1; i--) {
            double xes = Math.pow(2,(i - 1));
            int xs = (int) xes;
            j=0;
            while (j <= l-1) {
                for (int k = 0; k <= xs - 1; k++) {
                    output[j].update(signal_comp[j].plus(signal_comp[j+xs]));//signal[j] + signal[j + xs];
                    Complex c = new Complex(Math.cos(-2*3.1416*(k)*Math.pow(2,st-i)/l), Math.sin(-2*3.1416*(k)*Math.pow(2,st-i)/l));
                    output[j+xs].update((signal_comp[j].minus(signal_comp[j+xs])).times(c));
                    j++;
                    if (k==xs-1){
                        j = j + xs;//When k == number os xes at each stage, update j to j + number of xes + 1 
                    }
                } 
            }
            for (int s = 0; s <= l-1; s++) {
                signal_comp[s].update(output[s]); 
                System.out.println("Printing for stage"+i+":"+signal_comp[s]);
            }
        }
        System.out.println("The FFT of the input signal is");
        for (int s = 0; s <= l-1; s++) {
                System.out.println(signal_comp[s]);
            }
    }
}
