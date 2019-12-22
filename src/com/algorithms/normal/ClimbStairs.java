package com.algorithms.normal;

import java.util.HashMap;
import java.util.Map;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * leetcode 70题
 * https://leetcode-cn.com/problems/climbing-stairs/
 * @author DUAN
 * @date 2019/12/20
 */
public class ClimbStairs {

    static Map<Integer,Integer> stepMap = new HashMap<>();
    static {
        stepMap.put(1,1);
        stepMap.put(2,2);
    }

    /**
     * 动态规划解法
     * 由题意，爬到第n层的方式有在第n-2层一次上两个台阶和在n-1层上一个台阶两种方式
     * 所以当n>=3时有 d[n] = d[n-1] + d[n-2] 第一层只有一种方式，第二层有两种方式依次递推可以求得
     * @author DUAN
     * @date 2019/12/20 10:50
     */
    public int climbStairs(int n) {
       int []array = new int[n+1];
       array[1] = 1;
       array[2] = 2;
       for (int i = 3; i <= n; i++){
           array[i] = array[i-1] + array[i-2];
       }
       return array[n];
    }

    /**
     * 递归解法(超时)
     * @author DUAN
     * @date 2019/12/20 10:59
     */
    public int climbStairs2(int n) {
        if (n == 0){
            return 1;
        }
        if (n < 0){
            return 0;
        }
        return climbStairs2(n-1) + climbStairs2(n-2);
    }

    /**
     * 递归解法(优化)
     * @author DUAN
     * @date 2019/12/20 11:02
     */
    public int climbStairs3(int n) {
        if (stepMap.get(n) != null){
            return stepMap.get(n);
        }
        if (n == 0){
            return 1;
        }
        else if(n < 0){
            return 0;
        }
        int nStep = climbStairs(n-1) + climbStairs(n-2);
        stepMap.put(n,nStep);
        return nStep;
    }
}
