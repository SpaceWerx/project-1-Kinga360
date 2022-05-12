package com.revature;
import java.util.*;

public class Driver {

	public static void main(String[] args) {
		int[] a = {1,2,3,4,5};
		for (int i = 0; i < a.length; i++) {
			if(a[i]%2!=1){
				System.out.println(a[i]);
			
		}
	}
		List<String> names = new ArrayList<>();
		names.add("Alice");
		names.add("Bob");
		names.add("Charlie");
		names.forEach(str -> System.out.println(str));	
		boolean yes = true;
		Scanner scan = new Scanner(System.in);
		String answer;
		while (yes == true) {
			
			System.out.println("yes?");
			answer = scan.next();
			if (answer.equals("no")) {
				break;
			}
		}
	}
}
