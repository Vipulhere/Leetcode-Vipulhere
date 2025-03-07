class Solution {
    public int[] closestPrimes(int left, int right) {
        //github -- vipulhere   
        // Define the upper limit for prime number generation
        int LIMIT = 1000000;
        
        // Step 1: Generate prime numbers using Sieve of Eratosthenes
        boolean[] isPrime = new boolean[LIMIT + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false; // 0 and 1 are not prime

        for (int i = 2; i * i <= LIMIT; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= LIMIT; j += i) {
                    isPrime[j] = false; // Mark multiples of i as non-prime
                }
            }
        }

        // Step 2: Collect all prime numbers within the range [left, right]
        List<Integer> primes = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }

        // Step 3: Find the closest pair of prime numbers
        if (primes.size() < 2) {
            return new int[]{-1, -1}; // If less than 2 primes exist, return [-1, -1]
        }

        int minDiff = Integer.MAX_VALUE;
        int[] result = new int[2];

        for (int i = 1; i < primes.size(); i++) {
            int diff = primes.get(i) - primes.get(i - 1);
            if (diff < minDiff) {
                minDiff = diff;
                result[0] = primes.get(i - 1);
                result[1] = primes.get(i);
            }
        }

        return result;
    }
}
