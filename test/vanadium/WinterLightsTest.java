package vanadium;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class WinterLightsTest {

	@Test
	public void test() {
		String testData = "02002";
		System.out.println(getPalindromeCount(testData));
		System.out.println(getPalindromeCount("a"));
	}

	private boolean isStringPalindrome(String s) {
		if (s.isEmpty() || 1 == s.length()) {
			return Boolean.TRUE;
		} else {
			return isStringPalindrome(s.substring(0, s.length() - 1));
		}
	}

	//
	private int getPalindromeCount(String s) {
		int halfLengh = (int) Math.floor((double) s.length() / 2);
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (char c : s.toCharArray()) {
			if (!map.containsKey(c)) {
				map.put(c, 1);
			} else {
				map.put(c, map.get(c) + 1);
			}
		}
		int dividend = getFactorialValue(halfLengh);
		int divisor = 1;
		for (Integer value : map.values()) {
			divisor *= getFactorialValue((int) Math.floor((double) value / 2));
		}
		return dividend / divisor;

	}

	private int getFactorialValue(int n) {
		assert n >= 0;
		if (n == 0) {
			return 1;
		} else {
			return getFactorialValue(n - 1) * n;
		}
	}

	@Test
	public void testFactory() {
		System.out.println(getFactorialValue(3));
	}

	private boolean isPalindrome(String s) {
		if (s.length() == 1) {
			return Boolean.TRUE;
		}
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (char c : s.toCharArray()) {
			if (!map.containsKey(c)) {
				map.put(c, 1);
			} else {
				map.put(c, map.get(c) + 1);
			}
		}
		boolean isPalindrome = Boolean.TRUE;
		if (s.length() % 2 == 0) {
			for (int value : map.values()) {
				isPalindrome &= ((value % 2) == 0);
			}
		} else if (s.length() % 2 != 0) {
			
			for (int value : map.values()) {
				isPalindrome &= ((value % 2) == 0);
			}
		}
		return isPalindrome;
	}

	private int countPalindromeSubsequence(String s) {
		int count = 0;
		for (int subStrLength = 1; subStrLength <= s.length(); subStrLength++) {
			for (int j = 0; j + subStrLength <= s.length(); j++) {
				if (isPalindrome(s.substring(j, j + subStrLength)))
					count++;
			}
		}
		return count;
	}

	@Test
	public void testCountPalidromeSubsequence() {
		String testData = "02002";
		System.out.println(countPalindromeSubsequence(testData));
	}
}
