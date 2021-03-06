/**
 * 
 */
package com.ls.li.Leetcode.string;

import java.util.Stack;

/**
 * @author lishuai
 * @data 2016-12-14 下午5:15:38
 */

public class LongestValidParentheses {

	/**
	 * @author lishuai
	 * @data 2016-12-14 下午5:15:38
Given a string containing just the characters '(' and ')', 
find the length of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.

	 */

	public static void main(String[] args) {
		System.out.println(longestValidParentheses("((()))"));
		//System.out.println(isValidParentheses(")()())"));

	}
	//4 感觉和2差不多
	public static int longestValidParentheses(String s) {

        if (s == null) {
            return 0;
        }

        Stack<Integer> stack = new Stack<Integer>();
        int maxLen = 0;
        int accumulatedLen = 0;

        for(int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) {
                    accumulatedLen = 0;
                } else {
                    int matchedPos = stack.pop();
                    int matchedLen = i - matchedPos + 1;

                    if (stack.isEmpty()) {
                        accumulatedLen += matchedLen;
                        matchedLen = accumulatedLen;
                    } else {
                        matchedLen = i - stack.peek();
                    }

                    maxLen = Math.max(maxLen, matchedLen);
                }
            }
        }

        return maxLen;
   }
	/**
This is my DP solution, just one pass

V[i] represents number of valid parentheses matches from S[j to i] (j<i)

V[i] = V[i-1] + 2 + previous matches V[i- (V[i-1] + 2) ] if S[i] = ')' and '(' count > 0
	 */
	//3 动态规划DP
	public static int longestValidParentheses3(String s) {
	    char[] S = s.toCharArray();
	    int[] V = new int[S.length];
	    int open = 0;
	    int max = 0;
	    for (int i=0;i < S.length; i++) {
	        if (S[i] == '(') open++;
	        if (S[i] == ')' && open > 0) {
	            // matches found
	            V[i] = 2+ V[i-1];
	            // add matches from previous
	            if(i-V[i]>0)
	                V[i] += V[i-V[i]];
	            open--;
	        }
	        if (V[i] > max) max = V[i];
	    }
	    return max;
	}
	/**
The workflow of the solution is as below.

Scan the string from beginning to end.
If current character is '(',
push its index to the stack. If current character is ')' and the
character at the index of the top of stack is '(', we just find a
matching pair so pop from the stack. Otherwise, we push the index of
')' to the stack.
After the scan is done, the stack will only
contain the indices of characters which cannot be matched. Then
let's use the opposite side - substring between adjacent indices
should be valid parentheses.
If the stack is empty, the whole input
string is valid. Otherwise, we can scan the stack to get longest
valid substring as described in step 3.
	 */
	//2 思路：根据栈中的元素下标判断
	public static int longestValidParentheses2(String s) {
        int n = s.length(), longest = 0;
        Stack<Integer> st = new Stack<Integer>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') st.push(i);
            else {
                if (!st.empty()) {
                    if (s.charAt(st.peek()) == '(') st.pop();
                    else st.push(i);
                } else st.push(i);
            }
        }
        if (st.empty()) longest = n;
        else {
            int a = n, b = 0;
            while (!st.empty()) {
                b = st.peek(); st.pop();
                longest = Math.max(longest, a-b-1);
                a = b;
            }
            longest = Math.max(longest, a);
        }
        return longest;
    }

	//1 Time Limit Exceeded
    public static int longestValidParentheses1(String s) {
       if (s == null || s.length() < 2) return 0;
       int length = 0;
       int index = s.indexOf('(');
       if (index > 0) s = s.substring(index);
       for (int i = 0;i < s.length() - 1;i++) {
    	   for (int j = i + 1;j < s.length();j++) {
    		   if (isValidParentheses(s.substring(i,j + 1)) && (j - i) > length) length = j - i + 1;
    	   }
       }       
       return length;
    }
    public static boolean isValidParentheses(String s) {
    	Stack<Character> stack = new Stack<Character>();
    	for (int i = 0;i < s.length();i++) {
    		if (s.charAt(i) == '(') stack.push(s.charAt(i));
    		else if (!stack.empty()) {
    			stack.pop();
    			continue;
    		} else return false;
    	}   	
    	return stack.empty();
    }
    
}
