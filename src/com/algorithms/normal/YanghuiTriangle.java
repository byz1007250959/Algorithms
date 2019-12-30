package com.algorithms.normal;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * https://leetcode-cn.com/problems/pascals-triangle/
 * @author DUAN
 * @date 2019/12/30
 */
public class YanghuiTriangle {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> retList = new ArrayList<>();
        List<Integer> first = new ArrayList<>();
        List<Integer> second = new ArrayList<>();
        first.add(1);
        second.add(1);
        second.add(1);
        retList.add(first);
        retList.add(second);
        if (numRows < 3) {
            return retList.subList(0,numRows);
        }
        for (int i = 3; i <= numRows; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            for (int j = 0; j < i - 2; j++) {
                List<Integer> pre = retList.get(i - 2);
                list.add(pre.get(j)+pre.get(j+1));
            }
            list.add(1);
            retList.add(list);
        }
        return retList;
    }
}
