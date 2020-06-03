package test287;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 *  https://leetcode-cn.com/problems/find-the-duplicate-number/
 *  寻找重复数
 * @author zwj
 * @date 2020/5/26
 */
public class FindDuplicateDemo {


    public static void main(String[] args) {
        int[] a = {1, 2, 5, 9, 11,22,51,88};
        int[] b = {3,1,3,4,2};

        System.out.println(findDuplicate2(a,11));
    }

    public static int findDuplicate(int[] nums) {
        HashSet<Integer> hashSet = new HashSet<Integer>();
        for (int num : nums) {
            if (hashSet.contains(num)) {
                return num;
            }
            hashSet.add(num);
        }
        return -1;
    }

    public static int findDuplicate2(int[] nums , int x) {
        int l = 0;
        int r = nums.length-1;
        int mid = (l+r)>>1;
        while (l<=r){
            if (nums[mid]>x){
                r = mid-1;
            }else if (nums[mid]<x){
                l = mid+1;
            }else {
                return mid;
            }
            mid = (l+r)>>1;
        }
        return -1;
    }

}
