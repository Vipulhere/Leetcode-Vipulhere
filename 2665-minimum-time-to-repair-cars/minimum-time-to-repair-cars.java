class Solution {
    public long repairCars(int[] ranks, int cars) {
        //Github -- vipulhere
        // Define the search space: min time (0) to max possible time
        long left = 1, right = (long) ranks[0] * (long) cars * (long) cars;

        // Perform binary search to find the minimum time required
        while (left < right) {
            long mid = left + (right - left) / 2; // Midpoint of search space

            if (canRepairAll(ranks, cars, mid)) {
                right = mid; // If possible within 'mid' time, reduce search space
            } else {
                left = mid + 1; // Increase time if not enough cars are repaired
            }
        }
        return left; // Minimum required time
    }

    // Helper function to check if we can repair 'cars' within 'time' minutes
    private boolean canRepairAll(int[] ranks, int cars, long time) {
        long repairedCars = 0;

        for (int rank : ranks) {
            long n = (long) Math.sqrt(time / rank); // Max cars this mechanic can repair
            repairedCars += n;

            if (repairedCars >= cars) {
                return true; // If total repaired cars reach required count, return true
            }
        }
        return false; // Otherwise, return false
    }
}
