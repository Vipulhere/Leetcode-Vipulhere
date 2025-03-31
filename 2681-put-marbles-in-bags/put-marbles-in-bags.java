class Solution {
    public long putMarbles(int[] weights, int k) {
        int n = weights.length;
        if (k == 1 || k == n) return 0;

        List<Integer> pairSums = new ArrayList<>();
        //Github -- vipulhere
        // Calculate the cost of making a cut between each adjacent pair
        for (int i = 0; i < n - 1; i++) {
            pairSums.add(weights[i] + weights[i + 1]);
        }

        // Sort the pair sums
        Collections.sort(pairSums);

        long minSum = 0, maxSum = 0;
        for (int i = 0; i < k - 1; i++) {
            minSum += pairSums.get(i);
            maxSum += pairSums.get(pairSums.size() - 1 - i);
        }

        return maxSum - minSum;
    }
}
