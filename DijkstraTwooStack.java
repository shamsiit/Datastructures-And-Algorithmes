package Algo;

import java.util.Scanner;

import DS.Stack;

public class DijkstraTwooStack {

	public static void main(String args[]){
		System.out.println("Enter the expression : ");
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		char [] arr = s.toCharArray();
		Stack<String> ops = new Stack<String>();
		Stack<Double> vals = new Stack<Double>();
		for(int i=0;i<arr.length;i++){
			if(arr[i] == '('){
				
			}else if(arr[i] == '+'){
				ops.push(""+arr[i]);
			}else if(arr[i] == '-'){
				ops.push(""+arr[i]);
			}else if(arr[i] == '*'){
				ops.push(""+arr[i]);
			}else if(arr[i] == '/'){
				ops.push(""+arr[i]);
			}else if(arr[i] == ')'){
				String op = ops.pop();
				Double v = Double.parseDouble(""+vals.pop());
				
				if(op.equals("+")){
					v = v + Double.parseDouble(""+vals.pop());
				}else if(op.equals("-")){
					v = v - Double.parseDouble(""+vals.pop());
				}else if(op.equals("*")){
					v = v * Double.parseDouble(""+vals.pop());
				}else if(op.equals("/")){
					v = v / Double.parseDouble(""+vals.pop());
				}
				
				vals.push(v);
			}else{
				vals.push(Double.parseDouble(""+arr[i]));
			}
		}
		
		System.out.println("Result : "+vals.pop());
	}
}
