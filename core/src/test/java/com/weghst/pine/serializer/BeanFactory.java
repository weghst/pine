package com.weghst.pine.serializer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class BeanFactory {

    public static Bean newBean() {
        Bean bean = new Bean();
        bean.setA1(Integer.MAX_VALUE);
        bean.setA2(Long.MAX_VALUE);
        bean.setA3(Float.MAX_VALUE);
        bean.setA4(Double.MAX_VALUE);
        bean.setA5(String.valueOf(Math.random()));

        Bean.Child child = new Bean.Child();
        child.setA1(Integer.MIN_VALUE);
        child.setA2(Long.MIN_VALUE);
        child.setA3(Float.MIN_VALUE);
        child.setA4(Double.MIN_VALUE);
        child.setA5(String.valueOf(Math.random()));

        bean.setChild(child);

        List<Bean.Child> children = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            child = new Bean.Child();
            child.setA1((int) (Math.random() * Integer.MIN_VALUE));
            child.setA2((long) (Math.random() * Long.MIN_VALUE));
            child.setA3((float) (Math.random() * Float.MIN_VALUE));
            child.setA4(Math.random() * Double.MIN_VALUE);
            child.setA5(String.valueOf(Math.random()));
            children.add(child);
        }
        bean.setChildren(children);
        return bean;
    }
}
