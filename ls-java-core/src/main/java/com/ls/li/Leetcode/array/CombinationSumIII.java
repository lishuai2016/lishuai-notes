/**
 * 
 */
package com.ls.li.Leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lishuai
 * @data 2016-11-30 上午11:40:39
 */

public class CombinationSumIII {

	/**
	 * @author lishuai
	 * @data 2016-11-30 上午11:40:39
Find all possible combinations of k numbers that add up to a number n, 
given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.


Example 1:

Input: k = 3, n = 7

Output:

[[1,2,4]]

Example 2:

Input: k = 3, n = 9

Output:

[[1,2,6], [1,3,5], [2,3,4]]
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(combinationSum(3,9));
	}
    
    /**
Used backtracking to solve this.
Build an array to apply to "subset" template. 
Each time we add an element to the "list", for the next step, target= target - num[i].
Since we have already added one element, for the next step, we can only add k-1 elements. 
Since no duplicated elements accept, for the next loop, the "start" should point to the next index of current index. 
The list.remove(list.size() - 1) here means, we need to change the element here. I know it is hard to understand it, 
let me give you an example.
When k=3, n=9, my answer works like this:
[1]->[1,2]->[1,2,3]. Since now sum is not 9, no more backtracking, so after list.remove(list.size() - 1), 
it is [1,2]. Then next follows [1,2,4], sum is not 9, repeat process above untill [1,2,6]. 
When go to next backtracking, the list will be added to result, and for this list, no more backtracking.
Now we can go back to a previous backtracking, which is [1,3]->[1,3,4], fail. [1,4,]->[1,4,5], fail. And so one.
So the point of list.remove(list.size() - 1) is, after each "fail" or "success", 
since we don't need to do further attempts given such a condition, we delete the last element, 
and then end current backtracking. Next step is, add the next element to the deleted index, go on attempting.
     */
    //1 backtracking 回溯法+dfs深度优先算法
    public static List<List<Integer>> combinationSum31(int k, int n) {
    	int[] num = {1,2,3,4,5,6,7,8,9};
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        helper(result, new ArrayList<Integer>(), num, k, n,0);
        return result;
    }
    public static void helper(List<List<Integer>> result, List<Integer> list, int[] num, int k, int target, int start){
        if (k == 0 && target == 0){
            result.add(new ArrayList<Integer>(list));
        } else {
            for (int i = start; i < num.length && target > 0 && k >0; i++){
                list.add(num[i]);
                helper(result, list, num, k-1,target-num[i],i+1);
                list.remove(list.size()-1);
            }
        }
    }
    
    
  //2 和1一样 
    public static List<List<Integer>> combinationSum3(int k, int n) {

    	 List<List<Integer>> ans = new ArrayList<>();
    	 combination(ans, new ArrayList<Integer>(), k, 1, n);
    	 return ans;
    	
    }
    
    
    private static void combination(List<List<Integer>> ans, List<Integer> comb, int k,  int start, int n) {
    	if (comb.size() == k && n == 0) {
    		List<Integer> li = new ArrayList<Integer>(comb);
    		ans.add(li);
    		return;
    	}
    	for (int i = start; i <= 9; i++) {
    		comb.add(i);
    		combination(ans, comb, k, i+1, n-i);
    		comb.remove(comb.size() - 1);
    	}
    }
    
    
    //3自己参照写的 思路：回溯法+深度优先搜索   根据k和n的递减表示符合结果的元素在增加，当两者都为零的集合满足要求；
    //在不满足并且元素数已经达到最大的集合，去掉集合最后一个元素，继续搜索（类似于回溯剪枝）
    public static List<List<Integer>> combinationSum(int k, int n) {
    	 int[] nums={1,2,3,4,5,6,7,8,9};
	   	 List<List<Integer>> res=new ArrayList<List<Integer>>();
	   	 backTrack(res, new ArrayList<Integer>(),nums, k, n, 0);
	   	 
	   	 return res;
   	
   }
    
    public static void backTrack( List<List<Integer>> res,List<Integer> comb,int[] nums,int k,int target,int start){
    	if(target==0&&k==0){
    		List<Integer> list=new ArrayList<Integer>(comb);
    		res.add(list);
    		return;
    	}
    	for(int i=start;i<nums.length&&k>0&&target>0;i++){
    		comb.add(nums[i]);
    		backTrack(res, comb, nums, k-1, target-nums[i], i+1);
    		comb.remove(comb.size()-1);
    	}
    }

}
