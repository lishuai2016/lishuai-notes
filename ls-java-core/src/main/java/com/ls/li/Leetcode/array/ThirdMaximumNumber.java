/**
 * 
 */
package com.ls.li.Leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lishuai
 * @data 2016-11-28 下午3:20:35
 * Given a non-empty array of integers, return the third maximum number in this array. 
 * If it does not exist, return the maximum number. The time complexity must be in O(n).

Example 1:
Input: [3, 2, 1]

Output: 1

Explanation: The third maximum is 1.
Example 2:
Input: [1, 2]

Output: 2

Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
Example 3:
Input: [2, 2, 3, 1]

Output: 1

Explanation: Note that the third maximum here means the third maximum distinct number.
Both numbers with value 2 are both considered as second maximum.
 */

public class ThirdMaximumNumber {

	/**
	 * @author lishuai
	 * @data 2016-11-28 下午3:20:35
	 * @param args
	 */

	public static void main(String[] args) {
		int[] a={1,-2147483648,2};
		Integer x=null;
		System.out.println(x>1);
		//thirdMax(a);
	}

	//1自己   思路：去除重复项，先排序前三个，之后遍历和前三个比较，其实就是查询前三个大数（有个问题是，要是求第n大，没法处理了？）
    public static int thirdMax(int[] nums) {
    	if(nums.length==1) return nums[0];
    	if(nums.length==2) return nums[0]>nums[1]?nums[0]:nums[1]; 
    	//去除重复
    	List<Integer> list=new ArrayList<Integer>();
    	for(int i=0;i<nums.length;i++){
    		if(!list.contains(nums[i])){
    			list.add(nums[i]);
    		}
    	}
    	if(list.size()==1) return list.get(0);
    	if(list.size()==2) return list.get(0)>list.get(1)?list.get(0):list.get(1); 
    	
    	//走到这里则有第三大数   先处理前三个数 排序
    	int first=0;
    	int second=0;
    	
    	if(list.get(0)>list.get(1)){
    		first=list.get(0);
    		second=list.get(1);
    	}else{
    		first=list.get(1);
    		second=list.get(0);
    	}
    	
    	
    	int third=list.get(2);
    	if(third>first){
    		int t=first;
    		first=third;
    		third=t;
    		
    		int t1=second;
    		second=third;
    		third=t1;
    	}else if(third>second){
    		int t1=second;
    		second=third;
    		third=t1;
    	}
    	if(list.size()>3){
    		for(int i=3;i<list.size();i++){
        		if(list.get(i)>first){       			
        			third=second;
        			second=first;
        			first=list.get(i);
        			
        		}else if(list.get(i)>second){
        			int t=second;
        			second=list.get(i);
        			third=t;
        		}else if(list.get(i)>third){
        			third=list.get(i);
        		}
        	}
    	}
    	
    	
    	System.out.println(third);
    	return third;
    }
    
    //2 思路类似，这样简化代码
    public static int thirdMax1(int[] nums) {
    	
    	 Integer max1 = null;
         Integer max2 = null;
         Integer max3 = null;
         for (Integer n : nums) {
             if (n.equals(max1) || n.equals(max2) || n.equals(max3)) continue;
             if (max1 == null || n > max1) {
                 max3 = max2;
                 max2 = max1;
                 max1 = n;
             } else if (max2 == null || n > max2) {
                 max3 = max2;
                 max2 = n;
             } else if (max3 == null || n > max3) {
                 max3 = n;
             }
         }
         return max3 == null ? max1 : max3;
    }
    
    
}
