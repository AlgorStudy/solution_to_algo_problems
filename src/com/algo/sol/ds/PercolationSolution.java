package com.algo.sol.ds;

import java.util.Random;

public class PercolationSolution {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int matrixSize = 11;
		Percolation percolation = new Percolation(matrixSize);
		percolation.displayMatrix();
		System.out.printf("%d X %d matrix percolation ratio : %2.2f",
				matrixSize, matrixSize, percolation.getPercolationRatio());
	}

}

class Percolation {
	private static final boolean OPENED = Boolean.TRUE;
	private static final boolean CLOSED = Boolean.FALSE;

	private boolean[][] matrix;
	private int matrixSize;
	private WeightedQuickUnionUF weightedUnionFinder;
	private Random rnd;

	Percolation(int matrixSize) {
		if (matrixSize <= 0) {
			throw new IllegalArgumentException(
					"matrix size must be positive integer");
		}
		this.matrix = new boolean[matrixSize][matrixSize];
		this.weightedUnionFinder = new WeightedQuickUnionUF(matrixSize
				* matrixSize);
		this.matrixSize = matrixSize;
		this.rnd = new Random(43);
		initializeMatrix();
	}

	private void initializeMatrix() {
		for (int i = 0; i < matrixSize; i++) {
			for (int j = 0; j < matrixSize; ++j) {
				matrix[i][j] = CLOSED;
			}
		}
	}

	double getPercolationRatio() {
		int count = 0;
		for (; !isNetworkConnected(); ++count) {
			openRandomSite();
			displayMatrix();
		}
		return ((double) count / (double) (matrixSize * matrixSize)) * 100;
	}

	private boolean isNetworkConnected() {
		boolean openedOrClosed = CLOSED;
		for (int i = 0; i < matrixSize; ++i) {
			int topLayerSiteId = generateUniqueId(0, i);
			for (int j = 0; j < matrixSize; ++j) {
				int bottomLayerSiteId = generateUniqueId(matrixSize - 1, j);
				openedOrClosed |= weightedUnionFinder.connected(topLayerSiteId,
						bottomLayerSiteId);
				if (openedOrClosed) {
					break;
				}
			}
		}
		return openedOrClosed;
	}

	private int generateUniqueId(int i, int j) {
		return matrixSize * i + j;
	}

	private void openRandomSite() {
		int i = -1, j = -1;
		do {
			i = rnd.nextInt(matrixSize);
			j = rnd.nextInt(matrixSize);
		} while (matrix[i][j]);

		matrix[i][j] = OPENED;
		/*
		 *                  matrix[i-1][j]
		 *                        | 
		 *   matrix[i][j-1] - matrix[i][j] - matrix[i][j+1]
		 *                        |
		 *                  matrix[i+1][j]
	     *   check surrounding sites and connect if one of them is opened
		 */
		if (i < (matrixSize - 1) && matrix[i + 1][j]) {
			weightedUnionFinder.union(generateUniqueId(i, j),
					generateUniqueId(i + 1, j));
		}
		if (i > 0 && matrix[i - 1][j]) {
			weightedUnionFinder.union(generateUniqueId(i, j),
					generateUniqueId(i - 1, j));
		}
		if (j < (matrixSize - 1) && matrix[i][j + 1]) {
			weightedUnionFinder.union(generateUniqueId(i, j),
					generateUniqueId(i, j + 1));
		}
		if (j > 0 && matrix[i][j - 1]) {
			weightedUnionFinder.union(generateUniqueId(i, j),
					generateUniqueId(i, j - 1));
		}
	}

	void displayMatrix() {
		for (int i = 0; i < matrixSize; i++) {
			for (int j = 0; j < matrixSize; ++j) {
				System.out.printf("%8s", matrix[i][j] ? "OPENED" : "CLOSED");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
}

/**  
 * The <tt>WeightedQuickUnionUF</tt> class represents a
 * union-find data structure. It supports the <em>union</em> and <em>find</em>
 * operations, along with methods for determinig whether two objects are in the
 * same component and the total number of components.
 * <p>
 * This implementation uses weighted quick union by size (without path
 * compression). Initializing a data structure with <em>N</em> objects takes
 * linear time. Afterwards, <em>union</em>, <em>find</em>, and
 * <em>connected</em> take logarithmic time (in the worst case) and
 * <em>count</em> takes constant time.
 * <p>
 * For additional documentation, see <a
 * href="http://algs4.cs.princeton.edu/15uf">Section 1.5</a> of <i>Algorithms,
 * 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 * 
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
class WeightedQuickUnionUF {
	private int[] id; // id[i] = parent of i
	private int[] sz; // sz[i] = number of objects in subtree rooted at i
	private int count; // number of components

	/**
	 * Initializes an empty union-find data structure with N isolated components
	 * 0 through N-1.
	 * 
	 * @throws java.lang.IllegalArgumentException
	 *             if N < 0
	 * @param N
	 *            the number of objects
	 */
	public WeightedQuickUnionUF(int N) {
		count = N;
		id = new int[N];
		sz = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
			sz[i] = 1;
		}
	}

	/**
	 * Returns the number of components.
	 * 
	 * @return the number of components (between 1 and N)
	 */
	public int count() {
		return count;
	}

	/**
	 * Returns the component identifier for the component containing site
	 * <tt>p</tt>.
	 * 
	 * @param p
	 *            the integer representing one site
	 * @return the component identifier for the component containing site
	 *         <tt>p</tt>
	 * @throws java.lang.IndexOutOfBoundsException
	 *             unless 0 <= p < N
	 */
	public int find(int p) {
		while (p != id[p])
			p = id[p];
		return p;
	}

	/**
	 * Are the two sites <tt>p</tt> and <tt>q</tt> in the same component?
	 * 
	 * @param p
	 *            the integer representing one site
	 * @param q
	 *            the integer representing the other site
	 * @return <tt>true</tt> if the two sites <tt>p</tt> and <tt>q</tt> are in
	 *         the same component, and <tt>false</tt> otherwise
	 * @throws java.lang.IndexOutOfBoundsException
	 *             unless both 0 <= p < N and 0 <= q < N
	 */
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	/**
	 * Merges the component containing site<tt>p</tt> with the component
	 * containing site <tt>q</tt>.
	 * 
	 * @param p
	 *            the integer representing one site
	 * @param q
	 *            the integer representing the other site
	 * @throws java.lang.IndexOutOfBoundsException
	 *             unless both 0 <= p < N and 0 <= q < N
	 */
	public void union(int p, int q) {
		int rootP = find(p);
		int rootQ = find(q);
		if (rootP == rootQ)
			return;

		// make smaller root point to larger one
		if (sz[rootP] < sz[rootQ]) {
			id[rootP] = rootQ;
			sz[rootQ] += sz[rootP];
		} else {
			id[rootQ] = rootP;
			sz[rootP] += sz[rootQ];
		}
		count--;
	}

}
