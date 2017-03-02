package com.alds.quiz.fix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AlgoProblemRatingAndOrder {
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		try {
			int testNumber = InputProcessor.getTestNumber();
			List<List<AlgoProblem>> problSets = new ArrayList<List<AlgoProblem>>(
					testNumber);
			for (int i = testNumber; i > 0; --i) {
				int problemNumber = InputProcessor.getProblemNumber();
				problSets.add(InputProcessor.getAlgorithmProSet(problemNumber));
			}
			for (List<AlgoProblem> algorithmProSet : problSets) {
				InputProcessor.findNotMovedProbs(algorithmProSet);
			}
		} finally {
			InputProcessor.closeBufferedReader();
		}
	}

}
class InputProcessor {
	private static final BufferedReader br = new BufferedReader(
			new InputStreamReader(System.in));
	private static final Comparator<AlgoProblem> ALGO_PROB_COMPA = new Comparator<AlgoProblem>() {
		@Override
		public int compare(AlgoProblem o1, AlgoProblem o2) {
			// TODO Auto-generated method stub
			return o1.getRating() - o2.getRating();
		}
	};

	private InputProcessor() {
	}

	static int getTestNumber() throws IOException {
		String readLine = br.readLine();
		int testNumber = Integer.parseInt(readLine);
		if (testNumber < 0 || testNumber > 6) {
			throw new IllegalArgumentException("1~5");
		}
		return testNumber;
	}

	static int getProblemNumber() throws IOException {
		String readLine = br.readLine();
		int problemCount = Integer.parseInt(readLine);
		if (problemCount < 10 || problemCount > 12) {
			throw new IllegalArgumentException("10<=size<=12");
		}
		return problemCount;
	}

	static List<AlgoProblem> getAlgorithmProSet(int problemNumber)
			throws IOException {
		String readLine = br.readLine();
		String[] algoRatings = readLine.split(" ");
		int probCount = algoRatings.length;
		if (problemNumber != probCount) {
			throw new IllegalArgumentException("problem number != array size");
		}
		validateProblemRatings(algoRatings);
		List<AlgoProblem> problemSet = new ArrayList<AlgoProblem>(probCount);
		for (int i = 0; i < probCount; ++i) {
			AlgoProblem temp = new AlgoProblem();
			temp.setRating(Integer.parseInt(algoRatings[i]));
			temp.setProNum(i);
			temp.setRankingByRating(-1);
			problemSet.add(temp);
		}
		return problemSet;
	}

	private static void validateProblemRatings(String[] probl) {
		int size = probl.length;
		if (size < 10 || size > 12) {
			throw new IllegalArgumentException("10<=size<=12");
		}
		for (int i = 0; i < size; ++i) {
			String tempRating = probl[i];
			if (!tempRating.matches("^[0-9]+$")) {
				throw new IllegalArgumentException("not int");
			}
			for (int j = 0; j < size; ++j) {
				if (i != j && tempRating.equals(probl[j])) {
					throw new IllegalArgumentException("dup");
				}
			}
		}
	}

	static void findNotMovedProbs(List<AlgoProblem> probList) {
		Collections.sort(probList, ALGO_PROB_COMPA);
		final int size = probList.size();
		for (int i = 0; i < size; ++i) {
			AlgoProblem temp = probList.get(i);
			// System.out.print(temp);
			// System.out.print(" ");
			temp.setRankingByRating(i);
		}
		int count = 0;
		for (AlgoProblem t : probList) {
			if (t.getProNum() == t.getRankingByRating()) {
				count++;
			}
		}
		System.out.println(count);
	}

	/*
	 * private static void quickSort(List<AlgoProblem> problSet){ AlgoProblem[]
	 * snapShot = (AlgoProblem[])problSet.toArray();
	 * 
	 * }
	 */
	static void closeBufferedReader() throws IOException {
		br.close();
	}
}
class AlgoProblem {
	private int rating;
	private int proNum;
	private int rankingByRating;

	public int getRating() {
		return rating;
	}

	public int getProNum() {
		return proNum;
	}

	public int getRankingByRating() {
		return rankingByRating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public void setProNum(int proNum) {
		this.proNum = proNum;
	}

	public void setRankingByRating(int rankingByRating) {
		this.rankingByRating = rankingByRating;
	}

	@Override
	public String toString() {
		return "{ " + this.rating + ", " + this.proNum + ", "
				+ this.rankingByRating + " }";
	}
}
