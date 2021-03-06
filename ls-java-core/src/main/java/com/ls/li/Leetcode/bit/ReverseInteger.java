/**
 * 
 */
package com.ls.li.Leetcode.bit;

/**
 * @author lishuai
 * @data 2017-1-5 上午11:26:03
 */

public class ReverseInteger {

	/**
	 * @author lishuai
	 * @data 2017-1-5 上午11:26:03
Reverse digits of an integer.

Example1: x = 123, return 321
Example2: x = -123, return -321


Have you thought about this?
Here are some good questions to ask before coding. 
Bonus points for you if you have already thought through this!

If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.

Did you notice that the reversed integer might overflow? 
Assume the input is a 32-bit integer, then the reverse of 1000000003 overflows. 
How should you handle such cases?

For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.

Update (2014-11-10):
Test cases had been added to test the overflow behavior.

Integer.MAX_VALUE 
2147483647

1534236469
9646324351
	 */

	public static void main(String[] args) {
		//System.out.println(Integer.valueOf("0001"));
		System.out.println(reverse(1000000003));
	}
	//3九章  
	public static int reverse(int n) {
        int reversed_n = 0;
        
        while (n != 0) {
            int temp = reversed_n * 10 + n % 10;
            n = n / 10;
            if (temp / 10 != reversed_n) {
                reversed_n = 0;
                break;
            }
            reversed_n = temp;
        }
        return reversed_n;
    }
	//2 
	public static int reverse2(int x) {
        char[] c = Integer.toString(Math.abs(x)).toCharArray();
        int len = c.length;
		for (int i = 0; i < len / 2; i++) {
			char temp = c[i];
			c[i] = c[len -1 - i];
			c[len -1 - i] = temp;
		}
		StringBuffer sb= new StringBuffer();
        for(int i = 0; i < c.length; i++) {
           sb.append(c[i]);                          
        }
        if (sb.length() >= 10) {
        	Integer max = Integer.MAX_VALUE;
        	if (sb.toString().compareTo(max.toString()) > 0) {
        		return 0;
        	}
        }        
    	return x > 0 ? Integer.valueOf(sb.toString()) : -Integer.valueOf(sb.toString());
    }
	//1思路：首先去掉符号把书变成字符串，然后从头拼接（需要注意去掉前面的0，和溢出问题）38ms
    public static int reverse1(int x) {
    	long res = 0;
        String s = Integer.toString(Math.abs(x));
        int i = 0;         
        int k = 1;
        while (i < s.length()) {
        	long temp = (long)(s.charAt(i) - '0') * k;
        	res += temp;
        	if (res > Integer.MAX_VALUE) {
        		return 0;
        	}
        	k *= 10;
        	i++;        	
        }
    	return x < 0 ? -(int)res : (int)res;
    }
    //0   28ms
    public static int reverse0(int x) {
        if(x==0){
                return 0;
             } else if (x<0){
               String s=Integer. toString(x);
                char[] c=s.toCharArray();
                if(c.length ==2){
                    return x;
               } else{
                    for(int i=1;i<(c.length+1)/2;i++){
                         char temp=c[c.length -i];
                        c[c. length-i]=c[i];
                        c[i]=temp;
          
                    }
                    StringBuffer sb= new StringBuffer();
                     for(int i=0;i<c.length;i++){
                        sb.append(c[i]);                          
                    }
                     
                     if(c.length==11){
                  	   Integer temp=Integer.MIN_VALUE;
                		   if(sb.toString().compareTo(temp.toString())>0){
                			   return 0;
                		   }
                	   }
                       System.out .println(Integer.parseInt (sb.toString()));
                     return Integer.parseInt(sb.toString());
               }
               //x>0的情况
             } else {
               String s=Integer. toString(x);
                char[] c=s.toCharArray();
                if(c.length ==1){
                    return x;
               } else{
            	   
                    for(int i=0;i<c.length/2;i++){
                         char temp=c[c.length -1-i];
                        c[c. length-1-i]=c[i];
                        c[i]=temp;
                        
                    }
                   StringBuffer sb= new StringBuffer();
                     for(int i=0;i<c.length;i++){
                        sb.append(c[i]);                          
                    }
                     
                   if(c.length==10){
                	   Integer temp=Integer.MAX_VALUE;
              		   if(sb.toString().compareTo(temp.toString())>0){
              			   return 0;
              		   }
              	   }
                       System.out .println(Integer.parseInt (sb.toString()));
                    
                     return Integer.parseInt(sb.toString());
               }
               
             }
    }
}
