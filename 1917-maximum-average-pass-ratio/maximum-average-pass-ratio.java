class Solution {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        // Custom comparator: choose class with maximum gain
        PriorityQueue<double[]> pq = new PriorityQueue<>(
            (a, b) -> Double.compare(b[0], a[0]) // max-heap by gain
        );

        // Fill the heap with {gain, pass, total}
        for (int[] c : classes) {
            int pass = c[0], total = c[1];
            pq.offer(new double[]{gain(pass, total), pass, total});
        }

        // Assign extra students
        while (extraStudents-- > 0) {
            double[] top = pq.poll();
            int pass = (int) top[1] + 1;
            int total = (int) top[2] + 1;
            pq.offer(new double[]{gain(pass, total), pass, total});
        }

        // Calculate final average pass ratio
        double sum = 0.0;
        while (!pq.isEmpty()) {
            double[] cur = pq.poll();
            int pass = (int) cur[1];
            int total = (int) cur[2];
            sum += (double) pass / total;
        }

        return sum / classes.length;
    }

    private double gain(int pass, int total) {
        return (double)(pass + 1) / (total + 1) - (double) pass / total;
    }
}
