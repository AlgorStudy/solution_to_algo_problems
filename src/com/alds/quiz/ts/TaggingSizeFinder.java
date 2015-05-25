package com.alds.quiz.ts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
/**
 * 생성된 태그 리스트의 태깅횟수 최대값과 최소값 사이를 10개 구간으로 나눈뒤
 * 각 구간에 속하는 태그들에 대해 태깅 사이즈 값을 저장하는 클래스
 */
class TaggingSizeFinder {
	private final long MAX_TAGGING_SIZE = 10000000l;
	/**
	 * 태깅 카운트를 위한 Comparator
	 */
	private static final Comparator<Tag> TAG_COUNT_COMP = new Comparator<Tag>(){
		@Override
		public int compare(Tag arg0, Tag arg1) {
			// TODO Auto-generated method stub
			int compFlag = 0;
			if(arg0.getTagCount() - arg1.getTagCount() > 0){
				compFlag = 1;
			}else if(arg0.getTagCount() - arg1.getTagCount() < 0){
				compFlag = -1;
			}else{
				compFlag = 0;
			}
			return compFlag;
		}
	};
	/**
	 * 태깅 아이디를 위한 Comparator
	 */
	private static final Comparator<Tag> TAG_ID_COMP = new Comparator<Tag>(){
		@Override
		public int compare(Tag arg0, Tag arg1) {
			// TODO Auto-generated method stub
			/**
			* 태그 아이디 중 뒤 숫자만 사용
			*/
			String tagId0 = arg0.getTagId().substring(2);
			String tagId1 = arg1.getTagId().substring(2);
			return Integer.parseInt(tagId1) - Integer.parseInt(tagId0);
		}
	};
	/**
	 * 주어진 갯수 만큼의 원소를 갖는 태그 리스트 생성
	 * @param count 태그 원소 갯숙
	 * @return 태그 리스트들
	 */
	List<Tag> generateTagList(int count){
		Random rand = new Random(System.nanoTime());
		List<Tag> tagList = new ArrayList<Tag>(count);
		for(int i = 0 ; i < count ; i++){
			Tag tag = new Tag();
			long taggingCount = rand.nextLong();
			if( taggingCount < 0){
				taggingCount *= -1;				
			}
			if(taggingCount > MAX_TAGGING_SIZE){
				taggingCount = taggingCount % MAX_TAGGING_SIZE;
			}
			tag.setTagId("태그"+i);
			tag.setTagCount(taggingCount);
			tagList.add(tag);
		}
		return tagList;
	}
	/**태그리스트들의 최대값과 최소값 사이를 10개 구간으로 나눈뒤 각 구간에 속하는 태그들의
	 * 태깅 사이즈를 저장 
	 * @param list 태그 리스트들
	 */
	void setTagSizeList(List<Tag> list){
		Tag max = Collections.max(list, TAG_COUNT_COMP);
		Tag min = Collections.min(list, TAG_COUNT_COMP);
		long sectionSizePerTenPercentage = (max.getTagCount() - min.getTagCount()) / 10; 
		for(Tag tag : list){
			long sectionLowerLimit = min.getTagCount();
			long sectionUpperLimit = min.getTagCount() + sectionSizePerTenPercentage;
			for(int i = 1 ; i <= 11 ; ++i){ //최대값을 포함하기 위해 11까지 계산
				if(sectionLowerLimit <= tag.getTagCount() && tag.getTagCount() < sectionUpperLimit){
					tag.setTagSize(i < 11 ? i : 10);// 태그 사이즈는 10이 최대
					break;
				}else{
					sectionLowerLimit = sectionUpperLimit;
					sectionUpperLimit = (sectionLowerLimit + sectionSizePerTenPercentage);
				}
				
			}
		}
	}
	/**
	 * 태그 사이즈가 5이상인 태그들을 출력
	 * @param list
	 */
	void showTagSizeList(List<Tag> list){
		Collections.sort(list, TAG_ID_COMP);
		for(Tag tag : list){
			if(tag.getTagSize() > 5){
				System.out.println("tagName:"+tag.getTagId()+", tagSize:"+tag.getTagSize()+", tagCount:"+tag.getTagCount());
			}
		}
	}
	
}

