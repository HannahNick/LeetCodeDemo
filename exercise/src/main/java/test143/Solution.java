package test143;

import base.ListNode;

/**
 * 求链表环的长度
 * @author zwj
 * @date 2021/4/25
 */
public class Solution {

    public int detectCycle(ListNode head) {
        if (head==null || head.next==null){
            return -1;
        }

        ListNode slow = head;
        ListNode fast = head;
        while (fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                break;
            }
        }
        //这里表示没有环
        if (slow==null || fast==null){
            return -1;
        }
        slow = head;
        int count = 0;

        while (slow!=null){
            slow = slow.next;
            count++;
            if (slow==fast){
                break;
            }
        }
        return count;
    }
}
