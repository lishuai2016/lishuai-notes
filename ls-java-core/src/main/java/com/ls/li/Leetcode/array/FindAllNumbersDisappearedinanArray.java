/**
 * 
 */
package com.ls.li.Leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lishuai
 * @data 2016-11-28 上午9:32:47
 */

public class FindAllNumbersDisappearedinanArray {

	/**
	 * @author lishuai
	 * @data 2016-11-28 上午9:32:47
	 * @param args
	 * Input:
Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), 
some elements appear twice and others appear once.

Find all the elements of [1, n] inclusive that do not appear in this array.

Could you do it without extra space and in O(n) runtime? 
You may assume the returned list does not count as extra space.

Example:

Input:
[4,3,2,7,8,2,3,1]

Output:
[5,6]
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a={4,3,2,7,8,2,3,1};
		findDisappearedNumbers(a);
	}
	//1 （标记）思路：由于数组中是1~n的数字，可以将数组中的数字转化为数组的下标， 标记出现过的为负数 ，遍历数组为正数的下标即为结果
    public static List<Integer> findDisappearedNumbers(int[] nums) {
    	List<Integer> ret = new ArrayList<Integer>();
        
        for(int i = 0; i < nums.length; i++) {
            int val = Math.abs(nums[i]) - 1;
            if(nums[val] > 0) {
                nums[val] = -nums[val];
            }
        }
        
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] > 0) {
                ret.add(i+1);
            }else{
            	//这一句也可不要，恢复原数组的数据
            	nums[i]=-nums[i];
            }
        }
        return ret;
    	
    }
    
    //2（标记）与1类似，只不过通过加一个数N来实现
    public static List<Integer> findDisappearedNumbers2(int[] nums) {
    	
   	 List<Integer> res = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < nums.length; i ++) 
       	 	nums[(nums[i]-1) % n] += n;
        for (int i = 0; i < nums.length; i ++)
       	 	if (nums[i] <= n) res.add(i+1);
        return res;
   	
   }
    
    
    
    
    
    //3、自己的
    public static List<Integer> findDisappearedNumbers1(int[] nums) {
    	
    	List<Integer> list =new ArrayList<Integer>();
    	for(int i=0;i<nums.length;i++){
    		if(!list.contains(nums[i])){
    			list.add(nums[i]);
    		}
    	}
    	for(int j=0;j<list.size();j++){
    		nums[list.get(j)-1]=list.get(j);
    	}
 
    	int m=0;
    	for(int k=0;k<nums.length;k++){
    		if(nums[k]!=k+1){
    			list.add(m, k+1);
    			m++;
    		}
    	}
    	return list.subList(0, m);
    	
    }

}
