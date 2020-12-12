
//for testing only
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Arrays;

public class LL1 {
	//keeps track of tokens, number values are swapped with 'n'
	static char[] tokens;
	static char lookAhead;

	//int to keep track of location in expression
	static int count = 0;
	static boolean valid = true;
	
	public static void main(String[] args) {
		
		String expression;
		expression=args[0];
		tokenize(expression);
		
		//set lookahead to first char in the tokens arr
		lookAhead=tokens[count];
		//call starting symbol function
		E();
		if(lookAhead=='$' && valid) {
			System.out.println("Valid");
		}
		else{
			System.out.println("Invalid Expression");
		}
	}

	//fills char array with tokens from the string expression
	public static void tokenize(String expression) {
		String tempStr;
		int i=0;
		int k=0;
		float tempF;
		StringTokenizer st = new StringTokenizer(expression, "+-*/()",true);
		//initialize tokens array
		tokens = new char[st.countTokens()+1];
		
		while(st.hasMoreTokens()) {
			tempStr=st.nextToken();
			//if token is numeric store in numbers, and put n in tokens
			try{
				tempF = Float.parseFloat(tempStr);
				//numbers[k]=tempF;
				tokens[i]='n';
				k++;
			}catch(NumberFormatException error) {
				tokens[i]=tempStr.charAt(0);
			}
			
			i++;
		}
		//put $ at end of tokens arr
		tokens[i]='$';
	}
	
	/**
	 *Below are variable functions 
	**/
	
	public static void E() {
		//apply rule 1
		if(lookAhead=='n' || lookAhead=='(') {
			T();
			EPrime();
		}
		else {
			valid=false;
		}
	}
	
	public static void EPrime() {
		//apply rule 2/3
		if(lookAhead=='+' ||lookAhead=='-') {
			get(lookAhead);
			T();
			EPrime();
		}
	}
	
	public static void T() {
		//apply rule 5
		if(lookAhead=='n' || lookAhead=='(') {
			F();
			TPrime();
		}
		else {
			valid=false;
		}
	}
	
	public static void TPrime() {
		//apply rule 6/7
		if(lookAhead=='*' || lookAhead=='/') {
			get(lookAhead);
			F();
			TPrime();
		}
	}
	
	public static void F() {
		//apply rule 9
		if(lookAhead=='(') {
			get(lookAhead);
			E();
			if(lookAhead==')') {
				get(lookAhead);
			}
			else {
				valid=false;
			}
		}
		//apply rule 10
		else if(lookAhead=='n') {
			get(lookAhead);
		}
		else {
			valid=false;
		}
	}
	
	public static void get(char ch) {
		if(lookAhead==ch) {
			lookAhead=tokens[++count];
		}
		
	}
	
}

