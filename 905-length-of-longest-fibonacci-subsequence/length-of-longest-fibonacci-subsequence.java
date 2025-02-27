class Solution {
    public int lenLongestFibSubseq(int[] arr) {
        //vipulhere
        // HashMap to store index of each element
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        int n = arr.length;
        int maxLength = 0;

        // 2D array to store length of Fibonacci-like subsequence
        int[][] dp = new int[n][n];

        // Populate index map with array elements
        for (int i = 0; i < n; i++) {
            indexMap.put(arr[i], i);
        }

        // Iterate over pairs of indices (j, i) where j < i
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int x = arr[i] - arr[j]; // The number that should exist before arr[j] for a valid Fibonacci sequence
                if (x < arr[j] && indexMap.containsKey(x)) {
                    int k = indexMap.get(x); // Get the index of x
                    dp[j][i] = dp[k][j] + 1; // Extend the length of the sequence
                } else {
                    dp[j][i] = 2; // Minimum length for any valid sequence
                }
                maxLength = Math.max(maxLength, dp[j][i]);
            }
        }

        // If maxLength is still 2, no valid sequence of length >= 3 was found
        return maxLength > 2 ? maxLength : 0;
    }
}