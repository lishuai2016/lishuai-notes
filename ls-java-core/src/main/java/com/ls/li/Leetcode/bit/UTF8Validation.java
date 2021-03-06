/**
 * 
 */
package com.ls.li.Leetcode.bit;

/**
 * @author lishuai
 * @data 2017-1-6 上午9:41:15
 */

public class UTF8Validation {

	/**
	 * @author lishuai
	 * @data 2017-1-6 上午9:41:15
A character in UTF8 can be from 1 to 4 bytes long, subjected to the following rules:

For 1-byte character, the first bit is a 0, followed by its unicode code.
For n-bytes character, the first n-bits are all one's, the n+1 bit is 0, 
followed by n-1 bytes with most significant 2 bits being 10.
This is how the UTF-8 encoding would work:

   Char. number range  |        UTF-8 octet sequence
      (hexadecimal)    |              (binary)
   --------------------+---------------------------------------------
   0000 0000-0000 007F | 0xxxxxxx
   0000 0080-0000 07FF | 110xxxxx 10xxxxxx
   0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
   0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
Given an array of integers representing the data, return whether it is a valid utf-8 encoding.

Note:
The input is an array of integers. 
Only the least significant 8 bits of each integer is used to store the data. 
This means each integer represents only 1 byte of data.

Example 1:

data = [197, 130, 1], which represents the octet sequence: 11000101 10000010 00000001.

Return true.
It is a valid utf-8 encoding for a 2-bytes character followed by a 1-byte character.
Example 2:

data = [235, 140, 4], which represented the octet sequence: 11101011 10001100 00000100.

Return false.
The first 3 bits are all one's and the 4th bit is 0 means it is a 3-bytes character.
The next byte is a continuation byte which starts with 10 and that's correct.
But the second continuation byte does not start with 10, so it is invalid.

UTF-8是一种变长字节编码方式。对于某一个字符的UTF-8编码，如果只有一个字节则其最高二进制位为0；
如果是多字节，其第一个字节从最高位开始，连续的二进制位值为1的个数决定了其编码的位数，其余各字节均以10开头。UTF-8最多可用到6个字节。 
如表： 
1字节 0xxxxxxx 
2字节 110xxxxx 10xxxxxx 
3字节 1110xxxx 10xxxxxx 10xxxxxx 
4字节 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx 
5字节 111110xx 10xxxxxx 10xxxxxx 10xxxxxx 10xxxxxx 
6字节 1111110x 10xxxxxx 10xxxxxx 10xxxxxx 10xxxxxx 10xxxxxx 
因此UTF-8中可以用来表示字符编码的实际位数最多有31位，即上表中x所表示的位。除去那些控制位（每字节开头的10等），
这些x表示的位与UNICODE编码是一一对应的，位高低顺序也相同。 
实际将UNICODE转换为UTF-8编码时应先去除高位0，然后根据所剩编码的位数决定所需最小的UTF-8编码位数。 
因此那些基本ASCII字符集中的字符（UNICODE兼容ASCII）只需要一个字节的UTF-8编码（7个二进制位）便可以表示。 


这道题考察我们UTF-8编码，这种互联网所采用的通用的编码格式的产生是为了解决ASCII只能表示英文字符的局限性，和统一Unicode的实现方式。下面这段摘自维基百科UTF-8编码：

对于UTF-8编码中的任意字节B，如果B的第一位为0，则B独立的表示一个字符(ASCII码)；
如果B的第一位为1，第二位为0，则B为一个多字节字符中的一个字节(非ASCII字符)；
如果B的前两位为1，第三位为0，则B为两个字节表示的字符中的第一个字节；
如果B的前三位为1，第四位为0，则B为三个字节表示的字符中的第一个字节；
如果B的前四位为1，第五位为0，则B为四个字节表示的字符中的第一个字节；
因此，对UTF-8编码中的任意字节，根据第一位，可判断是否为ASCII字符；根据前二位，可判断该字节是否为一个字符编码的第一个字节；根据前四位（如果前两位均为1），可确定该字节为字符编码的第一个字节，并且可判断对应的字符由几个字节表示；根据前五位（如果前四位为1），可判断编码是否有错误或数据传输过程中是否有错误。

那么根据上面的描述，我们可以先来判断第一位，如果是0的话，则说明是ASCII码，我们直接跳过，判断方法是只要比二进制数10000000小的数第一位肯定是0，
然后我们来处理第一位是1的情况，由于第一位的1只是个标识符，后面连续跟的1的个数才是表示后面的字节的个数，我们可以统一从第一位开始连续1的个数，
然后减去1就是后面的字节的个数，我想的办法是如果该数字大于等于128，则表示第一位是1，然后减去128，如果得到的数大于等于64，则表示第二位是1，
依次类推就可以得到连续的个数，我们要注意10000000这个数是不合法的，遇到了直接返回false。我们得到了cnt的个数，
只要验证后面的字节是否是以10开头的数即可，验证方法也很简单，只要这个数在10000000 ~ 10111111范围之间，则一定是10开头的，参见代码如下：
	 */

