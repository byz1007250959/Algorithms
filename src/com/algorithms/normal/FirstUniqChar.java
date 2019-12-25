package com.algorithms.normal;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 * https://leetcode-cn.com/problems/first-unique-character-in-a-string/
 * @author DUAN
 * @date 2019/12/25
 */
public class FirstUniqChar {

    /**
     * 使用有序map来解决
     * @author DUAN
     * @date 2019/12/25 17:36
     */
    private int firstUniqChar(String s) {
        if (s==null || s.isEmpty()){
            return -1;
        }
        Set<Character> excludeSet = new HashSet<>();
        Map<Character,Integer> indexMap = new LinkedHashMap<>();
        for (int i=0;i<s.length();i++){
            if(excludeSet.contains(s.charAt(i))){
                continue;
            }
            if (indexMap.containsKey(s.charAt(i))){
                indexMap.remove(s.charAt(i));
                excludeSet.add(s.charAt(i));
            }
            else {
                indexMap.put(s.charAt(i),i);
            }
        }
        return indexMap.isEmpty() ? -1 : indexMap.get(indexMap.keySet().iterator().next());
    }

    /**
     * 简单优化：与上面思路相同，使用数组替代
     * @author DUAN
     * @date 2019/12/25 17:36
     */
    private int firstUniqChar2(String s) {
        if (s==null || s.isEmpty()){
            return -1;
        }
        int length = s.length();
        int []exclude = new int[26];
        int []index = new int[26];
        for (int i = 0; i < index.length; i++) {
            index[i] = -1;
        }
        for (int i = 0;i < length;i++){
            char c = s.charAt(i);
            if(exclude[c-97] == 1){
                continue;
            }
            if (index[c-97] != -1){
                index[c-97] = -1;;
                exclude[c-97] = 1;
            }
            else {
                index[c-97] = i;
            }
        }
        int min = length;
        boolean exists = false;
        for (int i = 0;i < index.length; i++) {
            if (index[i] != -1 && index[i] < min){
                min = index[i];
                exists = true;
            }
        }
        return exists ? min : -1;
    }

    public static void main(String []args) {
        FirstUniqChar firstUniqChar = new FirstUniqChar();
        System.out.println(firstUniqChar.firstUniqChar2("loveleetcode"));
    }
}
