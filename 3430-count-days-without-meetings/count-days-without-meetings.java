class Solution {
    public int countDays(int days, int[][] meetings) {
        //Github -- vipulhere
        // Sort the meetings based on start time
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));

        // Initialize with the first meeting's start and end
        int mergedStart = meetings[0][0];
        int mergedEnd = meetings[0][1];
        int totalMeetingDays = 0;

        // Iterate through remaining meetings
        for (int i = 1; i < meetings.length; i++) {
            int start = meetings[i][0];
            int end = meetings[i][1];

            // If current meeting overlaps or touches the merged interval
            if (start <= mergedEnd) {
                mergedEnd = Math.max(mergedEnd, end); // Extend the merged interval
            } else {
                // No overlap: add the previous merged interval length
                totalMeetingDays += (mergedEnd - mergedStart + 1);

                // Start a new merged interval
                mergedStart = start;
                mergedEnd = end;
            }
        }

        // Add the final merged interval's length
        totalMeetingDays += (mergedEnd - mergedStart + 1);

        // Subtract meeting days from total days to get free days
        return days - totalMeetingDays;
    }
}