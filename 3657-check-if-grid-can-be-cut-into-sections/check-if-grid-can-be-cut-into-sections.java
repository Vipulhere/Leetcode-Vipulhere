class Solution {
    public boolean checkValidCuts(int n, int[][] rectangles) {
        int l = rectangles.length;
        //Github -- vipulhere
        // Separate intervals for horizontal and vertical cuts
        int[][] horizontal = new int[l][2];
        int[][] vertical = new int[l][2];
        
        for (int i = 0; i < l; i++) {
            // For horizontal: use x-coordinates (startX, endX)
            horizontal[i][0] = rectangles[i][0];
            horizontal[i][1] = rectangles[i][2];
            
            // For vertical: use y-coordinates (startY, endY)
            vertical[i][0] = rectangles[i][1];
            vertical[i][1] = rectangles[i][3];
        }
        
        // Sort intervals by their start positions
        Arrays.sort(horizontal, (a, b) -> a[0] - b[0]);
        Arrays.sort(vertical, (a, b) -> a[0] - b[0]);
        
        // Check if we can find valid cuts horizontally or vertically
        return findCuts(horizontal) || findCuts(vertical);
    }
    
    // Try to find two non-overlapping cuts that divide rectangles into 3 sections
    public boolean findCuts(int[][] axis) {
        int count = 0;   // Count of non-overlapping groups
        int cut = 1;     // Current end of merged interval, start from 1 (grid starts at 0)

        for (int[] curr : axis) {
            // If current rectangle starts after the last merged interval ends
            if (curr[0] >= cut) {
                count++;  // New section can begin
            }

            // Extend the merged interval to the furthest right/top seen so far
            cut = Math.max(cut, curr[1]);

            // If we found 2 such cuts, we have 3 sections
            if (count >= 2) {
                return true;
            }
        }
        
        return false;  // Not enough cuts to split into 3 non-empty sections
    }
}
