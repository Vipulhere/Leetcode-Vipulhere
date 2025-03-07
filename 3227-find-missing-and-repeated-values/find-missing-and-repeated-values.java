class Solution {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        //github -- vipulhere
        int n = grid.length; // Get the dimension of the grid
        int size = n * n;    // The range of numbers is from 1 to n^2

        int[] count = new int[size + 1]; // Frequency array to count occurrences
        int repeated = -1, missing = -1;

        // Step 1: Count occurrences of each number in the grid
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                count[grid[i][j]]++; // Increment count for each number
            }
        }

        // Step 2: Identify the repeated and missing numbers
        for (int num = 1; num <= size; num++) {
            if (count[num] == 2) {
                repeated = num; // The number that appears twice
            } else if (count[num] == 0) {
                missing = num; // The number that is missing
            }
        }

        return new int[]{repeated, missing}; // Return the result as [a, b]
    }
}
