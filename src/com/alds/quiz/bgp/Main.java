package com.alds.quiz.bgp;

import java.io.IOException;
/**
 * 
 * 2인 플레이 볼링게임 실행 클래스
 *
 */
public class Main {
	public static void main(String[] args) throws IOException{
		BowlingGame playA = new MultipleBowlingGame("playA");
		BowlingGame playB = new MultipleBowlingGame("playB");
		for(int i = 0 ; i < 10 ; ++i){
			playA.playFrame(i);
			playA.showGameResult();
			playB.playFrame(i);
			playB.showGameResult();
		}
	}
}





