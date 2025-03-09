class Solution {
    public int numberOfAlternatingGroups(int[] colors, int k) {
        //Github -- vipulhere
        int n = colors.length;
        int count = 0;
        int alternatingCount = 0;

        // Step 1: Count alternating pairs in the first window of size k
        for (int i = 0; i < k - 1; i++) {
            if (colors[i] != colors[i + 1]) {
                alternatingCount++;
            }
        }

        // Step 2: Use sliding window to count alternating groups efficiently
        for (int i = 0; i < n; i++) {
            // Get the end index in a circular manner
            int j = (i + k - 1) % n;

            // Add new alternating pair at end of window
            if (colors[j] != colors[(j + 1) % n]) {
                alternatingCount++;
            }

            // Remove the first alternating pair of the previous window
            if (colors[i] != colors[(i + 1) % n]) {
                alternatingCount--;
            }

            // If we found an alternating group of size k, increase count
            if (alternatingCount == k - 1) {
                count++;
            }
        }

        return count;
    }
}

