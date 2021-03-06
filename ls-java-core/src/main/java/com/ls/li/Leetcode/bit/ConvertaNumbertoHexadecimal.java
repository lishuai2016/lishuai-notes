/**
 * 
 */
package com.ls.li.Leetcode.bit;

/**
 * @author lishuai
 * @data 2017-1-4 下午3:13:51
 */

public class ConvertaNumbertoHexadecimal {

	/**
	 * @author lishuai
	 * @data 2017-1-4 下午3:13:51
Given an integer, write an algorithm to convert it to hexadecimal. 
For negative integer, two’s complement method is used.

Note:

All letters in hexadecimal (a-f) must be in lowercase.
The hexadecimal string must not contain extra leading 0s. 
If the number is zero, it is represented by a single zero character '0'; 
otherwise, the first character in the hexadecimal string will not be the zero character.
The given number is guaranteed to fit within the range of a 32-bit signed integer.
You must not use any method provided by the library which converts/formats the number to hex directly.
Example 1:

Input:
26

Output:
"1a"
Example 2:

Input:
-1

1 1010

15  1111

Output:
"ffffffff"
	 */

	public static void main(String[] args) {
		System.out.println(Integer.toBinaryString(26));
		System.out.println(toHex(-1));

	}
	
	
	//3   和1类似，1的优化
    public static String toHex(int num) {
    	char[] map = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        if(num == 0) return "0";
        String result = "";
        while(num != 0){
            result = map[(num & 15)] + result; 
            num = (num >>> 4);
        }
        return result;
    }
	//2 九章  直接通过与操作1111得到低四位的对应的整数，然后根据是否超过10 分别加'0' 或者'a'，然后拼接
	public static String toHex2(int num) {
        if(num == 0) {
            return "0";
        }
        String ans = "";
        int len = 0;
        while(num != 0 && len < 8) {
            int bit = num & 15;
            if(bit < 10) {
                ans = (char)('0' + bit) + ans;
            }
            else {
                ans = (char)('a' + bit - 10) + ans;
            }
            num >>= 4;
            len++;
        }
        return ans;
    }
	
	//1思路：先转化为2进制，然后四位一组转化16进制
    public static String toHex1(int num) {
    	char[] dp = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
    	StringBuilder sb = new StringBuilder();
    	if (num == 0) {
    		sb.append('0');
    		return sb.toString();
    	}
    	String s = Integer.toBinaryString(num);
    	int i = s.length();
    	while (i >= 4) {     		
    		sb.append(dp[BinaryToInteger(s.substring(i - 4, i))]);
    		i = i - 4;
    	}
    	if (i != 0) {
    		sb.append(dp[BinaryToInteger(s.substring(0, i))]);
    	}    	
        return sb.reverse().toString();
    }
    public static int BinaryToInteger(String s) {
    	int res = 0;
    	int mul = 1;
    	for (int i = s.length() - 1; i >= 0; i--) {
    		res += (s.charAt(i) - '0') * mul;
    		mul <<= 1;
    	}
    	return res;
    }
}
