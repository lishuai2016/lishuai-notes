/**
 * 
 */
package com.ls.li.Leetcode.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author lishuai
 * @data 2016-12-5 上午9:01:04
 */

public class MergeIntervals {

	/**
	 * @author lishuai
	 * @data 2016-12-5 上午9:01:04
Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].


[[1,4],[0,4]]

[[1,4],[2,3]]
[[1,4],[0,2],[3,5]]   [[0,5]]
	 */

	public static void main(String[] args) {
		List<Interval> list = new ArrayList<Interval>();
//		list.add(new Interval(1,4));
//		list.add(new Interval(0,2));
//		list.add(new Interval(3,5));
		merge(list);

	}
	//2 效率：时间复杂度N，空间复杂度N
	//思路：首先对list集合排序（根据起始点值得大小）；维护一个起始点和终点，当当前元素和下一个元素有重叠时，更新终点值（取当前元素的终点和全局终点值比较，取较大的）；
	//否则，把当前范围写入返回list中，更新起始点和终点；最后再加入一个起始点和终点表示的范围；
	public static List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() <= 1) return intervals;
        List<Interval> res =  new ArrayList<Interval>();
		Collections.sort(intervals, new Comparator<Interval>() {
			public int compare(Interval i1,Interval i2) {
				return Integer.compare(i1.start,i2.start);	
			}						
		});
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;
        for (int i = 1;i < intervals.size();i++) {
        	if (intervals.get(i).start <= end) {
        		end = intervals.get(i).end > end ? intervals.get(i).end : end;
        	} else {
        		res.add(new Interval(start,end));
        		start = intervals.get(i).start;
        		end = intervals.get(i).end;
        	}
        }
		res.add(new Interval(start,end));
    	return res;
    }
	
	
	// 1ac解，觉得效率不是很高两层for循环
    public static List<Interval> merge1(List<Interval> intervals) {
        for (int i = 0;i < intervals.size() - 1;i++) {
        	for (int j = i +1;j < intervals.size();j++) {
        		Interval t1 = null;
        		Interval t2 = null;
        		// t1 放起点较低的 ，t2放起点较高的
        		if (intervals.get(i).start <= intervals.get(j).start) {
        			 t1 = intervals.get(i);
            		 t2 = intervals.get(j);
        		} else {
        			 t1 = intervals.get(j);
            		 t2 = intervals.get(i);
        		}       		
        		if (t1.end >= t2.start) {
        			//有重合时 end放终点最大的数
        			int end = t1.end > t2.end ? t1.end : t2.end;
        			Interval t3 = new Interval(t1.start,end);
        			intervals.remove(t1);
        			intervals.remove(t2);
        			intervals.add(t3);
        			//每执行一次合并操作，回到起点重新遍历
        			i = -1;
        			break;
        		}         		
        	}
        }    	
    	return intervals;
    }
}
