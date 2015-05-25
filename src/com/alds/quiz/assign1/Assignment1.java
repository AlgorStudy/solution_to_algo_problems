package com.alds.quiz.assign1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Assignment1 {
	private static final BufferedReader br = new BufferedReader(
			new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		try{
			CupPyramid cupPyramid = new CupPyramid(10);
			cupPyramid.pourWaterIntoCupPyramid(getWaterMount());
			System.out.println(cupPyramid.findMaxCupSequence());
		}finally{
			releaseBufferedReader();
		}
	}
	private static int getWaterMount() throws IOException{
		return Integer.parseInt(br.readLine());
	}
	private static void releaseBufferedReader() throws IOException {
		br.close();
	}
}

class CupPyramid {
	private final double[] layers, waterMount;
	private final int cupCount;
	private final double cupCapa;
	CupPyramid(int cupCount){
		this.cupCount = cupCount;
		this.cupCapa = 1l;
		this.layers = new double[cupCount]; 
		this.waterMount = new double[cupCount];
	}
	
	int findMaxCupSequence(){
		int maxCupSeq = -1;
		for(int i = 0; i < waterMount.length ; ++i){
			double eachMount = waterMount[i];
			if(eachMount > 0.0 && (maxCupSeq < i)){
				maxCupSeq = i;
			}
		}
		return maxCupSeq+1;
	}
	
	void pourWaterIntoCupPyramid(double liter){
		waterMount[0] = liter;
		int childIndex = 0;
		for (int i=0; i<(cupCount-1);++i) {
			double over = 0.0;
			if (waterMount[i] > cupCapa) {
				over = (waterMount[i] - cupCapa)/2;
				waterMount[i] = cupCapa;
			}
			if (i == 0 || layers[i-1] < layers[i]) {
				++childIndex;
			}
			if (childIndex >= cupCount){
				break;
			}
			layers[childIndex] = layers[i]+1;
			waterMount[childIndex] += over;
			++childIndex;
			if (childIndex >= cupCount){
				break;
			}
			layers[childIndex] = layers[i]+1;
			waterMount[childIndex] += over;
		}
	}
}