package test142;

import base.ListNode;

/**
 * 142.求环的入口
 * https://leetcode-cn.com/problems/linked-list-cycle-ii/
 * @author zwj
 * @date 2021/4/25
 */
public class Solution {

    public ListNode detectCycle(ListNode head) {
        if (head==null || head.next==null){
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast!=null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (fast==slow){
                break;
            }
        }
        if (slow == null || fast ==null){
            return null;
        }

        slow = head;
        while (slow!=null && fast !=null && slow !=fast){
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }

}