	public static void main(String[] args) {
		int[] a = {197, 130, 1};
		System.out.println(validUtf8(a));
		System.out.println(0b110);

	}
	//3   7ms
	public static boolean validUtf8(int[] data) {
		if(data==null || data.length==0) return false;
		boolean isValid = true;
		for (int i = 0; i < data.length; i++) {
			if (data[i] > 255) {
				return false; // 1 after 8th digit, 100000000
			}
			int numberOfBytes = 0;
			if((data[i] & 128) == 0) { // 0xxxxxxx, 1 byte, 128(10000000)
				numberOfBytes = 1;
			} else if((data[i] & 224) == 192) { // 110xxxxx, 2 bytes, 224(11100000), 192(11000000)
				numberOfBytes = 2;
			} else if((data[i] & 240) == 224) { // 1110xxxx, 3 bytes, 240(11110000), 224(11100000)
				numberOfBytes = 3;
			} else if((data[i] & 248) == 240) { // 11110xxx, 4 bytes, 248(11111000), 240(11110000)
				numberOfBytes = 4;
			} else {
				return false;
			}
			for(int j=1;j<numberOfBytes;j++) { // check that the next n bytes start with 10xxxxxx
				if(i+j >= data.length) return false;
				if((data[i + j] & 192) != 128) return false; // 192(11000000), 128(10000000)
			}
			i = i + numberOfBytes - 1;
		}
		return isValid;
	}
	/**
n >= 240 means at least 11110000 (next 3 number will at least 128) (bitCount = 3)
224> n > 192 means at least 11000000 (next 1 number will at least 128) (bitCount = 2)
240> n >= 224 mens at least 111000000 (next 2 number will at least 128) (bitCount = 1)

if(any bitCount < 0)
	 */
	//2   6ms
	public static boolean validUtf82(int[] data) {
	    int bitCount = 0;
	    
	    for(int n : data){
	        
	        if(n >= 192){
	            if(bitCount != 0)
	                return false;
	            else if(n >= 240)
	                bitCount = 3;
	            else if(n >= 224)
	                bitCount = 2;
	            else
	                bitCount = 1;
	        }else if(n >= 128){
	            bitCount--;
	            if(bitCount < 0)
	                return false;
	        }else if(bitCount > 0){
	            return false;
	        }
	    }
	    
	    return bitCount == 0;
	}
	// Java SE 7中, 整数类型(byte, short, int以及long) 也可以使用二进制数系来表示。要指定一个二进制字面量，可以给二进制数字添加前缀 0b 或者 0B
	//1   7ms
	public static boolean validUtf81(int[] data) {
        int count = 0;
        for (int c : data) {
            if (count == 0) {
                if ((c >> 5) == 0b110) count = 1;
                else if ((c >> 4) == 0b1110) count = 2;
                else if ((c >> 3) == 0b11110) count = 3;
                else if ((c >> 7) != 0) return false;
            } else {
                if ((c >> 6) != 0b10) return false;
                count--;
            }
        }
        return count == 0;
    }
	
	//0
    public static boolean validUtf80(int[] data) {
    	int count = 0;
    	for (int i : data) {
    		if (count == 0) {
    			if ((i >> 5) == 0b110) {
    				count = 1;
    			} else if ((i >> 4) == 0b1110) {
    				count = 2;
    			} else if ((i >> 3) == 0b11110) {
    				count = 3;
    			} else if ((i >> 7) != 0) {
    				return false;
    			}
    		} else {
    			if ((i >> 6) != 0b10) {
    				return false;    				
    			}
    			count--;
    		}
    	}
    	
    	return count == 0;
    }
}
