class Solution {
    public String smallestNumber(String pattern) {
        //git-vipulhere
        StringBuilder result = new StringBuilder();
        Stack<Integer> stack = new Stack<>();

        // Iterate through the pattern, including an extra iteration for the last digit
        for (int i = 0; i <= pattern.length(); i++) {
            stack.push(i + 1); // Push numbers from 1 to n+1 onto the stack

            // If we reach the end or encounter an 'I', start popping from the stack
            if (i == pattern.length() || pattern.charAt(i) == 'I') {
                while (!stack.isEmpty()) {
                    result.append(stack.pop()); // Append popped numbers to the result
                }
            }
        }

        return result.toString(); // Convert result to a string and return
    }

}