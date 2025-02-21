class Solution {
    public int lengthOfLongestSubstring(String s) {
        //github - vipulhere
        int left = 0, maxLength = 0;
        HashSet<Character> set = new HashSet<>(); // HashSet to store unique characters in the window
        // Iterate over the string using the right pointer
        for (int right = 0; right < s.length(); right++) {
            // If duplicate character found, shrink the window from the left
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left)); // Remove leftmost character
                left++; // Move the left pointer forward
            }

            // Add the new character to the set
            set.add(s.charAt(right));

            // Update the max length of unique substring found so far
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength; // Return the maximum length found
    }
}