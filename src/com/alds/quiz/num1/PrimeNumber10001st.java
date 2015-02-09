package com.alds.quiz.num1;

public class PrimeNumber10001st {
	public static void main(String[] args){
		long[] primeNumbers = new long[10000];
		long primeNumber = 2;
		primeNumbers[0] = primeNumber;// 2부터 소수를 초기화
		long targetNumber = primeNumbers[0]+1;
		long len = primeNumbers.length;
		long startTime = System.currentTimeMillis();
		for(int i = 0 ; i < len ; ){			
			boolean isPrime = true;
			for(int j = 0 ; j <= i// 지금까지 확인된 소수로 나누는데
					// 12의 예에서 보듯이 1,2,3,4,6,12 일 때 3으로 나누어 떨어진다는 의미는 4로도 나누어 떨어진다는 의미이므로
					// root값이하 까지의 소수로만 나누어서 떨어지는지만 확인해도 무방하다.
					&& primeNumbers[j] <= (long)Math.sqrt(targetNumber)					
					; ++j){
				isPrime &= (targetNumber % primeNumbers[j] !=0);
				if(!isPrime){
					break;
				}
			}
			if(isPrime){
				if((i+1)<len){// 10000번 째까지는 소수이면 저장하고
					primeNumbers[++i] = targetNumber;
					targetNumber++;
				}else{// 10001번째는 출력한다
					System.out.println(targetNumber);
					break;
				}
			}else{// 소수가 아니면 다음 숫자로 소수 여부를 확인한다
				targetNumber++;
				continue;
			}
		}
		System.out.println("Elapsed Time : "+(System.currentTimeMillis() - startTime)+"ms");
	}
}


