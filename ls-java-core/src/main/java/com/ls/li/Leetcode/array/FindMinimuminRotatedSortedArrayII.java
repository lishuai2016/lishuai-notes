/**
 * 
 */
package com.ls.li.Leetcode.array;

/**
 * @author lishuai
 * @data 2016-12-1 下午1:48:35
 */

public class FindMinimuminRotatedSortedArrayII {

	/**
	 * @author lishuai
	 * @data 2016-12-1 下午1:48:35
Follow up for "Find Minimum in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

The array may contain duplicates.
	 */

	public static void main(String[] args) {
		int[] a={1,2,3};
		System.out.println(findMin1(a));

	}
	
	//1 从左边找第一个变小的数即可，否则说明数组递增，返回最后一个元素的下标即可（考虑只有一个元素的特殊情况） 时间复杂度N
    public static int findMin(int[] nums) {
    	for(int i=1;i<nums.length;i++){
            if(nums[i]<nums[i-1]){
                return nums[i];
            }
        }       
        return nums[0];
    }
    //2 二分法 根据中间节点值和最后节点值定位  When num[mid] == num[hi], we couldn't sure the position of minimum in mid's left or right, so just let upper bound reduce one.
	public static int findMin1(int[] nums) {       
         int start = 0, end = nums.length - 1;
         while (start < end) {
             int mid = start+(end-start) / 2;
             if(nums[mid]>nums[end]){
            	 start=mid+1;
             }else if(nums[mid]<nums[end]){
            	 end=mid;
             }else{
            	 end--;
             }
         }
         return nums[start];
     }
}
