package com.weghst.pine.spi;

/**
 * @author Zou Yong(zouyong@mychebao.com)
 * @date 2015-09-10 00:05
 */
public class Test22 implements NewInterface {

    private String newField = "New_Field";

    public static String get(String p) {
        System.out.println("++++++++++++++++++++++" + p);
        System.out.println("New line............");
        return "API-get++++++++++++++++++++++++++";
    }

    public static String getNew() {
        Test22 t = new Test22();
        return "Test2 getNew" + ",22 " + Test33.get() + ", " + t.newField + ", " + t.newI();
    }

    @Override
    public String newI() {
        return "new interface";
    }
}
