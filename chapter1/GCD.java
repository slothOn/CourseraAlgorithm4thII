package chapter1;

import edu.princeton.cs.introcs.In;

import java.util.Scanner;

/**
 * Created by zxc on 2015/11/29.
 */
public class GCD {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("input num:");
        int a = scan.nextInt();
        int b = scan.nextInt();
        System.out.println(GCD(a,b));
    }
    //loop
    public static int gcd(int a, int b){
        int c = a%b;
        while(c!=0){
            a=b;
            b=c;
            c=a%b;
        }
        return b;
    }
    //recursion
    public static int GCD(int a,int b){
        if(b==0) return a;
        int c= a%b;
        return GCD(b,c);
    }
}
