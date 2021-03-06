/**
 * 
 */
package com.ls.li.Leetcode.array;

/**
 * @author lishuai
 * @data 2016-11-30 下午3:29:31
 */

public class MinimumSizeSubarraySum {

	/**
	 * @author lishuai
	 * @data 2016-11-30 下午3:29:31
Given an array of n positive integers and a positive integer s, 
find the minimal length of a subarray of which the sum ≥ s. 
If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] a={2,3,1,2,4,3};
		System.out.println(minSubArrayLen2(7,a));
	}
	
	//1、Java O(n) solution (two pointers) 类似于滑动窗口
    public static int minSubArrayLen(int s, int[] nums) {
    	if (nums == null || nums.length == 0)
    	    return 0;
    	  
    	  int i = 0, j = 0, sum = 0, min = Integer.MAX_VALUE;
    	  
    	  while (j < nums.length) {
    	    sum += nums[j++];
    	    
    	    while (sum >= s) {
    	      min = Math.min(min, j - i);
    	      sum -= nums[i++];
    	    }
    	  }
    	  
    	  return min == Integer.MAX_VALUE ? 0 : min;
    }
/**
 We will maintain a window that grows until sum reach the given sum. 
 Once the window grows to sum at least s then 
 we can start shirking the window from left with the hope to find a smaller window.
  We shrink until sum falls below s. Then we can grow the window on right again and so on. 
  We keep this procedure of growing-shrinking until the window start reaches the end of the array. 
  Below is the implementation of the above idea which runs in O(n) time and O(1) space.
 */
    //2 和1的原理一样(觉得这个比较好理解) 滑动窗口
    public static int minSubArrayLen1(int sum, int[] nums) {
        int minlen = Integer.MAX_VALUE;
		int curSum = 0;
		int start = 0;
		int end = 0;
		
		while(start < nums.length){
			//if current window doesn't add up to the given sum then 
			//strech the window to right
			if(curSum < sum && end < nums.length){
				curSum += nums[end];
				end++;
			}
			//if current window adds up to at least given sum then
			//we can shrink the window 
			else if(curSum >= sum){
				minlen = Math.min(minlen, end-start);
				curSum -= nums[start];
				start++;
			}
			//cur sum less than required sum but we reach the end 
			else{
				break;
			}
		}
		
		return (minlen == Integer.MAX_VALUE) ? 0 : minlen;
    }
    //3 暂时没明白
    public static int minSubArrayLen2(int s, int[] nums) {
        int i = 1, j = nums.length, min = 0;
        while (i <= j) {
            int mid = (i + j) / 2;
            if (windowExist(mid, nums, s)) {
                j = mid - 1;
                min = mid;
            } else i = mid + 1;
        }
        return min;
    }


    private static boolean windowExist(int size, int[] nums, int s) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i >= size) 
            	sum -= nums[i - size];
            sum += nums[i];
            if (sum >= s) return true;
        }
        return false;
    }
    
    //4 暂时没明白
    public static int minSubArrayLen3(int s, int[] nums) {
        int sum = 0, min = Integer.MAX_VALUE;

        int[] sums = new int[nums.length];
        for (int i = 0; i < nums.length; i++)
            sums[i] = nums[i] + (i == 0 ? 0 : sums[i - 1]);

        for (int i = 0; i < nums.length; i++) {
            int j = findWindowEnd(i, sums, s);
            if (j == nums.length) break;
            min = Math.min(j - i + 1, min);
        }
        
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    private static int findWindowEnd(int start, int[] sums, int s) {
        int i = start, j = sums.length - 1, offset = start == 0 ? 0 : sums[start - 1];
        while (i <= j) {
            int m = (i + j) / 2;
            int sum = sums[m] - offset;
        if (sum >= s) j = m - 1;
        else i = m + 1;
    }
    return i;
}
    
}
