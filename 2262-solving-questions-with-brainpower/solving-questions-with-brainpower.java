class Solution {
    public long mostPoints(int[][] questions) {
        int n = questions.length;
        long[] dp = new long[n + 1];  // +1 to avoid bounds checking when skipping past the end

        for (int i = n - 1; i >= 0; i--) {
            int points = questions[i][0];
            int skip = questions[i][1];

            // If we solve this question, we move to i + skip + 1
            long solve = points + (i + skip + 1 < n ? dp[i + skip + 1] : 0);

            // If we skip this question, we just take dp[i + 1]
            long skipIt = dp[i + 1];

            // Max of solving or skipping
            dp[i] = Math.max(solve, skipIt);
        }

        return dp[0];
    }
}
