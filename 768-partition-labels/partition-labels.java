class Solution {
    public List<Integer> partitionLabels(String s) {
        //Github -- vipulhere
        List<Integer> result = new ArrayList<>();
        int[] last = new int[26]; // since s contains only lowercase letters

        // Step 1: Store the last index of each character
        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i;
        }

        // Step 2: Greedy partitioning
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, last[s.charAt(i) - 'a']);
            if (i == end) {
                result.add(end - start + 1);
                start = i + 1;
            }
        }

        return result;
    }
}
