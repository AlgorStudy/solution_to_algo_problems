package com.alds.al.sol;

import java.util.Arrays;
import java.util.Random;

public class HeapSorting {
	public static void main(String[] args){
		int[] inputArray = genRandomIntArray(100);
		// 정렬 전 
		displayArray(inputArray);
		
		Heap heap = new Heap(inputArray);
		heap.heapSort();
		
		// 정렬 후
		displayArray(heap.getHeapArray());	
	}
	private static int[] genRandomIntArray(int size){
		Random random = new Random(43);
		int[] array = new int[size];
		for(int i = 0; i < size ; ++i ){
			array[i] = random.nextInt(size * 2);
		}
		return array;
	}
	private static void displayArray(int[] array){
		for(int temp : array){
			System.out.print(temp+" ");
		}
		System.out.print("\n");
	}
}
// 알고리즘은 introduction to algorothms를 참고
class Heap{
	private int[] heapArray;
	int[] getHeapArray(){
		return this.heapArray;
	}
	private int heapSize;
	
	Heap(int[] inputArray){
		this.heapArray = Arrays.copyOf(inputArray, inputArray.length);
		this.heapSize = inputArray.length;		
	}	
	/**
	HEAPSORT(A)
		1 BUILD-MAX-HEAP(A)
		2 for i = A.length downto 2
		3 exchange A[1] with A[i]
	    4 A.heap-size = A.heap-size-1
	    5 MAX-HEAPIFY(A, 1)
	*/
	void heapSort(){
		buildHeap();
		for(int i = heapArray.length - 1 ; i > 0; i-- ){
			swap(heapArray, 0, i);
			heapSize = heapSize - 1;
			maxHeapify(0);
		}
	}
	/**
	 * BUILD-MAX-HEAP.A/
		1 A.heap-size = A.length
		2 for i = floor(A.length/2) downto 1
		3 MAX-HEAPIFY(A, i)
	 */
	private void buildHeap(){
		/**
		 * Initialization: Prior to the first iteration of the loop, i = floor(n/2) . Each node
			floor(n/2)+1, floor(n/2)+2, ... , n is a leaf and is thus the root of a trivial max-heap
			
			floor(n/2)+1가 leaf node가 아니라면 2*( floor(n/2)+1 )와 2*( floor(n/2)+1 ) + 1은 각각 왼쪽, 오른쪽 자식 노드의 위치가 되는데
			2*( floor(n/2)+1 ) > heapSize 이므로 모순
			따라서  floor(n/2)+1는 leaf node
		 */
		int halfPoint = (int)Math.floor(heapArray.length / 2);
		for(int i = halfPoint ; i >= 0; --i){
			maxHeapify(i);
		}
	}
	/** 
	MAX-HEAPIFY.A; i/
	1 l = LEFT(i)
	2 r = RIGHT(i)
	3 if l <= A.heap-size and A[l] > A[i]
	4 	   largest = l
	5 else largest = i
	6 if r <= A.heap-size and A[r] > A[largest]
	7 	 largest = r
	8 if largest != i
	9     exchange A[i] with A[largest]
	10    MAX-HEAPIFY(A.largest)
	*/
	private void maxHeapify(int i){
		int largest = 0;
		int l = left(i);
		int r = right(i);		
		if(l < heapSize && heapArray[l] > heapArray[i]){
			largest = l;
		}else{
			largest = i;
		}
		if(r < heapSize && heapArray[r] > heapArray[largest]){
			largest = r;
		}
		if(largest != i){
			swap(heapArray, i, largest);
			maxHeapify(largest);
		}
	}
	/**
	 * PARENT.i/
	      1 return floor(i/2)
	   LEFT.i/
          1 return 2i
       RIGHT.i/
          1 return 2i + 1	
	 */
	private int left(int i){
		return 2*i;
	}	
	private int right(int i){
		return 2*i + 1;
	}
//	private int parent(int i){
//		return (int)Math.floor(i/2);
//	}
	private void swap(int[] array, int i, int j){
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}