class Solution {
    public int countGoodNumbers(long n) {
        //Github -- Vipulhere
        int mod = 1000000007;
        long evenCount = (n + 1) / 2;
        long oddCount = n / 2;
        
        long result = (power(5, evenCount, mod) * power(4, oddCount, mod)) % mod;
        return (int) result;
    }
    
    private long power(long base, long exp, int mod) {
        long result = 1;
        base %= mod;
        
        while (exp > 0) {
            if (exp % 2 == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            exp /= 2;
        }
        return result;
    }
}