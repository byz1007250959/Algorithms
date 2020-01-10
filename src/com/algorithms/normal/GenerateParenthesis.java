package com.algorithms.normal;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 *
 * 例如，给出 n = 3，生成结果为：
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 * https://leetcode-cn.com/problems/generate-parentheses/
 * @author DUAN
 * @date 2020/1/9
 */
public class GenerateParenthesis {

    public List<String> generateParenthesis(int n) {
        StringBuilder sb = new StringBuilder();
        List<String> result = new ArrayList<>();
        sb.append('(');
        search(n-1,n,sb,result);
        return result;
    }

    private void search(int leftCount, int rightCount, StringBuilder tmp, List<String> result) {
        if (rightCount < leftCount) {
            return;
        }
        if (leftCount == 0) {
            for (int i = 0; i < rightCount; i++) {
                tmp.append(')');
            }
            result.add(tmp.toString());
            return;
        }
        if (rightCount == 0) {
            result.add(tmp.toString());
            return;
        }
        StringBuilder tmpa = new StringBuilder(tmp);
        StringBuilder tmpb = new StringBuilder(tmp);
        tmpa.append('(');
        tmpb.append(')');
        search(leftCount-1,rightCount,tmpa,result);
        search(leftCount,rightCount-1,tmpb,result);
    }

    public static void main(String []args) {
        GenerateParenthesis generateParenthesis = new GenerateParenthesis();
        generateParenthesis.generateParenthesis(3).forEach(System.out::println);
    }
}
