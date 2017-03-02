package com.alds.quiz.pe.pro14;

import java.util.HashMap;
import java.util.Map;

public class LongestCollatzSeqCaller {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LongestCollatzSeqNum lcs = new LongestCollatzSeqNum(1000000);
		long startTime = System.currentTimeMillis();
		lcs.executeLongestCollatzSeqNum();
		System.out.printf("Elapsed Time : %4d ms\n", System.currentTimeMillis()	- startTime);
	}

}
class LongestCollatzSeqNum {
	private final Map<Long, Long> cache;//<K:producer number, V:number of Collatz Chain>
	private final int size;

	LongestCollatzSeqNum(int size) {
		this.size = size;
		cache = new HashMap<Long, Long>(size);
	}

	void executeLongestCollatzSeqNum() {
		long longestChainSeqProducer = 1l;
		long longestChainSeqNum = 1l;
		for (int i = 1; i < size; ++i) {
			long temp = getCollatzChainNumber(i);
			if (temp > longestChainSeqNum) {
				longestChainSeqNum = temp;
				longestChainSeqProducer = i;
			}
		}
		System.out.println(longestChainSeqProducer);
	}

	private long getCollatzChainNumber(final long targetNumber) {
		long copyOfParam = targetNumber;
		long count = 1l;
		while (copyOfParam > 1) {
			Long cacheValue = copyOfParam <= targetNumber ? cache.get(copyOfParam) : null;
			if (cacheValue == null) {
				if (copyOfParam % 2 == 0) {
					copyOfParam = copyOfParam / 2;
				} else {
					copyOfParam = (3 * copyOfParam + 1) / 2; // (odd * odd + 1) is obviously even, so it can be divided by 2.
				}
				count++;
			} else {
				count--;// back to the previous step
				count += cacheValue.longValue();
				break;
			}
		}
		if (cache.get(targetNumber) == null) {
			cache.put(targetNumber, count);
		}
		return count;
	}
}
