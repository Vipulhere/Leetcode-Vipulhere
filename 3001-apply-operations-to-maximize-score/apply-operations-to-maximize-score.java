import java.util.*;

class Solution {
    static final int MOD = 1_000_000_007;

    // Step 1: Count distinct prime factors (prime score)
    private int primeScore(int x) {
        Set<Integer> set = new HashSet<>();
        for (int i = 2; i * i <= x; i++) {
            while (x % i == 0) {
                set.add(i);
                x /= i;
            }
        }
        if (x > 1) set.add(x);
        return set.size();
    }

    public int maximumScore(List<Integer> numsList, int k) {
        int n = numsList.size();
        int[] nums = numsList.stream().mapToInt(i -> i).toArray();
        int[] score = new int[n];
        for (int i = 0; i < n; i++) {
            score[i] = primeScore(nums[i]);
        }

        // Step 2: Monotonic Stack for subarray ranges
        int[] left = new int[n];
        int[] right = new int[n];

        Stack<Integer> stack = new Stack<>();

        // Find right boundary
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && score[stack.peek()] < score[i]) {
                right[stack.pop()] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) right[stack.pop()] = n;

        stack.clear();

        // Find left boundary
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && score[stack.peek()] <= score[i]) {
                left[stack.pop()] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) left[stack.pop()] = -1;

        // Step 3: For each index, calculate how many subarrays it dominates
        long[] power = new long[n];
        for (int i = 0; i < n; i++) {
            power[i] = (long)(i - left[i]) * (right[i] - i);
        }

        // Step 4: Sort by nums[i] descending
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        Arrays.sort(idx, (a, b) -> Integer.compare(nums[b], nums[a]));

        // Step 5: Greedily pick top k subarrays
        long result = 1;
        for (int i = 0; i < n && k > 0; i++) {
            int j = idx[i];
            long count = Math.min(power[j], k);
            result = (result * modPow(nums[j], count, MOD)) % MOD;
            k -= count;
        }

        return (int) result;
    }

    // Fast modular exponentiation
    private long modPow(long base, long exp, int mod) {
        long res = 1;
        while (exp > 0) {
            if ((exp & 1) == 1)
                res = (res * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }
}
