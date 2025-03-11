class Solution {
    public int numberOfSubstrings(String s) {
        //github--vipulhere
        // Initialize count of characters a, b, and c
        int[] count = new int[3];  // count[0] for 'a', count[1] for 'b', count[2] for 'c'
        int start = 0;
        int result = 0;

        // Iterate over the string with the end pointer
        for (int end = 0; end < s.length(); end++) {
            // Update count for the current character
            count[s.charAt(end) - 'a']++;

            // Move the start pointer to shrink the window when all three characters are present
            while (count[0] > 0 && count[1] > 0 && count[2] > 0) {
                // All characters are present, calculate the number of substrings
                result += s.length() - end;
                count[s.charAt(start) - 'a']--;
                start++;
            }
        }

        return result;
    }
}
