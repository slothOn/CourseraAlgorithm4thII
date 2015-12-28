package chapter1;

import edu.princeton.cs.introcs.In;

/**
 * Created by zxc on 2015/11/29.
 */
public class FixedCapacityStackOfString {
    private String[] a;
    private int N;
    public FixedCapacityStackOfString(int cap){
        a= new String[cap];
        N=0;
    }

    public void push(String s){
        a[N++]=s;
    }

    public String pop(){
        return a[--N];
    }

    public boolean isEmpty(){
        return N==0;
    }

    public int size(){
        return N;
    }

    public static void main(String[] args){
        In in = new In("E:\\jwork\\algs4-data\\tobe.txt");
        String[] strs= in.readAllStrings();
        FixedCapacityStackOfString s= new FixedCapacityStackOfString(100);
        for(String str: strs){
            if(!str.equals("-")) s.push(str);
            else if(!str.isEmpty()) System.out.println(s.pop()+" ");
        }
        System.out.println(s.size()+" left in the stack");
    }
}
