package com.alds.quiz.assign1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Assignment1 {
	private static final BufferedReader br = new BufferedReader(
			new InputStreamReader(System.in));

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	private static void releaseBufferedReader() throws IOException {
		br.close();
	}
}


class CupPyramid {
	private final int size;
	private final double[] cupList;

	CupPyramid(int size) {
		cupList = new double[size];
		this.size = size;
	}

	void pourWaterIntoCupPyramid(int waterMount) {
		int currentLayer = 1;
		for (int i = 0; i < waterMount; ++i) {
			double flowSize = getFlowSizeAtEachLayer(currentLayer);
		}
	}

	private double getFlowSizeAtEachLayer(int layer) {
		double defaultFlowSize = 1.0;
		for (int i = 1; i <= layer; ++i) {
			defaultFlowSize /= 2;
		}
		return defaultFlowSize;
	}

	private int getLayerStartPoint(int l) {
		return ((l * l) - l + 2) / 2;
	}

	private int isLayerEndPoint(int l) {
		return ((l * l) + l) / 2;
	}
}