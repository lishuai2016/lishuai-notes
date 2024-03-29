/**
 * 
 */
package com.ls.li.Leetcode.string;

/**
 * @author lishuai
 * @data 2016-12-9 下午7:02:36
 */

public class ReverseWordsinaString {

	/**
	 * @author lishuai
	 * @data 2016-12-9 下午7:02:36

Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Clarification:
What constitutes a word?
A sequence of non-space characters constitutes a word.
Could the input string contain leading or trailing spaces?
Yes. However, your reversed string should not contain leading or trailing spaces.
How about multiple spaces between two words?
Reduce them to a single space in the reversed string.
	 */

	public static void main(String[] args) {
		System.out.println(reverseWords(""));

	}
    public static String reverseWords(String s) {
    	if (s == null || s.length() == 0) return s;
    	String[] split = s.trim().split("\\s+");
    	StringBuilder sb = new StringBuilder();
    	for (int i = split.length - 1;i >= 0;i--) {
    		if (i == 0) sb.append(split[i]);
    		else sb.append(split[i]).append(" ");
    	}   	
        return sb.toString();
    }
}
