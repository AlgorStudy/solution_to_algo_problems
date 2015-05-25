package com.alds.quiz.ts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
/**
 * 태그 사이즈 세팅 프로그램 수행용 클래스
 * 
 */
public class Main {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		try{
			int tagNumber = getTagNumber();
			TaggingSizeFinder tsf = new TaggingSizeFinder();
			List<Tag> generateTagList = tsf.generateTagList(tagNumber);
			tsf.setTagSizeList(generateTagList);
			tsf.showTagSizeList(generateTagList);
		}finally{
			br.close();
		}
	}
	/**
	 * 사용자로 부터 태그 리스트 사이즈를 입력받음
	 * @return 태그 리스트 사이즈
	 * @throws IOException
	 */
	private static int getTagNumber() throws IOException{
		return Integer.parseInt(br.readLine()); 
	}
}
