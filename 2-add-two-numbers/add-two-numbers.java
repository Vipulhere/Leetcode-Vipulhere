/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //git - vipulhere
        // Dummy node to simplify handling the head of the result list
        ListNode dummy = new ListNode(0);
        ListNode current = dummy; // Pointer to build the new list
        int carry = 0; // Variable to store carry-over value

        // Traverse both lists until both are exhausted
        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry; // Start with the carry value

            // Add the value from l1 if it exists
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next; // Move to the next node
            }

            // Add the value from l2 if it exists
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next; // Move to the next node
            }

            // Compute new digit and carry
            carry = sum / 10; // Carry for next iteration
            int newDigit = sum % 10; // Current digit

            // Create new node with the computed digit
            current.next = new ListNode(newDigit);
            current = current.next; // Move pointer forward
        }

        // Return the next of dummy node (actual result list)
        return dummy.next;
    }

}