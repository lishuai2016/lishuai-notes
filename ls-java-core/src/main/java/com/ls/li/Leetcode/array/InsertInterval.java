/**
 * 
 */
package com.ls.li.Leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lishuai
 * @data 2016-12-5 上午10:22:21
 */

public class InsertInterval {

	/**
	 * @author lishuai
	 * @data 2016-12-5 上午10:22:21
Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:
Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

Example 2:
Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
	 */

	public static void main(String[] args) {
		List<Interval> list = new ArrayList<Interval>();
		list.add(new Interval(1,3));
		list.add(new Interval(6,9));
		Interval newInterval = new Interval(2,5);
		insert4(list, newInterval);
	}
	//4 九章答案  思想和2，3差不多，都是采用更新插入值newInterval得范围
	public static List<Interval> insert4(List<Interval> intervals, Interval newInterval) {
        if (newInterval == null || intervals == null) {
            return intervals;
        }

        List<Interval> results = new ArrayList<Interval>();
        int insertPos = 0;

        for (Interval interval : intervals) {
            if (interval.end < newInterval.start) {
                results.add(interval);
                insertPos++;
            } else if (interval.start > newInterval.end) {
                results.add(interval);
            } else {
                newInterval.start = Math.min(interval.start, newInterval.start);
                newInterval.end = Math.max(interval.end, newInterval.end);
            }
        }

        results.add(insertPos, newInterval);

        return results;
    }
	
	//3 和2的思想一致，但是不需要额外的存储空间
	public List<Interval> insert3(List<Interval> intervals, Interval newInterval) {
        int i=0;
        while(i<intervals.size() && intervals.get(i).end<newInterval.start) i++;
        while(i<intervals.size() && intervals.get(i).start<=newInterval.end){
            newInterval = new Interval(Math.min(intervals.get(i).start, newInterval.start), Math.max(intervals.get(i).end, newInterval.end));
            intervals.remove(i);
        }
        intervals.add(i,newInterval);
        return intervals;
}
	//2 
    public static List<Interval> insert2(List<Interval> intervals, Interval newInterval) {    	
    	List<Interval> result = new ArrayList<Interval>();
        int i = 0;
        // add all the intervals ending before newInterval starts
        while (i < intervals.size() && intervals.get(i).end < newInterval.start)
            result.add(intervals.get(i++));
        //更新newInterval的值
        // merge all overlapping intervals to one considering newInterval
        while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
            newInterval = new Interval( // we could mutate newInterval here also
                    Math.min(newInterval.start, intervals.get(i).start),
                    Math.max(newInterval.end, intervals.get(i).end));
            i++;
        }
        result.add(newInterval); // add the union of intervals we got
        // add all the rest
        while (i < intervals.size()) result.add(intervals.get(i++)); 
        return result;
    }
	
	//1 有问题
    public static List<Interval> insert1(List<Interval> intervals, Interval newInterval) {    	
    	List<Interval> res =  new ArrayList<Interval>();
    	if (intervals.size() < 1) {
    		res.add(newInterval);
    		return res;
    	}
    	int low = -1;
    	int high = -1;
        for (int i = 0;i < intervals.size();i++) {
        	if (intervals.get(i).start <= newInterval.start && intervals.get(i).end >= newInterval.start) low = i;
        	if (intervals.get(i).start <= newInterval.end && intervals.get(i).end >= newInterval.end) high = i;       	
        }
        if (low == -1 && high ==-1) {
        	if (newInterval.end < intervals.get(0).start) {
        		intervals.add(0,newInterval);
        		return intervals;
        	} else if (newInterval.start > intervals.get(intervals.size() - 1).end){
        		intervals.add(newInterval);
        		return intervals;
        	} else {
        		res.add(newInterval);
        		return res;
        	}
        } else if (low == -1) {
        	res.add(new Interval(newInterval.start,intervals.get(high).end));
        	for (int i = high + 1;i < intervals.size();i++) {
        		res.add(intervals.get(i));
        	}  
        } else if (high == -1) {
        	for (int i = 0;i < low;i++) {
        		res.add(intervals.get(i));
        	}
        	res.add(new Interval(intervals.get(low).start,newInterval.end));

        	
        } else {
        	for (int i = 0;i < low;i++) {
        		res.add(intervals.get(i));
        	}
        	res.add(new Interval(intervals.get(low).start,intervals.get(high).end));
        	for (int i = high + 1;i < intervals.size();i++) {
        		res.add(intervals.get(i));
        	}    	
        }
    	return res;
    }
}
