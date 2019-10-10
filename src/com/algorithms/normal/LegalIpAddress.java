package com.algorithms.normal;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个整数打印出所有合法ip
 * @author DUAN
 * @date 2019/8/30
 */
public class LegalIpAddress {
    private Long value;
    private List<String> ipList = new ArrayList<>();
    public LegalIpAddress(Long val){
        this.value = val;
    }

    public void pringAllIp(){
        if(ipList.isEmpty()){
            System.out.println("[]");
        }
        for (String ip : ipList){
            System.out.println(ip);
        }
    }

    /**
     * 找到所有合法的ip分割地址
     * 思路：穷举所有可能，ip分为四段，数字字符串长度最小为4最大为12
     * 整个过程可以描述为在一个数字字符串中点三个点将字符串截取4段，穷举所有分割可能进行判断。
     * 第一个点起始位置最小在第一个字符，最大位置在倒数第三个字符之前
     * 第二个点起始位置最小位置在第一个点之后的字符，最大位置在倒数第二个字符之前
     * 第三个点起始位置最小在第二个点之后的字符，最大位置在倒数第一个字符之前
     * 从末尾点开始逐步向后移动到边界，三重嵌套循环可以穷举所有可能。
     * @author DUAN
     * @date 2019/8/30 14:16
     */
    public void findIpAddress(){
        String valueStr = value.toString();
        int length = valueStr.length();
        if (length < 4 || length > 12){
            return;
        }
        for (int i = 1; i < length-2; i++){
            for (int j = i+1; j < length-1; j++){
                for (int k = j+1; k < length; k++){
                    String firstPart = valueStr.substring(0,i);
                    String secondPart = valueStr.substring(i,j);
                    String thirdPart = valueStr.substring(j,k);
                    String lastPart = valueStr.substring(k);
                    String ip = firstPart+"."+secondPart+"."+thirdPart+"."+lastPart;
                    if (isLegal(firstPart) && isLegal(secondPart) && isLegal(thirdPart) && isLegal(lastPart)){
                        ipList.add(ip);
                    }
                }
            }
        }
    }
    private boolean isLegal(String ipPart){
        if (ipPart.length() > 1 && ipPart.startsWith("0")){
            //长度大于1并且以0开头不合法比如127.05.000.01之类
            return false;
        }
        else if (Integer.valueOf(ipPart) > 255){
            return false;
        }
        return true;
    }
    public static void main(String[] args){
        LegalIpAddress test = new LegalIpAddress(12700L);
        test.findIpAddress();
        test.pringAllIp();
    }
}
