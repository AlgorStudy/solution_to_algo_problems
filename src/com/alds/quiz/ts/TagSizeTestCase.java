package com.alds.quiz.ts;

import java.util.List;

import org.junit.Test;
/**
 * 태깅 사이즈 저장 클래스 단위 테스트
 */
public class TagSizeTestCase {
	/**
	 * 사이즈 10 개인 경우의 태그 리스트 태깅 사이즈 저장 로직 테스트
	 */
	@Test
	public void test() {
		TaggingSizeFinder tsf = new TaggingSizeFinder();
		List<Tag> generateTagList = tsf.generateTagList(10);
		tsf.setTagSizeList(generateTagList);
//		System.out.println(generateTagList);
		tsf.showTagSizeList(generateTagList);
	}

}
