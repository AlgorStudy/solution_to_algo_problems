package com.alds.quiz.bgp;

import java.io.IOException;

import org.junit.Test;
/**
 * 구현 결과 단위 테스트
 */
public class BowlingGameTestCase {

	/**
	 * 입력값 정상 변환 여부 확인
	 */
	@Test
	public void testScoreConversion() {
		char[] ca = "9-".toCharArray();
		BowlingGame bowlingGame = new BowlingGame();
		for(char c : ca){
			System.out.println(bowlingGame.convertInputToScore(c));
		}
	}
	/**
	 * 한 개 프레임 플레이 테스트
	 * @throws IOException
	 */
	@Test
	public void testPlayFrame() throws IOException{
		BowlingGame bowlingGame = new BowlingGame();
		Frame frame = new Frame(3);
		bowlingGame.calculateEachFrameScore(frame);
		System.out.println(frame);
	}
	/**
	 * 마지막 프레임 플레이 테스트
	 * @throws IOException
	 */
	@Test
	public void testPlayLastFrame() throws IOException{
		BowlingGame bowlingGame = new BowlingGame();
		Frame frame = new Frame(9);
		bowlingGame.calculateEachFrameScore(frame);
		System.out.println(frame);
	}
	
	/**
	 * 일인용 플레이 테스트
	 * @throws IOException
	 */
	@Test
	public void testSinglePlay() throws IOException{
		BowlingGame bowlingGame = new BowlingGame();
		bowlingGame.playSinglePlayerGame();
	}
}
