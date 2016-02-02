package com.weghst.pine.serializer;

import java.io.Serializable;
import java.util.List;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class Bean implements Serializable {

    private int a1;
    private long a2;
    private float a3;
    private double a4;
    private String a5;
    private Child child;
    private List<Child> children;

    public int getA1() {
        return a1;
    }

    public void setA1(int a1) {
        this.a1 = a1;
    }

    public long getA2() {
        return a2;
    }

    public void setA2(long a2) {
        this.a2 = a2;
    }

    public float getA3() {
        return a3;
    }

    public void setA3(float a3) {
        this.a3 = a3;
    }

    public double getA4() {
        return a4;
    }

    public void setA4(double a4) {
        this.a4 = a4;
    }

    public String getA5() {
        return a5;
    }

    public void setA5(String a5) {
        this.a5 = a5;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public static class Child implements Serializable {
        private int a1;
        private long a2;
        private float a3;
        private double a4;
        private String a5;

        public int getA1() {
            return a1;
        }

        public void setA1(int a1) {
            this.a1 = a1;
        }

        public long getA2() {
            return a2;
        }

        public void setA2(long a2) {
            this.a2 = a2;
        }

        public float getA3() {
            return a3;
        }

        public void setA3(float a3) {
            this.a3 = a3;
        }

        public double getA4() {
            return a4;
        }

        public void setA4(double a4) {
            this.a4 = a4;
        }

        public String getA5() {
            return a5;
        }

        public void setA5(String a5) {
            this.a5 = a5;
        }


        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Child{");
            sb.append("a1=").append(a1);
            sb.append(", a2=").append(a2);
            sb.append(", a3=").append(a3);
            sb.append(", a4=").append(a4);
            sb.append(", a5='").append(a5).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Bean{");
        sb.append("a1=").append(a1);
        sb.append(", a2=").append(a2);
        sb.append(", a3=").append(a3);
        sb.append(", a4=").append(a4);
        sb.append(", a5='").append(a5).append('\'');
        sb.append(", child=").append(child);
        sb.append(", children=").append(children);
        sb.append('}');
        return sb.toString();
    }
}
