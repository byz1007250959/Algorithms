package com.algorithms.normal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 求字符串中最长的不重复子串
 * @author DUAN
 * @date 2019/10/10
 */
public class NotRepeatSubString {
    /**
     * 该实现思路中子串的indexOf效率不高
     * 相当于两次循环
     * @author DUAN
     * @date 2019/10/10 10:49
     */
    public int longestSubString(String inputString){
        int max = 0;
        List<Character> subChar = new ArrayList<>();
        for (int i = 0; i < inputString.length(); i++){
            char c = inputString.charAt(i);
            int index = subChar.indexOf(c);
            if (index != -1){
                //出现重复字母
                subChar = subChar.subList(index + 1,subChar.size());
            }
            subChar.add(c);
            if (subChar.size() > max){
                max = subChar.size();
            }
        }
        return max;
    }
    /**
     * 采用滑动窗口的解法:O(n)时间复杂度
     * @author DUAN
     * @date 2019/10/10 11:27
     */
    public int fastSubString(String inputString){
        //记录一个字符上一次出现的下标
        Map<Character,Integer> charMap = new HashMap<>(16);
        //i指向窗口左，j指向右
        int i = 0, j = 0;
        int max = 0;
        /*
        窗口右侧循环不断向右移动，窗口左侧在发现遇到重复字符时候进行合适的移动
        (j - i + 1)窗口大小就是当前的最长不重复子串
         */
        for (; j < inputString.length(); j++){
            char c = inputString.charAt(j);
            Integer charIndex = charMap.get(c);
            if (charIndex != null){
                /*
                字符串发现重复，这里需要注意map中记录的是一个char上一次的位置
                所以可能会出现这个char上一次出现的位置还在目前左窗口的左边这种情况
                左窗口i的值也是一直增加的，所以进行一下判断。(下面的测试用例就会出现这种情况)
                 */
                if (charIndex >= i){
                    //左窗口移动到发生重复的字符的下一个位置
                    i = charIndex +1;
                }
            }
            charMap.put(c,j);
            //检查并且更新最大值
            if (max < j - i + 1){
                max = j - i +1;
            }
        }
        return max;
    }

    public static void main (String []args){
        String testinput = "aabccaacbca";
        NotRepeatSubString util = new NotRepeatSubString();
        System.out.println(util.longestSubString(testinput));
        System.out.println(util.fastSubString(testinput));
    }
}
