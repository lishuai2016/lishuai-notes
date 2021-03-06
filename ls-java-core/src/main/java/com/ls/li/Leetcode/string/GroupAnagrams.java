/**
 * 
 */
package com.ls.li.Leetcode.string;

import java.util.*;

/**
 * @author lishuai
 * @data 2016-12-13 下午3:12:14
 */

public class GroupAnagrams {

	/**
	 * @author lishuai
	 * @data 2016-12-13 下午3:12:14
Given an array of strings, group anagrams together.

For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
Return:

[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note: All inputs will be in lower-case.
	 */

	public static void main(String[] args) {
		String[] s = {"eat", "tea", "tan", "ate", "nat", "bat"};
		System.out.println(groupAnagrams(s));
		//System.out.println(compare("boo", "bob"));
	}
	//2思路：对字符进行排序作为map的key
	public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
       // Map<String, List<String>> map = null;
        Arrays.sort(strs);
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String keyStr = String.valueOf(ca);
            if (!map.containsKey(keyStr)) map.put(keyStr, new ArrayList<String>());
            map.get(keyStr).add(s);
        }
        return new ArrayList<List<String>>(map.values());
    }
	
	//1 Time Limit Exceeded 
    public static List<List<String>> groupAnagrams1(String[] strs) {
    	int[] flag = new int[strs.length];
    	List<List<String>> res = new ArrayList<List<String>>();
    	for (int i = 0;i < strs.length - 1;i++) {    		
    		if (flag[i] == 1) continue;
    		else {
    			List<String> temp = new ArrayList<String>();
    			String s1 = strs[i];
    			temp.add(s1);
    			flag[i] = 1;
    			for (int j = i + 1;j < strs.length;j++) {
    				if (compare(s1,strs[j])) {
    					temp.add(strs[j]);
    					flag[j] = 1;
    				}
    			}
    			res.add(temp);
    		}    		
    	}
    	if (flag[strs.length - 1] == 0) res.add(Arrays.asList(strs[strs.length - 1]));
        return res;
    }
    
    public static boolean compare(String s1,String s2) {
    	if (s1.length() != s2.length()) return false;
    	Map<Character,Integer> map1 = new HashMap<Character,Integer>();
    	Map<Character,Integer> map2 = new HashMap<Character,Integer>();
    	for (int i = 0;i < s2.length();i++) { 
    		if (map1.containsKey(s1.charAt(i))) map1.put(s1.charAt(i), map1.get(s1.charAt(i)) + 1);
    		else map1.put(s1.charAt(i), 1);
    		if (map2.containsKey(s2.charAt(i))) map2.put(s2.charAt(i), map2.get(s2.charAt(i)) + 1);
    		else map2.put(s2.charAt(i), 1);
    	}
    	if (map1.keySet().size() != map2.keySet().size()) return false;
    	for(Character c : map1.keySet()) if (!map2.containsKey(c) || map1.get(c) != map2.get(c)) return false;
    	return true;
    }
    
    
    public static boolean compare1(String s1,String s2) {
    	if (s1.length() != s2.length()) return false;
    	StringBuilder sb = new StringBuilder(s1); 
    	for (int i = 0;i < s2.length();i++) {
    		int index = sb.indexOf(s2.charAt(i) + "");
    		if (index == -1) return false;
    		sb.deleteCharAt(index);    		
    	}  	
    	return true;
    }
}
