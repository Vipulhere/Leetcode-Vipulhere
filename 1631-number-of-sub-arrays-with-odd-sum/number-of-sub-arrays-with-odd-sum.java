class Solution {
    public int numOfSubarrays(int[] arr) {
        //vipulhere
        final int MOD = 1000000007;  // Modulo for the result to avoid overflow
        int oddCount = 0, evenCount = 1;  // evenCount is initialized to 1 for the case where the sum of no elements is even
        int currentSum = 0;
        int result = 0;

        // Traverse the array to find all subarrays with an odd sum
        for (int num : arr) {
            // Update the current sum
            currentSum += num;

            // If the current sum is odd, add the number of even subarrays to the result
            if (currentSum % 2 != 0) {
                result += evenCount;  // Add count of even subarrays (as odd + even = odd)
                oddCount++;  // Increase odd count as we encountered an odd sum
            } else {
                result += oddCount;  // Add count of odd subarrays (as even + odd = odd)
                evenCount++;  // Increase even count as we encountered an even sum
            }

            // Ensure the result is modulo 10^9 + 7
            result %= MOD;
        }

        return result;
    }
}