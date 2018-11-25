/**
 * 
 */
package li.Leetcode.string;

/**
 * @author lishuai
 * @data 2016-12-15 上午9:26:08
 */

public class MultiplyStrings {

	/**
	 * @author lishuai
	 * @data 2016-12-15 上午9:26:08
Given two numbers represented as strings, return multiplication of the numbers as a string.

Note:
The numbers can be arbitrarily large and are non-negative.
Converting the input string to integer is NOT allowed.
You should NOT use internal library such as BigInteger.

"001"
"02"

"0002"
	 */

	public static void main(String[] args) {
		

	}
	//1九章（暂时没理解）
	 public String multiply1(String num1, String num2) {
	        if (num1 == null || num2 == null) {
	            return null;
	        }
	        
	        int len1 = num1.length(), len2 = num2.length();
	        int len3 = len1 + len2;
	        int i, j, product, carry;

	        int[] num3 = new int[len3];
	        for (i = len1 - 1; i >= 0; i--) {
	            carry = 0;
	            for (j = len2 - 1; j >= 0; j--) {
	                product = carry + num3[i + j + 1] +
	                    Character.getNumericValue(num1.charAt(i)) *
	                    Character.getNumericValue(num2.charAt(j));
	                num3[i + j + 1] = product % 10;
	                carry = product / 10;
	            }
	            num3[i + j + 1] = carry;
	        }

	        StringBuilder sb = new StringBuilder();
	        i = 0;

	        while (i < len3 - 1 && num3[i] == 0) {
	            i++;
	        }

	        while (i < len3) {
	            sb.append(num3[i++]);
	        }

	        return sb.toString();
	 }
	 /**
Remember how we do multiplication?

Start from right to left, perform multiplication on every pair of digits, 
and add them together. Let's draw the process! From the following draft, we can immediately conclude:

num1[i] * num2[j]` will be placed at indices [i + j`, `i + j + 1]` 
	  */
	 //2
	 public String multiply2(String num1, String num2) {
		    int m = num1.length(), n = num2.length();
		    int[] pos = new int[m + n];
		   
		    for(int i = m - 1; i >= 0; i--) {
		        for(int j = n - 1; j >= 0; j--) {
		            int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0'); 
		            int p1 = i + j, p2 = i + j + 1;
		            int sum = mul + pos[p2];

		            pos[p1] += sum / 10;
		            pos[p2] = (sum) % 10;
		        }
		    }  
		    
		    StringBuilder sb = new StringBuilder();
		    for(int p : pos) if(!(sb.length() == 0 && p == 0)) sb.append(p);
		    return sb.length() == 0 ? "0" : sb.toString();
	}
	 //仿2
	 public String multiply(String num1, String num2) { 
		 if (num1 == null || num2 == null) return null;			 
		 StringBuilder sb = new StringBuilder();
		 int length1 = num1.length();
		 int length2 = num2.length();
		 int[] pos = new int[length1 + length2];
		 for (int i = length1 - 1;i >= 0;i--) {
			 for (int j = length2 - 1;j >= 0;j--) {
				 int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
				 int sum = mul + pos[i + j + 1];
				 pos[i + j] += sum / 10;
				 pos[i + j + 1] = sum % 10;
			 }
		 }
		 for (int p : pos) if (!(sb.length() == 0 && p == 0)) sb.append(p);		 
		 return sb.length() == 0 ? "0" : sb.toString();
	 }
	 
	 
}
