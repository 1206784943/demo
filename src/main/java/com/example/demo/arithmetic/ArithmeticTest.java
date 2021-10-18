package com.example.demo.arithmetic;

import com.alibaba.fastjson.JSONArray;

public class ArithmeticTest {

    public static void main(String[] args) {
        test2();
    }

    //取两个字符串的最大交集
    public static void test() {
        String s1 = "135adbf67";
        String s2 = "125dbf59";
        String s3 = s2;
        int begin = 0;
        int end = s2.length();
        int i = 1;
        while (!s1.contains(s3)) {
            if (end == s2.length()) {
                begin = 0;
                end = (s2.length()) - (i++);
            } else {
                begin++;
                end++;
            }
            s3 = s2.substring(begin, end);
//            System.out.println(s3);
//            System.out.println("--------");
        }
        System.out.println("最大交集：" + s3);
    }

    public static void test2() {
        int[] one = {1, 3, 5, 7, 9, 11, 12};
        int[] two = {2, 4, 6, 8, 10};
        int onesize = one.length;
        int twosize = two.length;
        int threesize = onesize + twosize;
        int[] three = new int[threesize];
        int i = 0;
        int j = 0;
        for (int t = 0; t < threesize; t++) {
            if (i >= onesize) {   //如果第一个数组比较完了，直接把第二个数组后面的数，排序到后面
                three[t] = two[j++];
            } else if (j >= twosize) {  //如果第二个数组比较完了，直接把第一个数组后面的数，排序到后面
                three[t] = one[i++];
            } else {
                if (one[i] <= two[j]) {
                    three[t] = one[i++];
                } else {
                    three[t] = two[j++];
                }
            }
        }
        System.out.println(JSONArray.toJSONString(three));
    }
}
