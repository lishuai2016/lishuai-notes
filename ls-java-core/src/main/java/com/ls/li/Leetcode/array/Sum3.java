/**
 * 
 */
package com.ls.li.Leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lishuai
 * @data 2016-12-2 上午11:13:43
 */

public class Sum3 {

	/**
	 * @author lishuai
	 * @data 2016-12-2 上午11:13:43
Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
Find all unique triplets in the array which gives the sum of zero.

Note: The solution set must not contain duplicate triplets.

For example, given array S = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] a = {-1, 0, 1, 2, -1, -4};
		
		System.out.println(threeSum(a));
	}
	//1 回溯法  Time Limit Exceeded
	public  List<List<Integer>> threeSum1(int[] nums) {
        Arrays.sort(nums);
    	List<List<Integer>> res = new ArrayList<List<Integer>>();
    	backtrack(res, new ArrayList<Integer>(),nums, 0, 3, 0);   	
    	return res;
    }
    public  void backtrack(List<List<Integer>> res,List<Integer> list,int[] nums,int target,int k,int start) {
    	if (target == 0 && k == 0) {
    	    List<Integer> t = new ArrayList<Integer>(list);
    		if (!res.contains(t)) res.add(t);
    	}
    	for (int i = start;i < nums.length;i++) {
    		list.add(nums[i]);
    		backtrack(res, list, nums, target-nums[i], k-1, i+1);
    		list.remove(list.size()-1);
    	}
    }
	/**
The idea is to sort an input array and then run through all indices of a possible first element of a triplet. 
For each possible first element we make a standard bi-directional 2Sum sweep of the remaining part of the array.
 Also we want to skip equal elements to avoid duplicates in the answer without making a set or smth like that.
	 */
	//2      时间复杂度N*N
    public static List<List<Integer>> threeSum(int[] nums) {
    	Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<>(); 
        for (int i = 0; i < nums.length-2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i-1])) {
                int lo = i+1, hi = nums.length-1, sum = 0 - nums[i];
                while (lo < hi) {
                    if (nums[lo] + nums[hi] == sum) {
                        res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                        //两个while的作用跳过重复的元素
                        while (lo < hi && nums[lo] == nums[lo+1]) lo++;
                        while (lo < hi && nums[hi] == nums[hi-1]) hi--;
                        lo++; hi--;
                    } else if (nums[lo] + nums[hi] < sum) {                      
                        lo++;
                    } else {                       
                        hi--;
                    }
               }
            }
        }
        return res;
    }
   
   
    

}
