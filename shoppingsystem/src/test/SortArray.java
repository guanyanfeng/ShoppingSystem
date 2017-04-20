package test;

import java.util.Arrays;

public class SortArray {
	public static void main(String[] args) {
		int[] a = { 2, 1, 6, 7, 9, 5, 3, 8, 0, 4 };
		// √∞≈›≈≈–Ú
		 buddle(a);
		// —°‘Ò≈≈–Ú
		// choice(a);
		// ≤Â»Î≈≈–Ú
//		insert(a);
		System.out.println(Arrays.toString(a));
//		System.out.println(4&7);
	}

	private static void insert(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int value = arr[i];
			int j = i - 1;
			for (; j >= 0; j--) {
				if (arr[j] > value) {
					arr[j + 1] = arr[j];
				} else {
					break;
				}
			}
			arr[j + 1] = value;
		}
	}

	private static void choice(int[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			for (int j = i + 1; j < a.length; j++) {
				if (a[i] > a[j]) {
					int temp = a[i];
					a[i] = a[j];
					a[j] = temp;
				}
			}
		}
	}

	private static void buddle(int[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			for (int j = 0; j < a.length - i - 1; j++) {
				if (a[j] <a[j + 1]) {
					int n = a[j];
					a[j] = a[j + 1];
					a[j + 1] = n;
				}
			}
		}
	}
	 static final void f(){}
	  

}
