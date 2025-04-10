class Solution {
    private String targetSuffix;   // Suffix string which must be at the end of number
    private String limitNumber;    // Current upper bound number as string (start-1 or finish)
    private Long[] dp;             // Memoization array for storing results
    private int maxDigit;          // Maximum allowed digit (digit limit)

    public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        this.targetSuffix = s;
        this.maxDigit = limit;

        // Count numbers from 1 to finish
        limitNumber = String.valueOf(finish);
        dp = new Long[20]; // Reset DP array
        long countFinish = countNumbers(0, true);

        // Count numbers from 1 to start-1
        limitNumber = String.valueOf(start - 1);
        dp = new Long[20]; // Reset DP array again
        long countStart = countNumbers(0, true);

        // Final answer = Count from start to finish
        return countFinish - countStart;
    }

    // Recursive Digit DP Function
    private long countNumbers(int pos, boolean isLimit) {
        int totalLen = limitNumber.length();
        int suffixLen = targetSuffix.length();

        // If length of remaining number is less than suffix length -> Invalid
        if (totalLen < suffixLen) return 0;

        // Base Case: Only suffix length part left
        if (totalLen - pos == suffixLen) {
            String remaining = limitNumber.substring(pos);
            // If not tight or suffix <= remaining part of limitNumber
            if (!isLimit || targetSuffix.compareTo(remaining) <= 0) {
                return 1;
            }
            return 0;
        }

        // Memoization check
        if (!isLimit && dp[pos] != null) {
            return dp[pos];
        }

        long res = 0;
        int upperBound = isLimit ? limitNumber.charAt(pos) - '0' : 9;
        upperBound = Math.min(upperBound, maxDigit);

        for (int digit = 0; digit <= upperBound; digit++) {
            boolean nextLimit = isLimit && (digit == limitNumber.charAt(pos) - '0');
            res += countNumbers(pos + 1, nextLimit);
        }

        if (!isLimit) {
            dp[pos] = res;
        }
        return res;
    }
}