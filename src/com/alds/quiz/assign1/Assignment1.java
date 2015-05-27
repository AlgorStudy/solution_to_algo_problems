package com.alds.quiz.assign1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Assignment1 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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
		this.cupCapa = 1;
		this.layers = new double[cupCount]; 
		this.waterMount = new double[cupCount];
	}
	
	int findMaxCupSequence(){
		int maxCupSeq = -1;
		for(int i = 0; i < waterMount.length ; ++i){
			double eachMount = waterMount[i];
			if(eachMount > 0.0 && (maxCupSeq < i)){// 물이 할당된 컵의 인덱스 중 가장 큰 것을 찾음
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
				over = (waterMount[i] - cupCapa)/2;// 두개 자식 컵에 각각 동일한 양을 분배
				waterMount[i] = cupCapa;// 컵에서 수용할 수 있는 양만큼 할당
			}
			if (i == 0 || layers[i-1] < layers[i]) {// 부모 컵의 계층이 바뀌면 자식 컵에 대한 인덱스도 하나 증가해야한다
				++childIndex;
			}
			if (childIndex >= cupCount){
				break;
			}
			layers[childIndex] = layers[i]+1;// 자식 컵은 부모 컵보다 무조건 하위 계층에 있음
			waterMount[childIndex] += over;
			++childIndex;// 부모 컵에는 각각 2개의 자식 컵이 있고 인덱스는 좌에서 우로 하나씩 증가함
			if (childIndex >= cupCount){
				break;
			}
			layers[childIndex] = layers[i]+1;// 자식 컵은 부모 컵보다 무조건 하위 계층에 있음
			waterMount[childIndex] += over;
		}
	}
}