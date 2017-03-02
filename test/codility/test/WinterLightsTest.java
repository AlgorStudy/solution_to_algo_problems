package codility.test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class WinterLightsTest {
	private static final int MAX_LENGTH = 1000000007;
	@Test
	public void test() {
		String testCase = "02002";
		System.out.println(getPossibleSegments(testCase));
	}
	private int getPossibleSegments(String lightChain){
		int initialSegCount = lightChain.length();
		System.out.println(getCharCount(lightChain));
		return 11;
	}
	
	private int getCharCount(String lightChain){
		Set<Character> set = new HashSet<Character>();
		for(char c : lightChain.toCharArray()){
			set.add(c);
		}
		return set.size();
	}
}
