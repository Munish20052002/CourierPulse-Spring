package com.project.Springframework.beans;

import java.util.*;

public class CombinationGenerator {
	
	
	public static void main(String[] args) {
		char[] charArray = { 'H', 'A', 'C', 'K' };
		int n = 3;

		List<String> combinations = new ArrayList<>();

		for (int size = 1; size <= n; size++)
			backtrack(combinations, new StringBuilder(), charArray, size, 0);
		System.out.println(combinations);
	}

	private static void backtrack(List<String> combinations, StringBuilder current, char[] charArray, int n,
			int start) {
		if (current.length() == n) {
			combinations.add(current.toString());
			return;
		}

		for (int i = start; i < charArray.length; i++) {
			current.append(charArray[i]);
			backtrack(combinations, current, charArray, n, i + 1);
			current.deleteCharAt(current.length() - 1);
		}
	}
}