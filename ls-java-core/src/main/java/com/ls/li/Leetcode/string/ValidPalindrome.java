/**
 * 
 */
package com.ls.li.Leetcode.string;

/**
 * @author lishuai
 * @data 2016-12-15 下午1:40:19
 */

public class ValidPalindrome {

	/**
	 * @author lishuai
	 * @data 2016-12-15 下午1:40:19
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.

Note:
Have you consider that the string might be empty? This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome.
	 */

	public static void main(String[] args) {
		System.out.println(isPalindrome(",."));
		
		System.out.println( );

	}
	
	//4九章
	public static boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }

        int front = 0;
        int end = s.length() - 1;
        while (front < end) {
            while (front < s.length() && !isvalid(s.charAt(front))){ // nead to check range of a/b
                front++;
            }

            if (front == s.length()) { // for emtpy string “.,,,”     
                return true; 
            }           

            while (end >= 0 && ! isvalid(s.charAt(end))) { // same here, need to check border of a,b
                end--;
            }

            if (Character.toLowerCase(s.charAt(front)) != Character.toLowerCase(s.charAt(end))) {
                break;
            } else {
                front++;
                end--;
            }
        }

        return end <= front; 
    }

    private static boolean isvalid (char c) {
        return Character.isLetter(c) || Character.isDigit(c);
    }
	//2 需要额外的空间
	 public static boolean isPalindrome2(String s) {
	    	if (s == null) return false;
	    	s = s.trim().toLowerCase();
	    	if (s.isEmpty() || s.length() == 1) return true;
	    	StringBuilder sb = new StringBuilder();
	    	for (int i = 0;i < s.length();i++) {
	    		if (i < s.length() && !(Character.isLetter(s.charAt(i)) || Character.isDigit(s.charAt(i)))) continue;
	    		else sb.append(s.charAt(i));	    		
	    	}
	    	if (sb.length() < 2) return true;
	    	int low = 0;
	    	int high = sb.length() - 1;
	    	while (low < high) {
	    		if (sb.charAt(low) == sb.charAt(high)) {
	    			low++;
	    			high--;
	    		} else return false;
	    	}   
	        return true;
	    }
	//1 while (low <= high) 判断等号的是否有(好像都行)
    public static boolean isPalindrome1(String s) {
    	if (s == null) return false;
    	s = s.trim().toLowerCase();
    	if (s.isEmpty() || s.length() == 1) return true;
    	int low = 0;
    	int high = s.length() - 1;
    	while (low <= high) {
    		while (low < high && !(Character.isLetter(s.charAt(low)) || Character.isDigit(s.charAt(low)))) low++;
    		while (low < high && !(Character.isLetter(s.charAt(high)) || Character.isDigit(s.charAt(high)))) high--;
    		if (low < high && s.charAt(low) != s.charAt(high)) return false;
    		else {
    			low++;
    			high--;
    		}
    	}   
        return true;
    }
    //3和1类似简化
    public static boolean isPalindrome3(String s) {
        if (s.isEmpty()) {
        	return true;
        }
        int head = 0, tail = s.length() - 1;
        char cHead, cTail;
        while(head <= tail) {
        	cHead = s.charAt(head);
        	cTail = s.charAt(tail);
        	if (!Character.isLetterOrDigit(cHead)) {
        		head++;
        	} else if(!Character.isLetterOrDigit(cTail)) {
        		tail--;
        	} else {
        		if (Character.toLowerCase(cHead) != Character.toLowerCase(cTail)) {
        			return false;
        		}
        		head++;
        		tail--;
        	}
        }
        
        return true;
    }
}
