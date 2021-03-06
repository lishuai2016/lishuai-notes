/**
 * 
 */
package com.ls.li.Leetcode.string;

/**
 * @author lishuai
 * @data 2016-12-15 上午9:30:23
 */

public class ValidNumber {

	/**
	 * @author lishuai
	 * @data 2016-12-15 上午9:30:23
Validate if a given string is numeric.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true




".3"   true
"3.0f"  false
"012" true
"0x12" false
"-12" true
应该是主要考虑科学计数法的表示
Note: It is intended for the problem statement to be ambiguous. 
You should gather all requirements up front before implementing one.
	 */

	public static void main(String[] args) {
		System.out.println(isNumber("46.e3"));

	}
	//2九章 和法1原理类似，但是更简洁
	public static boolean isNumber(String s) {
        int len = s.length();
        int i = 0, e = len - 1;
        while (i <= e && Character.isWhitespace(s.charAt(i))) i++;
        if (i > len - 1) return false;
        while (e >= i && Character.isWhitespace(s.charAt(e))) e--;
        // skip leading +/-
        if (s.charAt(i) == '+' || s.charAt(i) == '-') i++;
        boolean num = false; // is a digit
        boolean dot = false; // is a '.'
        boolean exp = false; // is a 'e'
        while (i <= e) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = true;
            }
            //.前不能有e或者.
            else if (c == '.') {
                if(exp || dot) return false;
                dot = true;
            }
            //e前不能再有E或者前面没有数字
            else if (c == 'e') {
                if(exp || num == false) return false;
                exp = true;
                num = false;
            }//+-号只能出现在e的前面
            else if (c == '+' || c == '-') {
                if (s.charAt(i - 1) != 'e') return false;
            }
            else {
                return false;
            }
            i++;
        }
        return num;
    }
	
	/**
We start with trimming.

If we see [0-9] we reset the number flags.
We can only see . if we didn't see e or ..
We can only see e if we didn't see e but we did see a number. We reset numberAfterE flag.
We can only see + and - in the beginning and after an e
any other character break the validation.
At the and it is only valid if there was at least 1 number and if we did see an e then a number after it as well.

So basically the number should match this regular expression:

[-+]?(([0-9]+(.[0-9]*)?)|.[0-9]+)(e[-+]?[0-9]+)?
	 */
	//2
	public static boolean isNumber1(String s) {
	    s = s.trim();	    
	    boolean pointSeen = false;
	    boolean eSeen = false;
	    boolean numberSeen = false;
	    boolean numberAfterE = true;
	    for(int i=0; i<s.length(); i++) {
	        if('0' <= s.charAt(i) && s.charAt(i) <= '9') {
	            numberSeen = true;
	            numberAfterE = true;
	        } else if(s.charAt(i) == '.') {
	            if(eSeen || pointSeen) {
	                return false;
	            }
	            pointSeen = true;
	        } else if(s.charAt(i) == 'e') {
	            if(eSeen || !numberSeen) {
	                return false;
	            }
	            numberAfterE = false;
	            eSeen = true;
	        } else if(s.charAt(i) == '-' || s.charAt(i) == '+') {
	            if(i != 0 && s.charAt(i-1) != 'e') {
	                return false;
	            }
	        } else {
	            return false;
	        }
	    }
	    
	    return numberSeen && numberAfterE;
	}
	
	
	
	//1    e . 最多出现一次，+-号最多出现两次
    public static boolean isNumber0(String s) {
    	if (s == null || s.trim().length() == 0) return false;
    	String trim = s.trim();
    	if (s.charAt(0) == '+' || s.charAt(0) == '-') trim = trim.substring(1);
    	if (trim.length() == 0) return false;
    	char[] c = trim.toCharArray();
    	int i = 0;
    	boolean flagpoint = false;
    	boolean flagsign = false;
    	while (i < c.length) {
    		if (Character.isDigit(c[i])) {
    			i++;
    			continue;
    		} else if (c[i] == 'e' || c[i] == 'E') {
    			//e的前面必须是数字
    			if (i - 1 >= 0 && Character.isDigit(c[i - 1])) {
    				//e的后面必须是+-号加数字（整数）
    				if (i + 1 < c.length) {
    					if (c[i + 1] == '+' || c[i + 1] == '-') {
    						i = i + 2;
    						if (i >= c.length || !Character.isDigit(c[i])) return false;
    						while (i < c.length) {
    							if (!Character.isDigit(c[i])) return false;
    							i++;
    						}
    						if (i == c.length) return true;
    					} else if (Character.isDigit(c[i + 1])) {
    						i = i + 2;
    						while (i < c.length) {
    							if (!Character.isDigit(c[i])) return false;
    							i++;
    						}
    						if (i == c.length) return true;
    					} else {
    						return false;
    					}
    				} else {
    					return false;
    				}
    				
    			} else if (i - 1 >= 0 && c[i - 1] == '.') {
    				if (i - 2 >= 0 && Character.isDigit(c[i - 2])) {
    					//e的后面必须是+-号加数字（整数）
        				if (i + 1 < c.length) {
        					if (c[i + 1] == '+' || c[i + 1] == '-') {
        						i = i + 2;
        						if (i >= c.length || !Character.isDigit(c[i])) return false;
        						while (i < c.length) {
        							if (!Character.isDigit(c[i])) return false;
        							i++;
        						}
        						if (i == c.length) return true;
        					} else if (Character.isDigit(c[i + 1])) {
        						i = i + 2;
        						while (i < c.length) {
        							if (!Character.isDigit(c[i])) return false;
        							i++;
        						}
        						if (i == c.length) return true;
        					} else {
        						return false;
        					}
        				} else {
        					return false;
        				}
    				} else {
    					return false;
    				}
    			} else {
    				return false;
    			}
    			//要是.位于第一个前面可以没有数字，否则其前后必须都是数字
    		} else if (c[i] == '.') {
    			if (flagpoint) return false; 
    			if (i == 0) {
    				flagpoint = true;
    				i++;
    				continue;
    			}
    			//前
    			if (i - 1 >= 0 && Character.isDigit(c[i - 1])) {

    			} else {
    				return false;
    			}
    			//后
    			if (i + 1 < c.length && Character.isDigit(c[i + 1])) {
    				i = i + 2;
    				while (i < c.length) {
    					if (c[i] == 'e' || c[i] == 'E') {
    						//e的后面必须是+-号加数字（整数）
    	    				if (i + 1 < c.length) {
    	    					if (c[i + 1] == '+' || c[i + 1] == '-') {
    	    						i = i + 2;
    	    						if (i >= c.length || !Character.isDigit(c[i])) return false;
    	    						while (i < c.length) {
    	    							if (!Character.isDigit(c[i])) return false;
    	    							i++;
    	    						}
    	    						if (i == c.length) return true;
    	    					} else if (Character.isDigit(c[i + 1])) {
    	    						i = i + 2;
    	    						while (i < c.length) {
    	    							if (!Character.isDigit(c[i])) return false;
    	    							i++;
    	    						}
    	    						if (i == c.length) return true;
    	    					} else {
    	    						return false;
    	    					}
    	    				} else {
    	    					return false;
    	    				}
        				} else if (Character.isDigit(c[i])) {
        					i++;
        	    			continue;
        				} else {
        					return false;
        				}
    				}    				   				
    			} else if (i + 1 >= c.length) return true;
    			  else return false;
    			//+-号要么出现在开头，要么在e后面
    		} else if (c[i] == '+' || c[i] == '-') {
    			if (flagsign) return false; 
    			if (i == 0) {
    				flagsign = true;
    				i++;
    				continue;
    			}
    			return false;
    		} else {
    			return false;
    		}
    	}
    	if (c.length== 1 && (c[0] == '.' || c[0] == '+' || c[0] == '-')) return false;
    	else return true;
    }
}
