/**
 * 
 */
package com.ls.li.Leetcode.math;

/**
 * @author lishuai
 * @data 2017-1-9 下午5:43:28
 */

public class AddStrings {

	/**
	 * @author lishuai
	 * @data 2017-1-9 下午5:43:28
Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

Note:

The length of both num1 and num2 is < 5100.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
	 */

	public static void main(String[] args) {
		System.out.println(addStrings("911","123"));

	}
	
	//2 和1的思路一样，简化代码   27ms
	 public static String addStrings(String num1, String num2) {
	        StringBuilder sb = new StringBuilder();
	        int carry = 0;
	        for(int i = num1.length() - 1, j = num2.length() - 1; i >= 0 || j >= 0 || carry == 1; i--, j--){
	            int x = i < 0 ? 0 : num1.charAt(i) - '0';
	            int y = j < 0 ? 0 : num2.charAt(j) - '0';
	            sb.append((x + y + carry) % 10);
	            carry = (x + y + carry) / 10;
	        }
	        return sb.reverse().toString();
	    }
	//1思路：两个有序序列合并成一个   33ms
    public static String addStrings1(String num1, String num2) {
    	if (num1 == null || num1.length() == 0) {
    		return num2;
    	}
    	if (num2 == null || num2.length() == 0) {
    		return num1;
    	}
    	StringBuilder sb = new StringBuilder();
    	char[] c1 = num1.toCharArray();
    	char[] c2 = num2.toCharArray();
        int len1 = c1.length - 1;
        int len2 = c2.length - 1;
        int carry = 0;
        while (len1 >= 0 && len2 >= 0) {
        	int temp = (c1[len1] - '0') + (c2[len2] - '0') + carry;
        	if (temp > 9) {
        		temp -= 10;
        		carry = 1;
        	} else {
        		carry = 0;
        	}
        	sb.append(temp);
        	len1--;
        	len2--;
        }
        while (len1 != -1) {
        	int temp = (c1[len1] - '0') + carry;
        	if (temp > 9) {
        		temp -= 10;
        		carry = 1;
        	} else {
        		carry = 0;
        	}
        	sb.append(temp);
        	len1--;
        }
        while (len2 != -1) {
        	int temp = (c2[len2] - '0') + carry;
        	if (temp > 9) {
        		temp -= 10;
        		carry = 1;
        	} else {
        		carry = 0;
        	}
        	sb.append(temp);
        	len2--;
        }
        if (carry != 0) {
        	sb.append(carry);
        }
    	return sb.reverse().toString();
    }
}
