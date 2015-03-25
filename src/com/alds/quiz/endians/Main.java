package com.alds.quiz.endians;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	private final static long EIGHT_BIT_VAL = 256l;
	private final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	/**
	 * @param args
	 */
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		try{
			long testCaseCount = getInputValue();
			for(int i = 0 ; i < testCaseCount ; ++i ){
				long inputValue = getInputValue();
				getEndianValue(inputValue);
			}
		}finally{
			releaseBufferedReader();
		}
	}
	static Long getInputValue() throws IOException {
		String readLine = br.readLine();
		return Long.parseLong(readLine);
	}
	static void releaseBufferedReader() throws IOException{
		br.close();
	}
	static void getEndianValue(long param){		
		long endianVal = 0l;
		long pow = 1;
		Stack<Long> st = new Stack<Long>();
		for(int i = 4; i > 0 ; --i ){
			long byteFlag = param % EIGHT_BIT_VAL;
			st.push(byteFlag);
//			pow *= eightBitVal;
			param /= EIGHT_BIT_VAL;			
		}
		for(;!st.empty();){
			long popVal = (long)st.pop();
			endianVal += (popVal*pow);
			pow *= EIGHT_BIT_VAL;
		}
		System.out.println(endianVal);
	}
	
}
