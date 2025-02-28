class Solution {
    public String shortestCommonSupersequence(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        //vipulhere
        // Step 1: Compute the Longest Common Subsequence (LCS)
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Step 2: Build the shortest common supersequence using the LCS table
        StringBuilder result = new StringBuilder();
        int i = m, j = n;

        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                result.append(str1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                result.append(str1.charAt(i - 1));
                i--;
            } else {
                result.append(str2.charAt(j - 1));
                j--;
            }
        }

        // Append remaining characters from str1
        while (i > 0) {
            result.append(str1.charAt(i - 1));
            i--;
        }

        // Append remaining characters from str2
        while (j > 0) {
            result.append(str2.charAt(j - 1));
            j--;
        }

        // Reverse the string as we built it backwards
        return result.reverse().toString();
    }
}