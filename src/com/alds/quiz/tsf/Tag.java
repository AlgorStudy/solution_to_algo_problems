package com.alds.quiz.tsf;

class Tag {
	/**
	 * 태그 사이즈
	 */
	private long tagSize;
	/**
	 * 태그 휫수
	 */
	private long tagCount;
	/**
	 * 태그 별 아이디
	 */
	private String tagId;
	/**
	 * 태그 사이즈
	 * @return 테그 사이즈 정수 값 리턴
	 */
	public long getTagSize() {
		return tagSize;
	}
	/**
	 * 태그 별 사이트 저장
	 * @param tagSize 태그 사이즈
	 */
	public void setTagSize(long tagSize) {
		this.tagSize = tagSize;
	}
	/**
	 * 태깅 횟수 리턴
	 * @return 태깅 횟수 long 리턴
	 */
	public long getTagCount() {
		return tagCount;
	}
	/**
	 * 태깅 횟수 저장
	 * @param tagCount 태깅 횟수, long 타입
	 */
	public void setTagCount(long tagCount) {
		this.tagCount = tagCount;
	}
	/**
	 * 테그별 식별자 리턴
	 * @return 테그별 식별자 string 리턴
	 */
	public String getTagId() {
		return tagId;
	}
	/**
	 * 태그 별 고유 아이디 저장
	 * @param tagId 태그별 식별자 ,string 타입
	 */
	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
	/**
	 * Tag 출력 시 포맷
	 */
	public String toString(){
		return "tagSize = "+tagSize+" : tagCount = "+tagCount+" : tagId = "+tagId+"\n";
	}
}

