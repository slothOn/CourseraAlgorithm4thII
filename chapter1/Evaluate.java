package chapter1;

/**
 * Created by zxc on 2015/11/29.
 */
// ( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )
// ( ( 1 + sqrt ( 5.0 ) ) / 2.0 )
public class Evaluate {
    public static void main(String[] args){
    	String[] args1="( ( 1 + sqrt ( 5.0 ) ) / 2.0 )".split(" ");
    	Stack<String> opr =new Stack<String>();//operator:+,-,*,/,sqrt
    	Stack<Double> val =new Stack<Double>();//num
    	double a,b;
    	for(String s: args1){
    		switch(s){
    		case "+":
    		case "-":
    		case "*":
    		case "/":
    		case "sqrt":
    			opr.push(s);
    			break;
    		case "(": break;
    		case ")": 
    			String nopr=opr.pop();
    			switch (nopr) {
    			case "+":
    				b=val.pop(); a=val.pop(); val.push(a+b);
    				break;
        		case "-":
        			b=val.pop(); a=val.pop(); val.push(a-b);
    				break;
        		case "*":
        			b=val.pop(); a=val.pop(); val.push(a*b);
    				break;
        		case "/":
        			b=val.pop(); a=val.pop(); val.push(a/b);
    				break;
				case "sqrt":
					a=val.pop(); val.push(Math.sqrt(a));
				default:
					break;
				}
    			break;
    		default:
    			val.push(Double.valueOf(s));
    			break;
    		}
    	}
    	System.out.println(val.pop());
    }

}
