package solution_to_algo_problems;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import static java.util.Calendar.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class CodilityTest {

	private final Set<Character> set = new HashSet<Character>();
	@Test
	public void test() throws ParseException {
		System.out.println(Arrays.toString(splitTimeStamp("15:15:00")));
		putCharacterInSet(splitTimeStamp("15:15:00"));
		System.out.println(set.size());
		showTimeStamp("15:15:00");
		showTimeStamp("15:15:12");
	}
	
	private String[] splitTimeStamp(String st){
		String[] tokens = st.split(":");
		return tokens;
	}
	private void putCharacterInSet(String[] tokens){
		for(String eachToken : tokens){
			char[] charArray = eachToken.toCharArray();
			for(char eachChar : charArray){
				set.add(eachChar);
			}
		}
	}
	private void putCharacterInSet(String token){
		char[] charArray = token.toCharArray();
		for(char eachChar : charArray){
			set.add(eachChar);
		}
	}
	
	private String getLpaddingStringFromHMS(int hms){
		return String.format("%02d", hms);
	}
	
	private void showTimeStamp(String st) throws ParseException{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
		Date parse = simpleDateFormat.parse(st);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parse);
		System.out.println(Calendar.HOUR_OF_DAY);
	}
	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
	@Test
	public void testTimeStampWhileLoop() throws ParseException{
		Date parse = simpleDateFormat.parse("22:22:21");
		Date parse1 = simpleDateFormat.parse("22:22:23");
		
		Calendar fromCalendar = Calendar.getInstance();
		Calendar toCalendar = Calendar.getInstance();
		
		fromCalendar.setTime(parse);
		toCalendar.setTime(parse1);
		
		int count = 0;
		for(;fromCalendar.getTimeInMillis() <= toCalendar.getTimeInMillis() ;fromCalendar.add(Calendar.SECOND, 1)){
			putCharacterInSet(getLpaddingStringFromHMS(fromCalendar.get(HOUR_OF_DAY)));
			putCharacterInSet(getLpaddingStringFromHMS(fromCalendar.get(MINUTE)));
			putCharacterInSet(getLpaddingStringFromHMS(fromCalendar.get(SECOND)));
			if(set.size() < 3){
				count++;
			}
			set.clear();
		}
		
		System.out.println(count);
	}
	
	@Test
	public void testSparseDecomposition(){
		for(int i = 0 ; i <= 26; ++i ){
			if(isSparse(i) && isSparse(26 - i)){
				System.out.println(Integer.toBinaryString(i)+":"+i);				
			}
		}
		System.out.println(isSparse(136));
		System.out.println(isSparse(1500 - 136));
	}
	
	@Test
	public void testSparseInteger(){
		System.out.println(Integer.toBinaryString(136));
		System.out.println(136 >> 1);
		System.out.println(Integer.toBinaryString(136 >> 1));
		System.out.println(136 & (136 >> 1));
		System.out.println(9 & (9 >> 1));
		System.out.println(10 & (10 >> 1));
	}
	
	private boolean isSparse(int n) {
	    return (n & (n >> 1)) == 0;
	}
	
	@Test
	public void testMaximumDistance(){
		List<Integer> list = new ArrayList<Integer>(3*3);
		int[] A = {1, 3, -9};
		for(int i = 0 ; i < A.length ; ++i){
			for(int j = 0 ; j < A.length ; ++j){
				list.add(A[i]+A[j]+Math.abs(i-j));
			}
		}
		System.out.println(Collections.max(list));
	}
	
	@Test
	public void testMaximumDistanceAsList(){
//		int[] A = {1, 3, -9};
		int[] A = {-8, 4, 0, 5, -3, 6};
		Integer[] B = new Integer[A.length];
		Integer[] C = new Integer[A.length];
		
		for(int i = 0 ; i < A.length ; ++i){
			B[i] = A[i] + i;
		}
		for(int j = 0 ; j < A.length ; ++j){
			C[j] = A[j] - j;
		}
		System.out.println(Collections.max(new ArrayList<Integer>(Arrays.asList(B))));
		System.out.println(Collections.max(new ArrayList<Integer>(Arrays.asList(C))));
	}
}
