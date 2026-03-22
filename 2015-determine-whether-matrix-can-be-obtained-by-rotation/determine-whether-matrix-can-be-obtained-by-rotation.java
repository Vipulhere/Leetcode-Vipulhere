class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {
        for (int k = 0; k < 4; k++) {
            if (isSame(mat, target)) {
                return true;
            }
            mat = rotate(mat);
        }
        return false;
    }

    private boolean isSame(int[][] a, int[][] b) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] != b[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private int[][] rotate(int[][] mat) {
        int n = mat.length;
        int[][] res = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[j][n - 1 - i] = mat[i][j];
            }
        }

        return res;
    }
}