package com.alds.quiz.bgp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
/**
 * 1인용 볼링 게임 : 2인용 볼링 게임에 기본 로직을 제공하는 super class
 *
 */
class BowlingGame {
	/**
	 * 사용자 입력을 받기 위한 Reader
	 */
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	/**
	 * 스트라이크에 대한 플래그 값
	 */
	static final char STRIKE = 'X';
	/**
	 * 스페어에 대한 플래그 값
	 */
	static final char SPARE = '/';
	/**
	 * 거터에 대한 플래그 값
	 */
	static final char GUTTER = '-';
	/**
	 * 파울에 대한 플래그 값
	 */
	static final char FOUL = 'F';
	/**
	 * 기본 수행되는 프레임 갯수  :10개
	 */
	static final int FRAME_PER_GAME = 10;
	/**
	 * 게임에 포함된 프레임 갯수
	 */
	final int frameCount;
	/**
	 * 게임에서 수행되는 프레임들의 정보
	 */
	final List<Frame> frames;
	/**
	 * 스트라이크된  프레임들의 정보
	 */
	final Stack<Frame> strikedFrames = new Stack<Frame>();
	/**
	 * 스페어 처리된 프레임들의 정보
	 */
	final Stack<Frame> sparedFrames = new Stack<Frame>();
	/**
	 * constructor에 파라미터가 없으면 기본값으로 프레임 생성
	 */
	BowlingGame() {
		frames = new ArrayList<Frame>(FRAME_PER_GAME);
		frameCount = FRAME_PER_GAME;
	}
	/**
	 * constructor에서 숫자 정보를 입력 받는 경우에는 입력 받은 갯수 만큼의 프레임만 생성
	 * @param frameCount 생성할 프레임 갯수
	 */
	BowlingGame(int frameCount) {
		frames = new ArrayList<Frame>(frameCount);
		this.frameCount = frameCount;
	}
	/**1인 플레이 볼링게임
	 * 
	 * @throws IOException
	 */
	void playSinglePlayerGame() throws IOException {
		try{
			for (int i = 0; i < frameCount; ++i) {
				Frame frame = playFrame(i);
				frames.add(frame);
				showGameResult();
			}
		}finally{
			br.close();
		}
	}
	/**
	 * 한 프레임에 대한 게임 결과를 계산 및 저장
	 * @param frameSeq
	 * @return 프레임 당 게임 수행 결과
	 * @throws IOException
	 */
	Frame playFrame(int frameSeq) throws IOException {
		Frame frame = new Frame(frameSeq);
		calculateEachFrameScore(frame);
		/*
		 * 스페어 처리하거나 스트라이크된 프레임은 추후 재계산을 위해 별도 저장
		 */
		if (!sparedFrames.isEmpty()) {
			recalculateSparedFrame(frame, sparedFrames);
		}
		if (isStrikingScoreRecalNecessary(frame)
				&& !strikedFrames.isEmpty()) {
			recalculateStrikedFrame(frame, strikedFrames);
		}

		if (isFrameStriked(frame)) {
			strikedFrames.push(frame);
		} else if (isFrameSpared(frame)) {
			sparedFrames.push(frame);
		}
		return frame;
	}
	/**
	 * 현재 진행 중인 프레임에 대한 점수 계산
	 * @param currentframe
	 * @throws IOException
	 */
	void calculateEachFrameScore(Frame currentframe) throws IOException {		
		int gameCount = currentframe.getScores().length;
			for (int i = 0; i < gameCount; ++i) {
				char c = br.readLine().toCharArray()[0];
				/*
				 * 잔여 핀카운트 정보를 가지고 스페어 처리 시 활용
				 */
				int pinCount = currentframe.getPinCount();
				
				currentframe.setClearedPinFlag(i, c == '0' ? GUTTER : c);
				int score = (c != SPARE) ? convertInputToScore(c) : currentframe.getPinCount();
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
	 * 주어진 프레임의 스트라이크 처리 여부 확인
	 * @param frame
	 * @return 프레임이 스트라이크 처리됬는지 여부
	 */
	boolean isFrameStriked(Frame frame) {
		boolean striked = Boolean.FALSE;
		for (char c : frame.getClearedPinFlag()) {
			striked |= (c == STRIKE);
		}
		return striked;
	}
	/**
	 * 주어진 프레임의 스페어 처리 여부 확인
	 * @param frame
	 * @return 프레임이 스페어 처리됬는지 여부
	 */
	boolean isFrameSpared(Frame frame) {
		boolean spared = Boolean.FALSE;
		for (char c : frame.getClearedPinFlag()) {
			spared |= (c == SPARE);
		}
		return spared;
	}
	/**스트라이크로 저리된 프레임의 점수가 재계산 되어야 하는지 여부를 결정
	 * 스트라이크가 연속되면 재계산 필요
	 * @param frame 스트라이크로 처리된 프레임
	 * @return 재계산 필요 여부
	 */
	boolean isStrikingScoreRecalNecessary(Frame frame) {
		boolean isNecessary = Boolean.FALSE;
		if (!isFrameStriked(frame)) {
			isNecessary = Boolean.TRUE;
		} else {
			isNecessary = frame.getFrameSeq() == (frameCount - 1);// 마지막 프레임이면 점수 재걔산 필요없음
		}
		return isNecessary;
	}
	/**
	 * 점수가 고정되었는지 여부 결정
	 * @param frame 현재 프레임
	 * @return 스페어나 스트라이크가 아니면 점수 고정됨
	 */
	boolean isScoreFixed(Frame frame) {
		return !isFrameStriked(frame) && !isFrameSpared(frame);
	}
	/**입력값을 볼림점수로 변환
	 * 
	 * @param c 입력으로 들어온 캐릭터
	 * @return 점수화된 결과
	 */
	int convertInputToScore(char c) {
		int score = 0;
		if ('0' <= c && c <= '9') {
			score = (int) (c - '0');
		} else if (c == STRIKE) {
			return 10;
		} else if (c == FOUL || c == SPARE) {// SPARE는 잔여 핀수에 따라 점수가 결정되지만 초기값만 0으로 세팅 
			return 0;
		} else {
			throw new IllegalArgumentException(String.valueOf(c));
		}
		return score;
	}
	/**프레임당 총합을 계산
	 * 
	 * @param frame
	 * @return 프레임에서 얻은 총 점수
	 */
	int calculateFrameTotalScore(Frame frame) {
		int total = 0;
		for (int score : frame.getScores()) {
			total += score;
		}
		return total;
	}
	/**스페어 처리된 프레임들의 점수를 재계산
	 * 
	 * @param currentFrame 스페어 처리된 프레임에 보너스 점수를 넘겨주는 프레임
	 * @param sparedFrames 스페어 처리된 프레임들
	 */
	void recalculateSparedFrame(Frame currentFrame, Stack<Frame> sparedFrames) {
		for (; !sparedFrames.isEmpty();) {
			Frame sparedFrame = sparedFrames.pop();
			int recaledTotal = sparedFrame.getTotal() + currentFrame.getFirstGameScore();
			sparedFrame.setTotal(recaledTotal);
			sparedFrame.setScoreFinal(Boolean.TRUE);
		}
	}
	/**스트라이크 처리된 프레임들의 점수를 재계산
	 * 
	 * @param currentFrame 스트라이크 처리된 프레임에 보너스 점수를 넘겨주는 프레임
	 * @param strikedFrames 스트라이크 처리된 프레임들
	 */
	void recalculateStrikedFrame(final Frame currentFrame,
			Stack<Frame> strikedFrames) {
		Frame previousFrame = currentFrame;
		for (; !strikedFrames.isEmpty();) {
			Frame strikedFrame = strikedFrames.pop();
			// 프레임당 최대 허용 점수는 30점
			int recaledTotal = strikedFrame.getTotal() + previousFrame.getTotal() > 30 ? 30 : strikedFrame.getTotal() + previousFrame.getTotal();
			strikedFrame.setTotal(recaledTotal);
			previousFrame = strikedFrame;
			strikedFrame.setScoreFinal(Boolean.TRUE);
		}
	}
	/** 프레임당 점수 계산이 종료되는 조건들
	 * 
	 * @param currentframe 현제 프레임
	 * @param pinCount 잔여 핀수
	 * @param i 프레임 내 수행 중인 게임의 순서값
	 * @return 루프 종료 여부
	 */
	boolean isLoopEnd(Frame currentframe, int pinCount, int i){
		boolean isLoopEnd = Boolean.FALSE;
		if(currentframe.getFrameSeq() < 9 && pinCount <= 0){// 마지막 프레임이 아니면  SPARE처리를 위해 한 번더 기회가 있음
			isLoopEnd = Boolean.TRUE;
		}
		if(currentframe.getFrameSeq() == 9 && i == 1 && pinCount > 0){// 마지막 프레임의 마지막 게임인데 핀을 다 클리어 하지 못하면 종료
			isLoopEnd = Boolean.TRUE;
		}
		if(currentframe.getFrameSeq() == 9 && i == 1 && pinCount <= 0){// // 마지막 프레임인데 핀을 다 클리어 했으면 한 번 더 기회를 줌
			isLoopEnd = Boolean.FALSE;
			currentframe.setPinCount(10);// 핀이 모두 클리어 됬으므로 다시 핀 갯수를 채움
		}
		return isLoopEnd;
	}
	/**게임 수행 결과 출력
	 * 
	 */
	void showGameResult() {
		int total = 0;
		for (Frame f : frames) {
			total += f.getTotal();
			System.out.println((f.getFrameSeq() + 1) + " "
					+ String.valueOf(f.getClearedPinFlag()) + " "
					+ (f.isScoreFinal() ? total : ""));
		}
	}
}