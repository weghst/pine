package com.weghst.pine.vo;

import lombok.val;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kevin Zou <kevinz@weghst.com>
 */
public class Test {

    public static void main(String[] args) {
        List<String> keys = new ArrayList<>();
        keys.add("HELLO");

        Map<String, Object> values = new HashMap<>();
        values.put("a", "b");
        values.put("c", "h");

        UserVo userVo = new UserVo();
        userVo.setEmail("HELLO");
        userVo.setPassword("PAS");
        userVo.setKeys(keys);
        userVo.setValues(values);

        System.out.println(userVo);
    }
}
