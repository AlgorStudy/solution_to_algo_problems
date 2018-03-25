package pe;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ProjectEuler {
	private static List<Integer> primeNumbers = new ArrayList<Integer>();
	static{
		primeNumbers.add(2);
	}
	@Test
	public void test() {
//		int targetValue = 13195;
		long targetValue = 600851475143l;
//		long targetValue = 87625999;
		for(int i = 2 ;i <= (int)Math.sqrt(targetValue); ++i ){
			if(isPrimeNumber(i) && (targetValue % i == 0)){
				System.out.print(i+" ");
				System.out.println(targetValue / i);
			}
		}
	}

	private boolean isPrimeNumber(int n){
		for(int eachPrimeNumber : primeNumbers){
			if(n < eachPrimeNumber)
				break;
			if(n % eachPrimeNumber == 0)
				return Boolean.FALSE;
			else
				continue;
		}
		primeNumbers.add(n);
		return Boolean.TRUE;
	}
}
