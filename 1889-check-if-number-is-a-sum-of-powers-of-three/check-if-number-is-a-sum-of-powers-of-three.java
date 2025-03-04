class Solution {
    public boolean checkPowersOfThree(int n) {
        while (n > 0) {
            if (n % 3 == 2) {
                return false; // If remainder is 2, n cannot be represented as sum of distinct powers of three
            }
            n /= 3; // Reduce n by dividing it by 3
        }
        return true;
    }
}