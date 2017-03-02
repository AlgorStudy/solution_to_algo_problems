package com.alds.quiz.assign2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Assignment2 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final String DELIMITER = " ";// 입력값 구분자
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		try{
			int listCount = getListCount();
			MinimunSectionFinder msf = new MinimunSectionFinder(listCount);
			for(int i = 0 ; i < listCount ; ++i){
				List<Element> listFromInput = getListFromInput(i);
				msf.addList(listFromInput);
			}
			msf.findMinimumSection();
		}finally{
			releaseBufferedReader();
		}
	}
	private static int getListCount() throws IOException{
		return Integer.parseInt(br.readLine());
	}
	private static List<Element> getListFromInput(final int listId) throws IOException{
		String intgerListInStr = br.readLine();
		String[] tokens = intgerListInStr.split(DELIMITER);
		Set<String> dupFilter = new HashSet<String>();
		for(String temp : tokens){
			dupFilter.add(temp);// 중복 값을 제거
		}
		List<Element> tempList = new ArrayList<Element>(dupFilter.size());
		for(String temp : dupFilter){
			Element element = new Element(Integer.parseInt(temp), listId);
			tempList.add(element);
		}
		return tempList;
	}
	private static void releaseBufferedReader() throws IOException{
		br.close();
	}
}
class MinimunSectionFinder{
	/**
	 * 리스트들을 합친 후 정렬하는 기준을 제공하는 인스턴스
	 */
	private static final Comparator<Element> ELEMENT_COMP = new Comparator<Element>(){
		@Override
		public int compare(Element o1, Element o2) {
			// TODO Auto-generated method stub			 
			// 값이 같으면 리스트 아이디 기준 정렬
			int valueCompare = o1.getValue() - o2.getValue();
			int idCompare = o1.getListId() - o2.getListId();
			return valueCompare != 0 ? valueCompare : idCompare;
		}		
	};
	
	private final int listCount;// 입력된 리스트의 갯수(k)
	private final List<Element> mergedList;// 입력된 리스트를 하나로 저장할 인스턴스 
	private int minSectionStartIndex, minSectionEndIndex;// 최단 구간의 시작 인덱스, 종료인덱스
	private int minSectionSize;// 최단 구간의 길이
	
	MinimunSectionFinder(int listCount){
		this.listCount = listCount;
		mergedList = new ArrayList<Element>(listCount*10);
		minSectionStartIndex = -1;
		minSectionEndIndex = -1;
		minSectionSize = Integer.MAX_VALUE;
	}
	/**
	 * 입력된 리스트를 하나의 인스턴스로 합침
	 * @param newList 사용자 입력한 리스트
	 */
	void addList(List<Element> newList){
		mergedList.addAll(newList);
	}
	/**
	 * 각 리스트의 원소를 적어도 하나씩 포함하는 최단 길이 구간 시작 값과 종료값 출력
	 */
	void findMinimumSection(){
		Collections.sort(mergedList, ELEMENT_COMP);
		Set<Integer> listIdSet = new HashSet<Integer>();
		int size = mergedList.size();
		for(int i = 0; ( i + listCount ) <= size ; ++i){
			for(int j = i ; j < ( i + listCount ) ; ++j){
				listIdSet.add(mergedList.get(j).getListId());//검색 대상 구간의 리스트 아이디를 중복을 제거해서 Set에 저장 
			}
			if(listIdSet.size() == listCount){// 모든 리스트 아이디가 들어왔다면
				int tempEndPoint = i+listCount-1, tempStartPoint = i;
				int sectionSize = mergedList.get(tempEndPoint).getValue() - mergedList.get(tempStartPoint).getValue();
				
				if(sectionSize < minSectionSize){// 구간 길이가 더 작은 새로운 구간이 발견되면 저장
					minSectionSize = sectionSize;
					minSectionStartIndex = tempStartPoint;
					minSectionEndIndex = tempEndPoint;
				}
			}
			listIdSet.clear();
		}
		if(minSectionStartIndex >=0 && minSectionEndIndex >=0){// 최소구간을 찾은 경우에만 출력
			System.out.println(mergedList.get(minSectionStartIndex).getValue()+", "+mergedList.get(minSectionEndIndex).getValue());
		}
	}
}
/**
 * 원소는 value와 속한 리스트의 아이디로 구성 
 *
 */
class Element{
	private final int value;
	private final int listId;
	Element(int value, int listId){
		this.value = value;
		this.listId = listId;
	}
	public int getValue() {
		return value;
	}
	public int getListId() {
		return listId;
	}
	@Override
	public String toString(){
		return "value = "+value+" : listId = "+listId;
	}
}