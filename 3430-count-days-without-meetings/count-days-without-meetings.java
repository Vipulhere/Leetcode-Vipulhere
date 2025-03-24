import java.util.*;

class Solution {
    public int countDays(int days, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));

        int mergedStart = meetings[0][0];
        int mergedEnd = meetings[0][1];
        int totalMeetingDays = 0;

        for (int i = 1; i < meetings.length; i++) {
            int start = meetings[i][0];
            int end = meetings[i][1];

            if (start <= mergedEnd) {
                mergedEnd = Math.max(mergedEnd, end);
            } else {
                totalMeetingDays += (mergedEnd - mergedStart + 1);
                mergedStart = start;
                mergedEnd = end;
            }
        }

        totalMeetingDays += (mergedEnd - mergedStart + 1);
        return days - totalMeetingDays;
    }
}
