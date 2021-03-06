/**
 * 
 */
package com.ls.li.Leetcode.hashtable;

import java.util.Arrays;

/**
 * @author lishuai
 * @data 2016-12-29 上午9:55:43
 */

public class HIndex {

	/**
	 * @author lishuai
	 * @data 2016-12-29 上午9:55:43
Given an array of citations (each citation is a non-negative integer) of a researcher, 
write a function to compute the researcher's h-index.

According to the definition of h-index on Wikipedia: 
"A scientist has index h if h of his/her N papers have at least h citations each, 
and the other N − h papers have no more than h citations each."

For example, given citations = [3, 0, 6, 1, 5], 
which means the researcher has 5 papers in total 
and each of them had received 3, 0, 6, 1, 5 citations respectively. 
Since the researcher has 3 papers with at least 3 citations each 
and the remaining two with no more than 3 citations each, his h-index is 3.

Note: If there are several possible values for h, the maximum one is taken as the h-index.

Hint:

An easy approach is to sort the array first.
What are the possible values of h-index?
A faster approach is to use extra space.

h代表“高引用次数”（high citations），一名科研人员的h指数是指他至多有h篇论文分别被引用了至少h次。
例如，某人的h指数是20，这表示他已发表的论文中，每篇被引用了至少20次的论文总共有20篇。
要确定一个人的h指数非常容易，到SCI网站，查出某个人发表的所有SCI论文，让其按被引次数从高到低排列，往下核对，
直到某篇论文的序号大于该论文被引次数，那个序号减去1就是h指数
	 */

	public static void main(String[] args) {
		int[] a = {11,15};
		System.out.println(hIndex(a));
	}
	/**
	The idea is to see that the result can only range from 0 to the length of the array 
	(because we can't have h-index greater than the total papers published). 
	So we create an array "arr" which acts like a HashMap (using pigeon hole principle) 
	and loop backwards from the highest element, then we find "tot" 
	which is the total number of papers that has more than i citations, 
	and we stop when tot>=i (total number of papers with more than i citations >= i). 
	We don't need to keep going because we are trying the biggest i possible, 
	we we stop and return the result.
	 */
	    // 1 借助额外的空间 时间复杂度N 空间复杂度N+1
	    public static int hIndex(int[] citations) {
	        int length = citations.length;
	        if (length == 0) {
	        	return 0;
	        }
	        
	        int[] array2 = new int[length + 1];
	        for (int i = 0; i < length; i++) {
	        	if (citations[i] > length) {
	        		array2[length] += 1;
	        	} else {
	        		array2[citations[i]] += 1;
	        	}
	        }
	        int t = 0;
	        for (int i = length; i >= 0; i--) {
	        	t = t + array2[i];
	        	if (t >= i) {
	        		return i;
	        	}
	        }
	        return 0;
	    }
	
	
	//0 思路先排序，然后逆序遍历数组，当元素值小于或者等于结束遍历；时间复杂度为NlogN，排序耗时
    public static  int hIndex0(int[] citations) {
        int res = 0;
        Arrays.sort(citations);
        int len = citations.length;
        for ( int i = len - 1;i >= 0;i--) {
        	if (citations[i] > 0 && citations[i] > res) res++;
        	else break;
        }        
        return res;
    }
}
