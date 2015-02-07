package com.alds.quiz.num1;

public class PrimeNumber10001st {
	public static void main(String[] args){
		long[] primeNumbers = new long[10000];
		long primeNumber = 2;
		primeNumbers[0] = primeNumber;
		long targetNumber = primeNumbers[0]+1;
		long len = primeNumbers.length;
		for(int i = 0 ; i < len ; ){			
			boolean isPrime = true;
			for(int j = 0 ; j <= i ; ++j){
				isPrime &= (targetNumber % primeNumbers[j] !=0);
				if(!isPrime){
					break;
				}
			}
			if(isPrime){
				if((i+1)<len){
					primeNumbers[++i] = targetNumber;
					targetNumber++;
				}else{
					System.out.println(targetNumber);
					break;
				}
			}else{
				targetNumber++;
				continue;
			}
		}
				
	}
}


