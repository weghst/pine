package com.weghst.pine;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by kevin on 15/8/10.
 */
public class Test {

    @org.testng.annotations.Test
    public void testExecute() {
        DateFormat format = DateFormat.getDateInstance();
        System.out.println(format.format(new Date()));
        System.out.println("++++++++++++++");
    }
}
