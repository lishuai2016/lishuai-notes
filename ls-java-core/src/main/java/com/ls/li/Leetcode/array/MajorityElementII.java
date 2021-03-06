/**
 * 
 */
package com.ls.li.Leetcode.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lishuai
 * @data 2016-11-29 下午5:04:47
 */

public class MajorityElementII {

	/**
	 * @author lishuai
	 * @data 2016-11-29 下午5:04:47
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. 
The algorithm should run in linear time and in O(1) space
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] a={1,2,3,3,2,2,3};
		System.out.println(majorityElement(a).toString());
	}
	//1 list中最多有两个元素，有可能没有   摩尔投票算法:先选出数组中出现次数较多的元素，然后再统计具体的出现次数
    public static List<Integer> majorityElement(int[] nums) {
    	if (nums == null || nums.length == 0)
    		return new ArrayList<Integer>();
    	List<Integer> result = new ArrayList<Integer>();
    	int number1 = nums[0], number2 = nums[0], count1 = 0, count2 = 0, len = nums.length;
    	for (int i = 0; i < len; i++) {
    		if (nums[i] == number1)
    			count1++;
    		else if (nums[i] == number2)
    			count2++;
    		else if (count1 == 0) {
    			number1 = nums[i];
    			count1 = 1;
    		} else if (count2 == 0) {
    			number2 = nums[i];
    			count2 = 1;
    		} else {
    			count1--;
    			count2--;
    		}
    	}
    	count1 = 0;
    	count2 = 0;
    	for (int i = 0; i < len; i++) {
    		if (nums[i] == number1)
    			count1++;
    		else if (nums[i] == number2)
    			count2++;
    	}
    	if (count1 > len / 3)
    		result.add(number1);
    	if (count2 > len / 3)
    		result.add(number2);
    	return result;
    }
    
    /**
     * 
    It is taking O(k) space actually for n/k case. 
    Since k would be a constant number in a given problem like when k = 2 or k = 3, 
    it is safe to say the solution is O(1) space.
     */
    //2 原理一样还是 摩尔投票算法
    public static List<Integer> majorityElement1(int[] nums) {
    	  return helper(nums, 3);
    }
    
    
    public static List<Integer> helper(int[] nums, int k) {
        List<Integer> ret = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        if (nums.length == 0) return ret;
        for (int n : nums) {
            if (map.containsKey(n)) map.put(n, map.get(n) + 1);
            else if (map.keySet().size() < k - 1) map.put(n, 1);
            else {
                    boolean flag = false;
                    for (Integer i : map.keySet()) {
                        if (map.get(i) == 0) {
                            map.remove(i);
                            map.put(n, 1);
                            flag = true;
                            break;
                        }
                    }
                    if (flag == false) {
                        for (Integer i : map.keySet()) {
                            map.put(i, map.get(i) - 1);
                        }
                    }
                 }
        }
        for (Integer i : map.keySet()) {
        	map.put(i, 0);
        }
        for (int n : nums) {
           if (map.containsKey(n)) map.put(n, map.get(n) + 1); 
        }
        for (Integer i : map.keySet()) {
            if (map.get(i) > nums.length / k) ret.add(i);
        }
        return ret;
    }
    //3 其实还有两种方案：1先排序，然后计算（缺点时间复杂度不满足题设）；2使用map统计（空间复杂度不满足题设），因此只能用摩尔投票算法
    public static List<Integer> majorityElement2(int[] nums) {
    	List<Integer> list=new ArrayList<Integer>();
    	
    	
    	
    	return list;
    }

}
