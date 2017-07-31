package line.japan;

import static org.junit.Assert.*;

import org.junit.Test;

public class FactorialTest {

	@Test
	public void factSumValueTest() {
		// fail("Not yet implemented");
		FactorialSum classicRecursiveFactorial = new ClassicRecursiveFactorial();
		FactorialSum iterativeFactorial = new IterativeFactorial();

		assertTrue(iterativeFactorial.calcultateFactorialSum(100) == classicRecursiveFactorial.calcultateFactorialSum(100));
	}

	@Test
	public void instanceVariableRecursive() {
		FactorialSum instanceVariableRecursiveFactorial = new InstanceVariableFactorialSum();
		// System.out.println(tailRecursiveFactorial.calcultateFactorialSum(3));
		System.out.println(instanceVariableRecursiveFactorial.calcultateFactorialSum(5));
	}
	
	@Test
	public void iterationTest() {
		FactorialSum iterationFactorial = new IterativeFactorial();
		// System.out.println(tailRecursiveFactorial.calcultateFactorialSum(3));
		System.out.println(iterationFactorial.calcultateFactorialSum(8181));
	}
	
	@Test
	public void tailRecursiveTest() {
		FactorialSum tailRecursiveFactorial = new TailRecurusiveFactorial();
		FactorialSum iterationFactorial = new IterativeFactorial();
//		System.out.println(tailRecursiveFactorial.calcultateFactorialSum(5));
		assertTrue(tailRecursiveFactorial.calcultateFactorialSum(10) == iterationFactorial.calcultateFactorialSum(10));
	}

	private static final class ClassicRecursiveFactorial implements FactorialSum{
		public long calcultateFactorialSum(int n) {
			if (n < 2)
				return n;
			else
				return calcultateFactorialSum(n - 1) + calcultateFactorialSum(n - 2);
		}
	}

	private static final class InstanceVariableFactorialSum implements FactorialSum{
		private long firstPartialSum = -1;
		private long secondPartialSum = -1;

		public long calcultateFactorialSum(int n) {
			if (n == 0) {
				secondPartialSum = n;
				return secondPartialSum;
			} else if (n == 1) {
				firstPartialSum = n;
				return firstPartialSum;
			} else {
				if (firstPartialSum < 0) {
					firstPartialSum = calcultateFactorialSum(n - 1);
				}
				if (secondPartialSum < 0) {
					secondPartialSum = calcultateFactorialSum(n - 2);
				}
				long facSum = firstPartialSum + secondPartialSum;
				secondPartialSum = firstPartialSum;
				firstPartialSum = facSum;
				return facSum;
			}
		}
	}
	
	private static final class IterativeFactorial implements FactorialSum{
		private long firstPartialSum = -1l;
		private long secondPartialSum = -1l;
		private long facSum = 0l;
		
		public long calcultateFactorialSum(int n) {
			
			for (int i = 0; i <= n; ++i) {
				if (i == 0) {
					secondPartialSum = i;
					facSum = secondPartialSum;
				} else if (i == 1) {
					firstPartialSum = i;
					facSum = firstPartialSum;
				} else {
					facSum = firstPartialSum + secondPartialSum;
					secondPartialSum = firstPartialSum;
					firstPartialSum = facSum;
				}
			}
			return facSum;
		}
	}
	
	private static final class TailRecurusiveFactorial implements FactorialSum{

		@Override
		public long calcultateFactorialSum(int n) {
			// TODO Auto-generated method stub
			
			return tailRecursiveFactorialSum(n, 1, 0);
		}
		private long tailRecursiveFactorialSum(int n, long firstPartialSum, long secondPartialSum){
			if(n == 0){
				return secondPartialSum;				
			}else if(n == 1){
				return firstPartialSum;
			}else{
				return tailRecursiveFactorialSum(n - 1, firstPartialSum + secondPartialSum, firstPartialSum);
			}
		}
	}
	
	interface FactorialSum{
		long calcultateFactorialSum(int n);
	}
}
