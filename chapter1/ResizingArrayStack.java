package chapter1;

import edu.princeton.cs.introcs.In;

/**
 * Created by zxc on 2015/11/29.
 */
public class ResizingArrayStack<T> {
    private int N;
    private T[] a;
    
    private void resize(int max){
    	T[] b= (T[])new Object[max];
    	for(int i=0; i<N; i++){
    		b[i]= a[i];
    	}
    	a=b;
    }
    
    public ResizingArrayStack(int cap){
        a= (T[])new Object[cap];
    }

    public void push(T s){
        if(N>=a.length/2) resize(a.length*2+1);
    	a[N++]=s;
    }

    public T pop(){
        T result= a[--N];
        a[N]= null;
        if(N<=a.length/4) resize(a.length/2+1);
        return result;
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
        ResizingArrayStack<String> s= new ResizingArrayStack<String>(1);
        for(String str: strs){
            if(!str.equals("-")) s.push(str);
            else if(!str.isEmpty()) System.out.println(s.pop()+" ");
        }
        System.out.println(s.size()+" left in the stack");
    }
}
