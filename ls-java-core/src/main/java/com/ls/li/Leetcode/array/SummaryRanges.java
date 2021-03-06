/**
 * 
 */
package com.ls.li.Leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lishuai
 * @data 2016-11-30 上午9:22:59
 */

public class SummaryRanges {

	/**
	 * @author lishuai
	 * @data 2016-11-30 上午9:22:59
Given a sorted integer array without duplicates, return the summary of its ranges.

For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] a={1,3};
		System.out.println(summaryRanges(a));
	}
	
	//1 思路：遍历查找不连续子串最后一个数字，并且统计子串的长度，用子串最后一个数字减去长度即可得到子串的范围，
	//对于最后一个数字需要做特殊处理，分两类情况，与前面的子串连续以及不连续    时间复杂度为n
    public static List<String> summaryRanges(int[] nums) {
    	
    	List<String> res = new ArrayList<String>();
    	if(nums.length==1) res.add(nums[0]+"");
    	int count=0;
    	for(int i=1;i<nums.length;i++){
    		if(i==nums.length-1){
    			if(nums[i-1]+1!=nums[i]){
    				if((nums[i-1]-count)==nums[i-1]){
    					res.add(nums[i-1]+"");
    				}else{
    					res.add((nums[i-1]-count)+"->"+nums[i-1]);
    				}   				
    				res.add(nums[i]+"");
    			}else{
    				count++;
    				res.add((nums[i]-count)+"->"+nums[i]);
    			}
    		}else{
    			if(nums[i-1]+1!=nums[i]){
        			if(count==0){
        				res.add(nums[i-1]+"");
        				count=0;
        			}else{
        				res.add((nums[i-1]-count)+"->"+nums[i-1]);
        				count=0;
        			}   			
        		}else{
        			count++;
        		}
    		}
    		
    		
    	}
    	
    	
    	return res;
    }
    //2 和1的原理类似，不过这样简化代码     时间复杂度为n
    public static List<String> summaryRanges1(int[] nums) {
    	List<String> list=new ArrayList<String>();
    	if(nums.length==1){
    		list.add(nums[0]+"");
    		return list;
    	}
        for(int i=0;i<nums.length;i++){
        	int a=nums[i];
        	while(i+1<nums.length&&(nums[i+1]-nums[i])==1){
        		i++;
        	}
        	if(a!=nums[i]){
        		list.add(a+"->"+nums[i]);
        	}else{
        		list.add(a+"");
        	}
        }
        return list;
    }

}
