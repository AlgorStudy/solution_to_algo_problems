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
	private static final String DELIMITER = " ";
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
		List<Element> tempList = new ArrayList<Element>(tokens.length);
		for(String temp :tokens){
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
	private final int listCount;
	private final List<Element> mergedList;
	private int minSectionStartIndex, minSectionEndIndex;
	private int minSectionSize;
	MinimunSectionFinder(int listCount){
		this.listCount = listCount;
		mergedList = new ArrayList<Element>(listCount*10);
		minSectionStartIndex = -1;
		minSectionEndIndex = -1;
		minSectionSize = Integer.MAX_VALUE;
	}
	void addList(List<Element> newList){
		mergedList.addAll(newList);
	}
	void findMinimumSection(){
		Collections.sort(mergedList, ELEMENT_COMP);
		Set<Integer> listIdSet = new HashSet<Integer>();
		int size = mergedList.size();
		for(int i = 0; ( i + listCount ) < size ; ++i){
			for(int j = i ; j < ( i + listCount ) ; ++j){
				listIdSet.add(mergedList.get(j).getListId());
			}
			if(listIdSet.size() == listCount){
				int tempEndPoint = i+listCount-1, tempStartPoint = i;
				int sectionSize = mergedList.get(tempEndPoint).getValue() - mergedList.get(tempStartPoint).getValue();
				
				if(sectionSize < minSectionSize){
					minSectionSize = sectionSize;
					minSectionStartIndex = tempStartPoint;
					minSectionEndIndex = tempEndPoint;
				}
			}
			listIdSet.clear();
		}
		System.out.println(mergedList.get(minSectionStartIndex).getValue()+", "+mergedList.get(minSectionEndIndex).getValue());
	}
}
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