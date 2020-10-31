package com.toyrentalproject.util;

import java.util.Scanner;

public class KeyboardUtil {
	
	public static int getInt(String string) {
		Scanner sc = new Scanner(System.in);
		System.out.println(string);
		return sc.nextInt();
	}

	public static String getString(String string) {
		Scanner sc = new Scanner(System.in);
		System.out.println(string);
		return sc.nextLine();
	}

	public static char getChar(String string) {
		Scanner sc = new Scanner(System.in);
		System.out.println(string);
		return sc.next().charAt(0);
	}

	public static double getDouble(String string) {
		Scanner sc = new Scanner(System.in);
		System.out.println(string);
		return sc.nextDouble();
	}

}
