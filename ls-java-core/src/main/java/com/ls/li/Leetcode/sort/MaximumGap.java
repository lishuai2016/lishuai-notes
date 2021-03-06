/**
 * 
 */
package com.ls.li.Leetcode.sort;

import java.util.Arrays;

/**
 * @author lishuai
 * @data 2016-12-16 下午2:19:17
 */

public class MaximumGap {

	/**
	 * @author lishuai
	 * @data 2016-12-16 下午2:19:17
Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

Try to solve it in linear time/space.

Return 0 if the array contains less than 2 elements.

You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.

//题意：找排序好的数字之间的最大差
[3,6,9,1]      				3
[100,3,2,1]   				97
[100,3,2,1000,2,199]   		801
[100,3,2,1,2,199] 			99
[100,3,2,1,2,199,101]       98
[100,1,199,101]              99
	 */

	public static void main(String[] args) {
		

	}
	/**
	Suppose there are N elements in the array, the min value is min and the max value is max. 
	Then the maximum gap will be no smaller than ceiling[(max - min ) / (N - 1)].

Let gap = ceiling[(max - min ) / (N - 1)]. We divide all numbers in the array into n-1 buckets, 
where k-th bucket contains all numbers in [min + (k-1)gap, min + k*gap). 
Since there are n-2 numbers that are not equal min or max and there are n-1 buckets, 
at least one of the buckets are empty. 
We only need to store the largest number and the smallest number in each bucket.

After we put all the numbers into the buckets. We can scan the buckets sequentially and get the max gap.
	 */
	//3
	public int maximumGap3(int[] num) {
	    if (num == null || num.length < 2)
	        return 0;
	    // get the max and min value of the array
	    int min = num[0];
	    int max = num[0];
	    for (int i:num) {
	        min = Math.min(min, i);
	        max = Math.max(max, i);
	    }
	    // the minimum possibale gap, ceiling of the integer division
	    int gap = (int)Math.ceil((double)(max - min)/(num.length - 1));
	    int[] bucketsMIN = new int[num.length - 1]; // store the min value in that bucket
	    int[] bucketsMAX = new int[num.length - 1]; // store the max value in that bucket
	    Arrays.fill(bucketsMIN, Integer.MAX_VALUE);
	    Arrays.fill(bucketsMAX, Integer.MIN_VALUE);
	    // put numbers into buckets
	    for (int i:num) {
	        if (i == min || i == max)
	            continue;
	        int idx = (i - min) / gap; // index of the right position in the buckets
	        bucketsMIN[idx] = Math.min(i, bucketsMIN[idx]);
	        bucketsMAX[idx] = Math.max(i, bucketsMAX[idx]);
	    }
	    // scan the buckets for the max gap
	    int maxGap = Integer.MIN_VALUE;
	    int previous = min;
	    for (int i = 0; i < num.length - 1; i++) {
	        if (bucketsMIN[i] == Integer.MAX_VALUE && bucketsMAX[i] == Integer.MIN_VALUE)
	            // empty bucket
	            continue;
	        // min value minus the previous value is the current gap
	        maxGap = Math.max(maxGap, bucketsMIN[i] - previous);
	        // update previous bucket value
	        previous = bucketsMAX[i];
	    }
	    maxGap = Math.max(maxGap, max - previous); // updata the final max value gap
	    return maxGap;
	}
	/**
The first step is to find the maximum value in nums array, it will
be the threshold to end while loop.
Then use the radix sort algorithm to sort based on each digit from Least Significant Bit
(LSB) to Most Significant Bit (MSB), that's exactly what's showing
in the link.
(nums[i] / exp) % 10 is used to get the digit, for each digit, basically the digit itself serves as the index to
access the count array. Count array stores the index to access aux
array which stores the numbers after sorting based on the current
digit.
Finally, find the maximum gap from sorted array.
Time and space complexities are both O(n). (Actually time is O(10n) at worst case for Integer.MAX_VALUE 2147483647)
	 */
	//2基数排序 
	public int maximumGap2(int[] nums) {
	    if (nums == null || nums.length < 2) {
	        return 0;
	    }
	    
	    // m is the maximal number in nums
	    int m = nums[0];
	    for (int i = 1; i < nums.length; i++) {
	        m = Math.max(m, nums[i]);
	    }
	    
	    int exp = 1; // 1, 10, 100, 1000 ...
	    int R = 10; // 10 digits

	    int[] aux = new int[nums.length];
	    
	    while (m / exp > 0) { // Go through all digits from LSB to MSB
	        int[] count = new int[R];
	        
	        for (int i = 0; i < nums.length; i++) {
	            count[(nums[i] / exp) % 10]++;
	        }
	        
	        for (int i = 1; i < count.length; i++) {
	            count[i] += count[i - 1];
	        }
	        
	        for (int i = nums.length - 1; i >= 0; i--) {
	            aux[--count[(nums[i] / exp) % 10]] = nums[i];
	        }
	        
	        for (int i = 0; i < nums.length; i++) {
	            nums[i] = aux[i];
	        }
	        exp *= 10;
	    }
	    
	    int max = 0;
	    for (int i = 1; i < aux.length; i++) {
	        max = Math.max(max, aux[i] - aux[i - 1]);
	    }
	     
	    return max;
	}
	
	//1九章 
	public static int maximumGap1(int[] nums) {
        // write your code here
        if (nums.length<2) return 0;
        int minNum = -1, maxNum = -1, n = nums.length;
        for (int i=0; i<n; ++i) {
            minNum = min(nums[i], minNum);
            maxNum = max(nums[i], maxNum);
        }
        if (maxNum==minNum) return 0;
        int average = (maxNum-minNum)/(n-1);
        if (average==0) ++average;
        int[] localMin = new int[n];
        int[] localMax = new int[n];
        for (int i=0; i<n; ++i) {
            localMin[i] = -1;
            localMax[i] = -1;
        }
        for (int i=0; i<n; ++i) {
            int t = (nums[i]-minNum)/average;
            localMin[t] = min(localMin[t], nums[i]);
            localMax[t] = max(localMax[t], nums[i]);
        }
        int ans = average, left = 0, right = 1;
        while (left<n-1) {
            while (right<n && localMin[right]==-1) ++right;
            if (right>=n) break;
            ans = max(ans, localMin[right]-localMax[left]);
            left = right;
            ++right;
        }
        return ans;
    }
    private static int min(int a, int b) {
        if (a==-1) return b;
        else 
            if (b==-1) return a;
            else
                if (a<b) return a;
                else return b;
    }
    private static int max(int a, int b) {
        if (a==-1) return b;
        else
            if (b==-1) return a;
            else
                if (a>b) return a;
                else return b;    
    }
	
	//0使用快速排序N*logN（不满足题意）
    public int maximumGap0(int[] nums) {
    	Arrays.sort(nums);
        if (nums == null || nums.length < 2) return 0;
        int maxGap = 0;
    	for (int i = 1;i < nums.length;i++) {
    		if (nums[i] - nums[i - 1] > maxGap) maxGap = nums[i] - nums[i - 1];
    	}    	
    	return maxGap;
    }
}
