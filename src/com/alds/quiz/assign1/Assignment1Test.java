package com.alds.quiz.assign1;

import org.junit.Test;

public class Assignment1Test {

	@Test
	public void test() {
		System.out.println(calculateWaterVol(1.0, 6.0, 10));
	}
	/**
	 * 	 * http://www.careercup.com/question?id=9820788
	 */
	public double calculateWaterVol(double c, double l, int kth /* one based */) {
		int [] height = new int[kth];
		double[] water = new double[kth];
		water[0] = l;
		int childIndex = 0;
		for (int i=0; i<(kth-1);++i) {
			double over = 0.0;
			if (water[i] > c) {
				over = (water[i] - c)/2;
				water[i] = c;
			}
			if (i == 0 || height[i-1] < height[i]) {
				++childIndex;
			}
			if (childIndex >= kth) break;
			height[childIndex] = height[i]+1;
			water[childIndex] += over;
			++childIndex;
			if (childIndex >= kth) break;
			height[childIndex] = height[i]+1;
			water[childIndex] += over;
		}
		return water[kth-1] >  c ? c : water[kth-1];
	}
	
	
	@Test
	public void testFindMaxCupSeq() {
		CupPyramid cupPyramid = new CupPyramid(10);
		cupPyramid.pourWaterIntoCupPyramid(6);
		System.out.println(cupPyramid.findMaxCupSequence());
	}
}
