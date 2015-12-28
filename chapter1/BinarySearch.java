package chapter1;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

import java.util.Arrays;

/**
 * Created by zxc on 2015/11/29.
 */
public class BinarySearch {
    public static void main(String[] args){
        int[] whitelist = In.readInts("E:\\intellij\\src\\Chapter1\\tinyW.txt");
        Arrays.sort(whitelist);
        while(!StdIn.isEmpty()){
            int key=StdIn.readInt();
            if(RANK(key, whitelist)<0)
                StdOut.println(key);
        }
    }
    //loop
    public static int rank(int key, int[] a){
        int lo=0, hi=a.length-1;
        int mid;
        while(lo<=hi){
            mid=(lo+hi)/2;
            if(a[mid]==key) return mid;
            else if(a[mid]<key) lo=mid+1;
            else hi=mid-1;
        }
        return -1;//no return negative
    }

    //recursion
    public static int RANK(int key, int[] a){
        return rankr(key, a, 0, a.length-1);
    }

    public static int rankr(int key, int[] a, int lo, int hi){
        if(lo<=hi){
            int mid=(lo+hi)/2;
            if(a[mid]==key) return mid;
            else if(a[mid]<key) return rankr(key, a, mid+1, hi);
            else return rankr(key, a, lo, mid-1);
        }
        return -1;
    }
}
