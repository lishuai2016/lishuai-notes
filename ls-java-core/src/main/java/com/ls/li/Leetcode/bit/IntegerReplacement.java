/**
 * 
 */
package com.ls.li.Leetcode.bit;

/**
 * @author lishuai
 * @data 2017-1-4 下午2:24:49
 */

public class IntegerReplacement {

	/**
	 * @author lishuai
	 * @data 2017-1-4 下午2:24:49
Given a positive integer n and you can do operations as follow:

If n is even, replace n with n/2.
If n is odd, you can replace n with either n + 1 or n - 1.
What is the minimum number of replacements needed for n to become 1?

Example 1:

Input:
8  1000

Output:
3

Explanation:
8 -> 4 -> 2 -> 1
Example 2:

Input:
7    111

Output:
4

Explanation:
7 -> 8 -> 4 -> 2 -> 1
or
7 -> 6 -> 3 -> 2 -> 1

5    101
5->6-> 3 -> 2 -> 1
5->4-> 2 -> 1


I really think it should be tagged medium because there are many subtleties 
and good understanding of binary arithmetic is required.

The first step towards solution is to realize that you're allowed to remove the LSB only if it's zero. 
And to reach the target as fast as possible, removing digits is the best way to go. 
Hence, even numbers are better than odd. This is quite obvious.

What is not so obvious is what to do with odd numbers. 
One may think that you just need to remove as many 1's as possible to increase the evenness of the number. Wrong! 
Look at this example:

111011 -> 111010 -> 11101 -> 11100 -> 1110 -> 111 -> 1000 -> 100 -> 10 -> 1
And yet, this is not the best way because

111011 -> 111100 -> 11110 -> 1111 -> 10000 -> 1000 -> 100 -> 10 -> 1
See? Both 111011 -> 111010 and 111011 -> 111100 remove the same number of 1's, 
but the second way is better.

So, we just need to remove as many 1's as possible, doing +1 in case of a tie? Not quite. 
The infamous test with n=3 fails for that strategy because 11 -> 10 -> 1 is better than 11 -> 100 -> 10 -> 1. 
Fortunately, that's the only exception (or at least I can't think of any other, and there are none in the tests).

So the logic is:

If n is even, halve it.
If n=3 or n-1 has less 1's than n+1, decrement n.
Otherwise, increment n.
Here is an example of such a solution in Java:

public int integerReplacement(int n) {
    int c = 0;
    while (n != 1) {
        if ((n & 1) == 0) {
            n >>>= 1;
        } else if (n == 3 || Integer.bitCount(n + 1) > Integer.bitCount(n - 1)) {
            --n;
        } else {
            ++n;
        }
        ++c;
    }
    return c;
}
Of course, doing bitCount on every iteration is not the best way. 
It is enough to examine the last two digits to figure out whether incrementing or decrementing will give more 1's. 
Indeed, if a number ends with 01, then certainly decrementing is the way to go. Otherwise, if it ends with 11, 
then certainly incrementing is at least as good as decrementing (*011 -> *010 / *100) or even better 
(if there are three or more 1's). This leads to the following solution:

public int integerReplacement(int n) {
    int c = 0;
    while (n != 1) {
        if ((n & 1) == 0) {
            n >>>= 1;
        } else if (n == 3 || ((n >>> 1) & 1) == 0) {
            --n;
        } else {
            ++n;
        }
        ++c;
    }
    return c;
}
An alternative approach to intuitive algorithm was very well put by @dettier in a discussion: 
you should create as many trailing zeroes as you can. This way you can avoid the tie-breaking trap (there can be no ties), 
but you'll still have to handle the n=3 exception separately.
	 */

	public static void main(String[] args) {
		System.out.println(integerReplacement(7));
		System.out.println(Integer.toBinaryString(2147483647));
		String s = Integer.toBinaryString(2147483647);
		int t = 2147483647;
		int count = 0;
		while (t != 1) {
			t >>= 1;
			count++;
		}
		System.out.println(count);
	}
	/**
When n is even, the operation is fixed. The only unknown procedure is when it is odd. 
When n is odd it can be written into the form n = 2k+1 (k is a non-negative integer.). 
That is, n+1 = 2k+2 and n-1 = 2k. Then, (n+1)/2 = k+1 and (n-1)/2 = k. So one of (n+1)/2 
and (n-1)/2 is even, the other is odd. And the "best" case of this problem is to divide as much as possible. 
Because of that, always pick n+1 or n-1 based on if it can be divided by 4. 
The only special case of that is when n=3 you would like to pick n-1 rather than n+1.

Intuitive explanation:
The greedy heuristic of the above solution: the "best" case of this problem is to divide as much as possible.
Each time we divide by 2 we shift right one bit. We can only divide when the right most bit is 0 (n is even).
When the right most bit is 1, we have 2 choices: n+1 or n-1. 
We choose n+1 when we can eliminate more 1-bit so we have a longer trailing 0 (so we can divide more).
Example:
1011 -> 1100
1011 -> 1010

Proof:
Let n = 2k+1. Let count is the number of steps.

Option 1: n+1 == 2k+2; (n+1) / 2 == k+1 (count: 2)
Option 2: n-1 == 2k; (n-1) / 2 == k (count: 2)

If (n+1) % 4 == 0, then (k+1) % 2 == 0 -> k will be odd.
Let k = 2h + 1
Continue option 1: (k+1) / 2 == h+1 (count: 3)
Continue option 2:

if we choose to +1 here: k+1 == 2h+2; (k+1) / 2 == h+1 (count: 4) -> worse than option 1
if we choose to -1 here: k-1 == 2h; (k-1) / 2 == h (count: 4) -> need 4 steps to reach h, 
while option 1 need 3 steps to reach h+1. If h is even, option 1 can keep on dividing. 
If h is odd, option 1 can simply -1 to reach h (count: 4), so it will never be worse than option 2.
Therefore, when (n+1) % 4 == 4, using option 1 (n+1) is always better or 
equally good compare with using option 2 (n-1).

The proof is a little sloppy and it hasn't fully proved the correctness. However, I hope it give you the idea.
	 */
	//2
	public static int integerReplacement(int n) {
	    if(n==Integer.MAX_VALUE) return 32; //n = 2^31-1;
	    int count = 0;
	    while(n>1){
	        if(n%2==0) n/=2;
	        else{
	            if((n+1)%4==0&&(n-1!=2)) n+=1;
	            else n-=1;
	        }
	        count++;
	    }
	    return count;
	}
	
	//1思路：首先要是偶数直接除2；奇数的话判断二进制表示的最后两位是01还是11，要是01则n减1（3是个特例）；否则n加1 ；需要注意这里是有无符号移位
    public static int integerReplacement1(int n) {
    	int count = 0;
    	while (n != 1) {
    		if ((n & 1) == 0) {
    			n >>>= 1;
    		} else if (n == 3 || ((n >>> 1) & 1) == 0) {
    			n--;
    		} else {
    			n++;
    		}
    		count++;
    	}
        return count;
    }
}
