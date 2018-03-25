package mooc;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;

public class Lesson3ProgrammingAssignmentTest {

	@Test
	public void test() {
		int[] arrayInteger = new int[10000];
		try(BufferedReader br = new BufferedReader(new FileReader("D:\\dropbox\\Dropbox\\MOOC\\QuickSort.txt"))){			
			int i = 0 ;
			for(String line = br.readLine(); line != null ; line = br.readLine()) {
				int valueInInteger = Integer.parseInt(line);				
				arrayInteger[i++] = valueInInteger;				
			}
			quickSort(arrayInteger, 0, arrayInteger.length - 1);
			System.out.println(Arrays.toString(arrayInteger));
			System.out.println(counter);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void testWithSmallDataSet() {
		int[] testDataSet = {4,3,2,1};
		quickSort(testDataSet, 0 , testDataSet.length - 1);
		System.out.println(Arrays.toString(testDataSet));
	}
	
	private void quickSort(int[] inputData, int p, int r) {
		if(p < r) {
			int q = partitionArray(inputData, p, r);
			quickSort(inputData, p, q - 1);
			quickSort(inputData, q + 1, r);
		}
	}
	private long counter = 0l;
	private int partitionArray(int[] a, int p, int r) {
		int x = a[r];
		int i = p - 1;
		for(int j = p ; j < r ; ++j) {
			if(a[j] < x) {
				i++;
				swap(a, i, j);				
			}
		}
		swap(a, i + 1, r);
		return i + 1;
	}
	
	private void swap(int[] a, int i , int j) {
		int temp = a[j];
		a[j] = a[i];
		a[i] = temp;
		counter++;
	}
	
}
