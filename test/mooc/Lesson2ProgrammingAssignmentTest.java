package mooc;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;
/**
 * 
 * @author USER
 *
 */
public class Lesson2ProgrammingAssignmentTest {

	@Test
	public void testWholeDataSort() {
		try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\USER\\Dropbox\\MOOC\\IntegerArray.txt"))){
			int[] intArray = new int[100000];
			int i = 0;
			for(String intInString = br.readLine(); intInString != null ; intInString = br.readLine(), i++) {
				intArray[i] = Integer.valueOf(intInString);
			}
			int[] mergeSortedResult = mergeSort(intArray);
			System.out.println(Arrays.toString(mergeSortedResult));
			System.out.println("inversionCount : "+inversionCount);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSubArrayTest() {
		int[] testData = {2,1,0};
		System.out.println(Arrays.toString(mergeSort(testData)));
	}
	
	@Test
	public void testMergeSortTest() {
		int[] testData = {4,3,2,1,0};
		// System.out.println(Arrays.toString(Arrays.copyOfRange(testData, 0, testData.length/2)));
		// System.out.println(Arrays.toString(Arrays.copyOfRange(testData, testData.length/2, testData.length)));
		System.out.println(Arrays.toString(mergeSort(testData)));
		System.out.println("inversionCount : "+inversionCount);
	}
	
	private int[] mergeSort(int[] data) {
		if(data.length == 1) {
			return data;
		}else {
			int[] leftSide = mergeSort(Arrays.copyOfRange(data, 0, data.length/2));
			int[] rightSide = mergeSort(Arrays.copyOfRange(data, data.length/2, data.length));
			return getMergedArray(leftSide, rightSide);
		}
	}
	private long inversionCount = 0l;
	private int[] getMergedArray(int[] leftSide, int[] rightSide) {
		int[] mergedAndSortedArray = new int[leftSide.length + rightSide.length];
		for(int i = 0, j = 0, k = 0;k < mergedAndSortedArray.length; ++k) {
			if(i >= leftSide.length) {
				mergedAndSortedArray[k] = rightSide[j];
				++j;
				continue;
			}
			if(j >= rightSide.length) {
				mergedAndSortedArray[k] = leftSide[i];
				++i;
				continue;
			}
			if(leftSide[i] < rightSide[j]) {
				mergedAndSortedArray[k] = leftSide[i];
				++i;
			}else{
				mergedAndSortedArray[k] = rightSide[j];
				++j;
				inversionCount += (leftSide.length - i);
			}
		}
		return mergedAndSortedArray;
	}
}
