/**
 * 
 */
package com.ls.li.Leetcode.bit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lishuai
 * @data 2017-1-6 上午10:26:48
 */

public class BinaryWatch {

	/**
	 * @author lishuai
	 * @data 2017-1-6 上午10:26:48
A binary watch has 4 LEDs on the top which represent the hours (0-11), 
and the 6 LEDs on the bottom represent the minutes (0-59).

Each LED represents a zero or one, with the least significant bit on the right.


For example, the above binary watch reads "3:25".

Given a non-negative integer n which represents the number of LEDs that are currently on, 
return all possible times the watch could represent.

Example:

Input: n = 1
Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
Note:
The order of output does not matter.
The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, 
it should be "10:02".


8 4 2 1
32 16 8 4 2 1
	 */

	public static void main(String[] args) {
		readBinaryWatch3(3);

	}
    public static List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        
        return res;
    }
    //3   3ms  Using Backtracking and Idea of "Permutation and Combination"分成两部分小时和分钟，然后回溯法 生成所有可能组合；
    //然后将两部分合并
    public static List<String> readBinaryWatch3(int num) {
        List<String> res = new ArrayList<>();
        int[] nums1 = new int[]{8, 4, 2, 1}, nums2 = new int[]{32, 16, 8, 4, 2, 1};
        for(int i = 0; i <= num; i++) {
            List<Integer> list1 = generateDigit(nums1, i);
            List<Integer> list2 = generateDigit(nums2, num - i);
            for(int num1: list1) {
                if(num1 >= 12) continue;
                for(int num2: list2) {
                    if(num2 >= 60) continue;
                    res.add(num1 + ":" + (num2 < 10 ? "0" + num2 : num2));
                }
            }
        }
        return res;
    }

    private static List<Integer> generateDigit(int[] nums, int count) {
        List<Integer> res = new ArrayList<>();
        generateDigitHelper(nums, count, 0, 0, res);
        return res;
    }

    private static void generateDigitHelper(int[] nums, int count, int pos, int sum, List<Integer> res) {
        if(count == 0) {
            res.add(sum);
            return;
        }
        
        for(int i = pos; i < nums.length; i++) {
            generateDigitHelper(nums, count - 1, i + 1, sum + nums[i], res);    
        }
    }
    
    
    //2九章（觉得麻烦） 4ms
    public static List<String> readBinaryWatch2(int num) {
        ArrayList<String> ans = new ArrayList<String>();
        ArrayList<ArrayList<Integer>> hour = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> min = new ArrayList<ArrayList<Integer>>();
	    for(int i = 0; i < 4; i++) {
	        hour.add(new ArrayList<Integer>());
	    }
	    for(int i = 0; i < 6; i++) {
	        min.add(new ArrayList<Integer>());
	    }
        for(int i = 0; i < 12; i++) {
            int n = Integer.bitCount(i);
            hour.get(n).add(i);
        }
        for(int i = 0; i < 60; i++) {
            min.add(new ArrayList<Integer>());
            int n = Integer.bitCount(i);
            min.get(n).add(i);
        }
        for(int i = 0; i <= num && i < 4; i++) {
            for(int h = 0; h < hour.get(i).size(); h++) {
                for(int m = 0; m < min.get(num - i).size() && num - i < 6; m++) {
                    String string = hour.get(i).get(h).toString() + ":";
                    if(min.get(num - i).get(m) < 10) {
                        string += "0";
                    }
                    string += min.get(num - i).get(m).toString();
                    ans.add(string);
                }
            }
        }
        return ans;
    }
    /**
 1
 10
 100
 1000
 10000
 100000
 1000000
 My understanding is to differentiate hours and minutes by bit, 
 as hours will occupy 4 bits, and minutes will occupy 6. 
 Replace the line with "Integer.bitCount(h + m * 16) == num" also works.
 Take n=2 to understand this.
1:01 is one valid time.
so h=1 m=1

Integer.BitCount(h+m)= Integer.BitCount(1+1)= 1 // number of 1s in 2
Integer.BitCount(h*64+m)= Integer.BitCount(65)= 2 // number of 1s in 65
If you don't understand the above two statements, follow along.

Why multiply by 64 or left shift of 6?
To understand this, first address the following questions.

What is the maximum value of m can be? The answer is 59 (check the condition in for loop).

How many bits are required to represent 59? 5 bits.
Represent m=1 in bits.

 m=                                  0  0   0  0   1  
Now add hours (h=1) to m. If you do h+m, let see what will happen (1+1=10)

         h =                        0 0 0 0 0 1
       m=                           0 0 0 0 0 1 
       h+m=                       0 0  0  0 1 0
what is number of 1's in h+m now? 1

if h=1 and m=1, there are two LEDs are glowing. But h+m says only one glowing. 
It is not capturing the idea properly. 
Our situation is to add hours without distorting minutes bit. So left shift h to 6. h<<6.
 If you shift lesser than 6, you will distort m bits. You could go beyond six shifts, but it is unnecessary.

            h    =  1 0 0 0 0 0
           m    =   0 0 0 0 0 1 
           h+m=   1 0  0  0 0 1                                   
What is the bit count now? 2

This equals to the number of LEDs growing.

In short, you could do this. Integer.bitCount(h)+Integer.bitCount(m).
     */
    //1  这种比较简单直接  下面两种方式都行，乘64的原因是避免两者有重叠
    public List<String> readBinaryWatch1(int num) {
        List<String> times = new ArrayList<>();
        for (int h = 0; h < 12; h++) {
        	 for (int m = 0; m < 60; m++) {
//             	if (Integer.bitCount(h * 64 + m) == num) {
//                 	times.add(String.format("%d:%02d", h, m));
//                 }
        		 if (Integer.bitCount(h) + Integer.bitCount(m) == num) {
        			times.add(String.format("%d:%02d", h, m));
        		 }
             }
        }        
        return times;        
    }
}
