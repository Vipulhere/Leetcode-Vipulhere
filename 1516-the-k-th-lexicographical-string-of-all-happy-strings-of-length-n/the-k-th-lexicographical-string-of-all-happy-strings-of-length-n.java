class Solution {
    public String getHappyString(int n, int k) {
        //github - vipulhere
        List<String> happyStrings = new ArrayList<>();
        backtrack(happyStrings, new StringBuilder(), n, ' ');
        
        // If k is larger than the available happy strings, return an empty string
        return k > happyStrings.size() ? "" : happyStrings.get(k - 1);
    }

    private void backtrack(List<String> happyStrings, StringBuilder sb, int n, char lastChar) {
        // Base case: If we reach length n, add to the list
        if (sb.length() == n) {
            happyStrings.add(sb.toString());
            return;
        }

        // Try adding 'a', 'b', and 'c' while ensuring consecutive characters are different
        for (char c : new char[]{'a', 'b', 'c'}) {
            if (c != lastChar) {
                sb.append(c);
                backtrack(happyStrings, sb, n, c);
                sb.deleteCharAt(sb.length() - 1); // Backtrack
            }
        }
    }
}