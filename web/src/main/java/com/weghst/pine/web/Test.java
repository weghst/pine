package com.weghst.pine.web;

import com.weghst.pine.util.RandomUtils;
import net.sourceforge.pinyin4j.PinyinHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class Test {
    public static void main(String[] args) {
        List<String> s = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            s.add(String.valueOf(RandomUtils.nextCode6()));
        }

        List<String> result1 = new ArrayList<>();
        long begin = System.currentTimeMillis();
        for (String str : s) {
            // String str = s.get(i);
            if (str.contains("22")) {
                result1.add(str);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end - begin) + ", Size: " + result1.size());


        String city = "重庆";
//        String[] pys = PinyinHelper.toHanyuPinyinStringArray('a');
//        for (String p : pys) {
//            System.out.println(p);
//        }
    }
}
