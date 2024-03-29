/**
 * 
 */
package com.ls.li.Leetcode.array;

/**
 * @author lishuai
 * @data 2016-12-1 上午10:17:42
 */

public class FindPeakElement {

	/**
	 * @author lishuai
	 * @data 2016-12-1 上午10:17:42
A peak element is an element that is greater than its neighbors.

Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that num[-1] = num[n] = -∞.

For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
int[] a={2,1};
System.out.println(findPeakElement(a));
	}
	//1 思路 由于num[-1] = num[n] = -∞，从左边找第一个变小的数即可，否则说明数组递增，返回最后一个元素的下标即可（考虑只有一个元素的特殊情况） 时间复杂度N
    public static int findPeakElement(int[] nums) {
    	if(nums.length==1) return nums[0];
    	for(int i=1;i<nums.length;i++){
    		if(nums[i]<nums[i-1]){
    			return i-1;
    		}
    	}    	
        return nums.length-1;
    }
    /**
This problem is similar to Local Minimum. And according to the given condition, num[i] != num[i+1], 
there must exist a O(logN) solution. So we use binary search for this problem.

If num[i-1] < num[i] > num[i+1], then num[i] is peak
If num[i-1] < num[i] < num[i+1], then num[i+1...n-1] must contains a peak
If num[i-1] > num[i] > num[i+1], then num[0...i-1] must contains a peak
If num[i-1] > num[i] < num[i+1], then both sides have peak
(n is num.length)
     */
    //2 二分查找 时间复杂度 logN 迭代实现
    public static int findPeakElement1(int[] nums) {
    	int lo = 0, hi = nums.length-1;
        while(lo < hi){
            if(lo +1== hi)
                return nums[lo] > nums[hi]? lo : hi;
            int mid = lo + (hi - lo)/2;
            if(nums[mid] > nums[mid-1] && nums[mid] > nums[mid+1])
                return mid;
            else if(nums[mid] > nums[mid-1] && nums[mid] < nums[mid+1])
                lo = mid+1;
            else
                hi = mid-1;
        }
        return lo;
    }
    
    //3 二分查找 时间复杂度 logN 递归实现
    public static int findPeakElement2(int[] nums) {
    	 return helper(nums,0,nums.length-1);
    }
    
    public static int helper(int[] num,int start,int end){
        if(start == end){
            return start;
        }else if(start+1 == end){
            if(num[start] > num[end]) return start;
            return end;
        }else{
            
            int m = (start+end)/2;
            
            if(num[m] > num[m-1] && num[m] > num[m+1]){

                return m;

            }else if(num[m-1] > num[m] && num[m] > num[m+1]){

                return helper(num,start,m-1);

            }else{

                return helper(num,m+1,end);

            }
            
        }
    }
    /**
    Consider that each local maximum is one valid peak.
My solution is to find one local maximum with binary search.
Binary search satisfies the O(logn) computational complexity.
     */
    //4 思路与2一样
    public static int findPeakElement3(int[] nums) {
    	int low = 0;
        int high = nums.length-1;
        
        while(low < high)
        {
            int mid1 = (low+high)/2;
            int mid2 = mid1+1;
            if(nums[mid1] < nums[mid2])
                low = mid2;
            else
                high = mid1;
        }
        return low;
    }
}
