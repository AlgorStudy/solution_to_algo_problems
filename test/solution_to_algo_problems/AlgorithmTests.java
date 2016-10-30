package solution_to_algo_problems;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

public class AlgorithmTests {
	// 4
	@Test
	public void test() {
		System.out.println(Math.sqrt(4));
		System.out.println(Math.sqrt(5));
		System.out.println(Math.sqrt(1));
		System.out.println(Math.sqrt(-9));
		System.out.println(Math.sqrt(0));
		System.out.println(Math.sqrt(0));
		int k = (int) Math.sqrt(5);
		int j = (int) Math.sqrt(4);
		assertFalse(5 == k * k);
		assertTrue(4 == j * j);
	}

	@Test
	public void testStringSubstitution() {
		// String testCase = "ABBCC";
		String testCase = "AAAAA";
		Random random = new Random(47);
		String previousSubstituedString = "";
		do {
			previousSubstituedString = substituteStrinbByRule(testCase,
					(int) (Math.abs(random.nextLong()) % 6));
			if (!"".equalsIgnoreCase(previousSubstituedString)) {
				testCase = previousSubstituedString;
			} else {
				break;
			}
		} while (true);
		System.out.printf("%s\n", testCase);
	}

	private String substituteStrinbByRule(String S, int ruleNumber) {
		switch (ruleNumber) {
		case 0:
			if (S.contains("AB")) {
				return S.replace("AB", "AA");
			}
		case 1:
			if (S.contains("BA")) {
				return S.replace("BA", "AA");
			}
		case 2:
			if (S.contains("CB")) {
				return S.replace("CB", "CC");
			}
		case 3:
			if (S.contains("BC")) {
				return S.replace("BC", "CC");
			}
		case 4:
			if (S.contains("AA")) {
				return S.replace("AA", "A");
			}
		case 5:
			if (S.contains("CC")) {
				return S.replace("CC", "C");
			}
		default:
			return "";
		}
	}

	@Test
	public void testDecideArrayCanBeSortedInAtMostOneSwap() {
		// int[] test = {7,5,3,3,1};
		// System.out.println(getMinPosition(test));
		// System.out.println(getMaxPosition(test));
		// int[] sortedTest = {1,3,3,5,7};
		// System.out.println(isArrayNonDecreasingSorted(test));
		// System.out.println(isArrayNonDecreasingSorted(sortedTest));
		 int[] A = {1,5,3,3,7};
//		 int[] A = {1,3,5,3,4};
		// int[] unSortedTest = {7,3,3,5,1};
		// int[] unSortedTest = {1,3,5};
//		int[] unSortedTest = { -9, -8, -7, -6, -5, -5, -4 };
		System.out.println(getMinPosition(A, 0,	A.length - 1));
		System.out.println(getMaxPosition(A, 0,	A.length - 1));
		int count = 0;
		for (int i = 0, j = A.length - 1; i < j; i++, j--) {
			int minPosition = getMinPosition(A, i, j);
			int maxPosition = getMaxPosition(A, i, j);
			if (minPosition == i && maxPosition == j) {
				continue;
			} else {
				if (maxPosition != j) {
					swapOnArray(A, maxPosition, j);
					count++;
					if (isArrayNonDecreasingSorted(A)) {
						break;
					}
				}
				if (minPosition != i) {
					swapOnArray(A, minPosition, i);
					count++;
					if (isArrayNonDecreasingSorted(A)) {
						break;
					}
				}
			}
			if (count > 1) {
				break;
			}
		}
		System.out.println(count);
	}
	
	@Test
	public void testDecideArrayCanBeSortedInAtMostOneSwapForDebug() {
		 int[] A = {1,5,3,3,7};
//		 int[] A = {1,3,5,3,4};
		// int[] unSortedTest = {7,3,3,5,1};
		// int[] unSortedTest = {1,3,5};
//		int[] unSortedTest = { -9, -8, -7, -6, -5, -5, -4 };
		int count = 0;
		for (int i = 0, j = A.length - 1; i <= j; i++, j--) {
			int minPosition = getMinPosition(A, i, j);
			int maxPosition = getMaxPosition(A, i, j);
			
			if (minPosition != i && maxPosition != i) {
				swapOnArray(A, minPosition, i);
				count++;
				maxPosition = getMaxPosition(A, i, j);
				if (isArrayNonDecreasingSorted(A)) {
					break;
				}
			}
			
			if (maxPosition != j && minPosition != j) {
				swapOnArray(A, maxPosition, j);
				count++;
				minPosition = getMinPosition(A, i, j);
				if (isArrayNonDecreasingSorted(A)) {
					break;
				}
			}
			
			
			if (count > 1) {
				break;
			}
		}
		System.out.println(count);
	}
	
	private int getMinPosition(int[] A, int i, int j) {
		int minPos = i;
		int min = A[minPos];
		for (; i <= j; ++i) {
			if (min > A[i]) {
				min = A[i];
				minPos = i;
			}
		}
		return minPos;
	}

	private int getMaxPosition(int[] A, int i, int j) {
		int maxPos = 0;
		int max = A[maxPos];
		for (;i <= j; ++i) {
			if (max < A[i]) {
				max = A[i];
				maxPos = i;
			}
		}
		return maxPos;
	}

	private boolean isArrayNonDecreasingSorted(int[] A) {
		for (int i = 0, j = i + 1; i < A.length - 1; ++i, ++j) {
			if (A[i] > A[j]) {
				return false;
			}
		}
		return true;
	}

	private void swapOnArray(int[] A, int i, int j) {
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}

}


class Solution {
    public boolean solution(int[] A) {
        // write your code in Java SE 8
        int count = 0;
		for (int i = 0, j = A.length - 1; i <= j; i++, j--) {
			int minPosition = getMinPosition(A, i, j);
			int maxPosition = getMaxPosition(A, i, j);
			if (minPosition == i && maxPosition == j) {
				continue;
			} else {
				if (maxPosition != j) {
					swapOnArray(A, maxPosition, j);
					count++;
					if (isArrayNonDecreasingSorted(A)) {
						break;
					}
				}
				if (minPosition != i) {
					swapOnArray(A, minPosition, i);
					count++;
					if (isArrayNonDecreasingSorted(A)) {
						break;
					}
				}
			}
			if (count > 1) {
				break;
			}
		}
		return count <= 1;
    }
    
    private int getMinPosition(int[] A, int i, int j) {
		int minPos = i;
		int min = A[minPos];
		for (i = 0; i <= j; ++i) {
			if (min > A[i]) {
				min = A[i];
				minPos = i;
			}
		}
		return minPos;
	}

	private int getMaxPosition(int[] A, int i, int j) {
		int maxPos = 0;
		int max = A[maxPos];
		for (i = 0; i <= j; ++i) {
			if (max < A[i]) {
				max = A[i];
				maxPos = i;
			}
		}
		return maxPos;
	}
    
    private boolean isArrayNonDecreasingSorted(int[] A){
		for(int i = 0, j = i +1 ; i < A.length - 1 ; ++i, ++j){
			if(A[i] > A[j]){
				return false;
			}
		}
		return true;
	}
	private void swapOnArray(int[] A, int i, int j){
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}
}