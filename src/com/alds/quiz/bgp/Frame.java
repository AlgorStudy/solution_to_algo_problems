package com.alds.quiz.bgp;
/**
 * 볼링 게임에서 프레임에 대한 데이터 모델
 * @author USER
 *
 */
class Frame{
	/**
	 * 잔여 핀카운트
	 */
	private int pinCount;
	/**
	 * 프레임 식별자
	 */
	private final int frameSeq;
	/**
	 * 득점 결과
	 */
	private final int[] scores;
	/** 
	 * 핀처리 결과 출력용 플래그 값
	 */
	private final char[] clearedPinFlag;
	/**
	 * 프레임 총 득점
	 */
	private int total;
	/**
	 * 득점 결과가 확정되어 출력해도 되는 여부
	 */
	private boolean isScoreFinal;
	/**
	 * 프레임 식별자를 제외한 나머지 필드는 기본 값 세팅
	 * @param frameSeq 프레임 식별자
	 */
	Frame(int frameSeq){
		this.pinCount = 10;
		this.frameSeq = frameSeq;
		this.scores = new int[frameSeq < 9 ? 2 : 3];
		this.clearedPinFlag = new char[frameSeq < 9 ? 2 : 3];
		this.total = 0;		
	}
	/**
	 * 득점 결과 리턴
	 * @return 정수 배열 형태의 득점 결과
	 */
	public int[] getScores() {
		return scores;
	}	
	/**프레임 식별자 리턴
	 * 
	 * @return 정수 형태의 프레임 식별자
	 */
	public int getFrameSeq() {
		return frameSeq;
	}
	/**
	 * 프레임 당 2~3개의 플레이가 있고 각 플레이에 점수를 저장
	 * @param score 득점 점수
	 * @param index 프레임 내 플레이 인덱스
	 */
	public void setScore(int score, int index) {
		this.scores[index] = score;
	}
	/**프레임에서 첫 게임의 득점 결과를 리턴
	 * 
	 * @return 정수 형태의 득점
	 */
	public int getFirstGameScore() {
		return this.scores[0];
	}
	/**2~3 개 플레이를 종료하고 프레임에서 획득한 모든 점수 리턴
	 * 
	 * @return 프레임에서 득점한 총 점수
	 */
	public int getTotal(){
		return this.total;
	}
	/**2~3 개 플레이를 종료하고 프레임에서 획득한 모든 점수 저장
	 * 
	 * @param total 프레임에서 득점한 총 점수
	 */
	public void setTotal(int total){
		this.total = total;
	}
	/**프레임당 게임을 수행하는 동한 잔여 핀수 리턴
	 * 
	 * @return 프레임에서 남은 핀의 갯수
	 */
	public int getPinCount() {
		return pinCount;
	}
	/**프레임당 게임을 수행하는 동한 잔여 핀수 저장
	 * @param pinCount 프레임에서 남은 핀의 갯수
	 */
	public void setPinCount(int pinCount) {
		this.pinCount = pinCount;
	}
	/**파울, 스페어, 스트라이크 등 핀을 클리어한 결과 플래그를 리턴
	 * 
	 * @return 핀을 클리어한 상태 값
	 */
	public char[] getClearedPinFlag() {
		return clearedPinFlag;
	}
	/**파울, 스페어, 스트라이크 등 핀을 클리어한 결과 플래그를 각 플레이별 저장
	 * 
	 * @param i 플레이 순번
	 * @param c 클리어 결과 플래그
	 */
	public void setClearedPinFlag(int i, char c) {
		clearedPinFlag[i] = c;
	}
	/**점수가 최종 판정이 되어 출력 가능한지에 대한 플래그 리턴
	 * 
	 * @return 출력 여부 리턴
	 */
	public boolean isScoreFinal() {
		return isScoreFinal;
	}
	/**점수가 최종 판정이 되어 출력 가능한지에 대한 플래그 저장
	 * 
	 * @param isScoreFinal 출력 여부 저장
	 */
	public void setScoreFinal(boolean isScoreFinal) {
		this.isScoreFinal = isScoreFinal;
	}
	/**
	 * 디버그를 위한 프레임 정보 출력 포맷 지정 
	 */
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder(50);
		for(int s : scores){
			sb.append(String.valueOf(s)+", ");
		}
		return "frameSeq = "+frameSeq+" : scores = "+sb.toString()
				+" : clearedPinFlag = "+String.valueOf(clearedPinFlag)+" : pinCount = "+pinCount+" : total = "+total;
	}
}