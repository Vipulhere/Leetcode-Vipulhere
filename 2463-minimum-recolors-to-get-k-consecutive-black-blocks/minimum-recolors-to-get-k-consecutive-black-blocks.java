class Solution {
    public int minimumRecolors(String blocks, int k) {
        // Github -- vipulhere
        int n = blocks.length();
        int minOperations = Integer.MAX_VALUE; // Initialize minimum operations to a very large number

        // Step 1: Traverse through the string and check for all substrings of length k
        for (int i = 0; i <= n - k; i++) {
            int operations = 0;

            // Step 2: Count the number of 'W' in the current substring of length k
            for (int j = i; j < i + k; j++) {
                if (blocks.charAt(j) == 'W') {
                    operations++; // Each 'W' will require one operation to turn into 'B'
                }
            }

            // Step 3: Update the minimum operations required
            minOperations = Math.min(minOperations, operations);
        }

        // Step 4: If we can't find any substring of length k with enough operations, return 0
        return minOperations == Integer.MAX_VALUE ? 0 : minOperations;
    }
}
