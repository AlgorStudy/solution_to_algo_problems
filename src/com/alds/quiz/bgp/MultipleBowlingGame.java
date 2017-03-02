package com.alds.quiz.bgp;

import java.io.IOException;
import java.util.Random;
/**
 * 
 * 2명 이상 플레이를 위한 볼링 게임 클래스
 *
 */
public class MultipleBowlingGame extends BowlingGame{
	/**
	 * 사용자 입력을 대신하는 난수 발생기
	 */
	private final Random rand;
	/**
	 * 사용자 아이디
	 */
	private final String playerId; 
	MultipleBowlingGame(String playerId){
		super();
		this.rand = new Random(System.nanoTime());
		this.playerId = playerId;
	}
	/**
	 * 2명 이상 플레이시 입력을 난수에서 받아서 프레임 당 게임을 수행
	 */
	@Override
	void calculateEachFrameScore(Frame currentframe){
		int gameCount = currentframe.getScores().length;
		for(int i = 0; i < gameCount; ++i){
			int pinCount = currentframe.getPinCount();
			char c = generateAutoInput(pinCount, i);
			
			currentframe.setClearedPinFlag(i, c == '0' ? GUTTER : c);
			int score = (c != SPARE) ? convertInputToScore(c) : currentframe.getPinCount();// SPARE가 입력으로 들어오면 잔여 핀을 점수화
			currentframe.setScore(score, i);
			
			if(i < 2){// 마지막 프레임은 보너스 게임까지 세번의 기회가 있지만, 핀갯수는 2게임에 총 10개가 기본
				currentframe.setPinCount(pinCount -= score);
				if(i == 1 && c != SPARE && currentframe.getPinCount() == 0){// 프레임 중 두번째 게임에서 잔여핀이 모두 처리되면 스페어로 마킹
					currentframe.setClearedPinFlag(i, SPARE);
				}
			}
			if(isLoopEnd(currentframe, pinCount, i)){
				break;
			}else{
				continue;
			}
		}
		int totalScore = calculateFrameTotalScore(currentframe);
		currentframe.setTotal(totalScore);
		currentframe.setScoreFinal(currentframe.getFrameSeq() < (frameCount - 1) ? isScoreFixed(currentframe) : Boolean.TRUE);// 마지막 프레임이면 무조건 점수 확정
	}
	/**
	 * 싱글플레이의 프레임당 게임을 수행하고 결과를 저장
	 */
	@Override
	Frame playFrame(int frameSeq) throws IOException {
		Frame playFrame = super.playFrame(frameSeq);
		frames.add(playFrame);
		return playFrame;
	}
	
	/**핀 값에 따라 입력값 유효성을 체크하고 재생성
	 * 
	 * @param pinCount 잔여 핀 값
	 * @return 난수에서 생성된 입력값
	 */
	private char generateAutoInput(int pinCount, int gameSeq){
		char c = convertRandNumberToFlag();
		while(gameSeq == 0 && pinCount == 10 && c == SPARE){// 게임이 플레이되지 않았는데 스페어 처리는 불가
			c = convertRandNumberToFlag();
		}
		while(pinCount < 10 && convertInputToScore(c) > pinCount){// 핀 갯수 보다 크게 입력 발생 불가
			c = convertRandNumberToFlag();
		}
		return c;
	}
	/** 난수를 유효한 입력값으로 변환
	 * 
	 * @return 0~9, 스트라이크, 스페어, 파울 등 입력 플래그
	 */
	private char convertRandNumberToFlag() {
		int rndNumber = rand.nextInt(13);
		if (rndNumber < 10) {
			return (char) (rndNumber + 48);
		} else if (rndNumber == 10) {
			return STRIKE;
		} else if (rndNumber == 11) {
			return FOUL;
		} else {
			return SPARE;
		}
	}
	/**
	 * 2인 이상 플레이시 플레이어 아이디를 출력에 포함
	 */
	@Override
	void showGameResult() {
		int total = 0;
		for (Frame f : frames) {
			total += f.getTotal();
			System.out.println(playerId+" : "+(f.getFrameSeq() + 1) + " "
					+ String.valueOf(f.getClearedPinFlag()) + " "
					+ (f.isScoreFinal() ? total : ""));
		}
	}
}
