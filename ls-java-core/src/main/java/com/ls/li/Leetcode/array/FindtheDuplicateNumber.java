/**
 * 
 */
package com.ls.li.Leetcode.array;

import java.util.Arrays;

/**
 * @author lishuai
 * @data 2016-11-29 上午8:44:54
 */

public class FindtheDuplicateNumber {

	/**
	 * @author lishuai
	 * @data 2016-11-29 上午8:44:54
	 * @param args
Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
 prove that at least one duplicate number must exist. Assume that there is only one duplicate number, 
 find the duplicate one.

Note:
You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a={4,3,2,7,1,6,5,8,8};
		
		//findDuplicate(a);
		System.out.println(findDuplicate1(a));
	}
	//1、自己第一想法 使用快排排序好数组，然后遍历一遍，判断相邻的两个数是否相等即可
    public static int findDuplicate(int[] nums) {
    	int back=0;
        Arrays.sort(nums);
        for(int i=0;i<nums.length-1;i++){
        	if(nums[i]==nums[i+1]){
        		back= nums[i];
        		break;
        	}
        }
        return back;
    	
    }
    /*
	 * The main idea is the same with problem Linked List Cycle II,
	 * https://leetcode.com/problems/linked-list-cycle-ii/. 
	 * Use two pointers the fast and the slow. 
	 * The fast one goes forward two steps each time, while the slow one goes only step each time. 
	 * They must meet the same item when slow==fast. In fact, they meet in a circle, 
	 * the duplicate number must be the entry point of the circle when visiting the array from nums[0]. 
	 * Next we just need to find the entry point. 
	 * We use a point(we can use the fast one before) to visit form begining with one step each time, 
	 * do the same job to slow. When fast==slow, they meet at the entry point of the circle. 
	 * The easy understood code is as follows.
	 * 
	 *  this is guarentted if you understand the problem #142 very well. 
	 *  Please note the constraint in the problem " array nums containing n + 1 integers 
	 *  where each integer is between 1 and n (inclusive)" makes the array an abstracted linked list: 
	 *  n[x] -> y. Now since integer cannot be 0, item 0 is guarenteed to be a "node" outside 
	 *  any cycle because n[x] must be larger than 0. 
	 *  
	 *  Then the 1st fast-slow traverse is guranteed to have the meet point at equally distance 
	 *  from the common starting point (0) to the cycle entry point
	 *  
	 *  
	 *  There could be multiple cycles in our 'graph'. 
	 *  But the beauty of this problem is that nums[0] is always the entrance to the cycle 
	 *  which has duplicate numbers, because no one can jump back to nums[0]. 
	 *  See the example below. index = [0 1 2 3 4 5 6 7]; nums=[5 2 1 3 5 7 6 4]. 
	 *  nums[1] and nums[2] forms an isolated cycle. nums[3]=3 forms an isolated cycle. 
	 *  nums[6]=6 forms an isolated cycle. nums[5] nums[7] nums[4] forms a cycle 
	 *  and nums[0]=5 is an entrance to the cycle.
	 *  
	 *  
 1) Because of duplicated number, you will for sure go into a cycle when entering from index 0.

(2) If the dumplicated number is 'x', x will appear once 
in the path from index 0 to the first element of the cycle and appear once in the cycle.
(Even though 'x' might appear more times in other places of the array.)
	 */
    /*2 思路：由于题设中的n+1个数每个数在1~n之间，使得这个数组特殊化为一个抽象的链表，即n[x]->y；
    	同时由于数组中的数都是大于零的，使得数组的第一个元素肯定在环的外面，因此从n[0]开始一定可以进入环（如果存在的话）
    	[1,2,3,4,5,3]
    	1 2
    	2 4
    	3 3
    	
    	4 1
    	5 2
    	3 3
    	*/  
    public static int findDuplicate1(int[] nums) {
    	
    	if (nums.length > 1)
    	{
    		int slow = nums[0];
    		int fast = nums[nums[0]];
    		while (slow != fast)
    		{
    			slow = nums[slow];
    			fast = nums[nums[fast]];
    		}

    		fast = 0;
    		while (fast != slow)
    		{
    			fast = nums[fast];
    			slow = nums[slow];
    		}
    		return slow;
    	}
    	return -1;
    	
    }
    //3 和2思路类似
    public static int findDuplicate2(int[] nums) {
    	int slow = 0;
        int fast = 0;
        // 找到快慢指针相遇的地方
        do{
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while(slow != fast);
        int find = 0;
        // 用一个新指针从头开始，直到和慢指针相遇
        while(find != slow){
            slow = nums[slow];
            find = nums[find];
        }
        return find;
    }
    
    //4 二分查找+鸽笼原理    鸽笼原理 （抽屉原理） “如果有五个鸽子笼，养鸽人养了6只鸽子，那么当鸽子飞回笼中后，至少有一个笼子中装有2只或2只以上鸽子。
    public static int findDuplicate3(int[] nums) {
    	 int min = 0, max = nums.length - 1;
         while(min <= max){
             // 找到中间那个数
             int mid = min + (max - min) / 2;
             int cnt = 0;
             // 计算总数组中有多少个数小于等于中间数
             for(int i = 0; i < nums.length; i++){
                 if(nums[i] <= mid){
                     cnt++;
                 }
             }
             // 如果小于等于中间数的数量大于中间数，说明前半部分必有重复
             if(cnt > mid){
                 max = mid - 1;
             // 否则后半部分必有重复
             } else {
                 min = mid + 1;
             }
         }
         return min;
    }

}
