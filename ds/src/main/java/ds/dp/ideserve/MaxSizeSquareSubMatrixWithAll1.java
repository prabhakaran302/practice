package ds.dp.ideserve;

public class MaxSizeSquareSubMatrixWithAll1 {
	public static void main(String[] args) {
		int matrix[][] = { { 1, 1, 0, 0, 1, 1 }, { 0, 0, 1, 0, 1, 1 }, { 1, 1, 1, 1, 1, 0 }, { 1, 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 1, 1, 1 }, { 0, 1, 1, 1, 1, 1 }, { 1, 0, 0, 0, 1, 1 } };
		System.out.println(maximumSizeSquareSubmatrixWithAllOnes(matrix));
	}

	private static int maximumSizeSquareSubmatrixWithAllOnes(int[][] matrix) {
		int row = matrix.length + 1;
		int col = matrix[0].length + 1;
		int res[][] = new int[row][col];
		for (int i = 0; i < row; i++)
			res[i][0] = 0;
		for (int i = 0; i < col; i++)
			res[0][i] = 0;

		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				if (matrix[i - 1][j - 1] == 0) {
					res[i][j] = 0;
				} else {
					res[i][j] = Math.min(res[i - 1][j - 1], Math.min(res[i - 1][j], res[i][j - 1]));
				}
			}
		}
		return res[row][col];
	}
}
