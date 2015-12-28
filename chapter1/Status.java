package chapter1;

import java.util.Scanner;

import edu.princeton.cs.introcs.StdIn;

/**
 * Created by zxc on 2015/11/29.
 */
public class Status {
    public static void main(String[] args) {
        Bag<Double> bags = new Bag<Double>();
        Scanner scanner= new Scanner(System.in);
        while (scanner.hasNext()) {
            double num=scanner.nextDouble();
            bags.add(num);
        }
        scanner.close();
        System.out.println("a");
        double sum=0.0;
        for(double n: bags){
            sum+=n;
        }
        int N=bags.size();
        double mean=sum/N;

        sum=0.0;
        for(double n:bags){
            sum+=(n-mean)*(n-mean);
        }
        double std=Math.sqrt(sum/(N-1));
        System.out.printf("%.2f\n", mean);
        System.out.printf("%.2f\n", std);
    }
}
