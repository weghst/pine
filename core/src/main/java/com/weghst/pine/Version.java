package com.weghst.pine;

/**
 * 版本({@code Version})类型.
 * <p>
 * {@link #compareTo(Version) }方法提供比较版本号操作, 该方法只会比较{@code major, minor, incremental }部分,
 * {@code qualifier }部分并不会参与比较. 当Version实例{@code major, minor, incremental }部分相同及使
 * {@code qualifier }部分不相同, 这两个Version也会认为是相等的.
 *
 * @author Kevin Zou (kevinz@skfiy.org)
 */
public class Version implements Comparable<Version> {

    private final int[] vers = {0, 0, 0};
    private String qualifier;

    /**
     * 通过已存在的版本号创建{@code Version }实例.
     *
     * @param version 已经存在的字符版本号
     */
    public Version(String version) {
        if (version.indexOf("-") > 1) {
            String[] vals = version.split("-");
            qualifier = vals[1];
        }

        String[] subs = version.split("\\.");
        for (int i = 0; i < subs.length; i++) {
            vers[i] = Integer.valueOf(subs[i]);
        }
    }

    /**
     * 构造{@code Version }实例.
     *
     * @param major       主版本号,一个无符号的整数
     * @param minor       次版本号,一个无符号的整数
     * @param incremental 增量版本号,一个无符号的整数
     * @param qualifier   版本限定符,可为空
     */
    public Version(int major, int minor, int incremental, String qualifier) {
        if (major < 0 || minor < 0 || incremental < 0) {
            throw new IllegalArgumentException(
                    "major, minor, incremental 版本号标识必须为一个无符号的整数");
        }

        vers[0] = major;
        vers[1] = minor;
        vers[2] = incremental;
        this.qualifier = qualifier;
    }

    /**
     * 主版本号.
     *
     * @return 一个无符号的整数
     */
    public int getMajor() {
        return vers[0];
    }

    /**
     * 次版本号.
     *
     * @return 一个无符号的整数
     */
    public int getMinor() {
        return vers[1];
    }

    /**
     * 增量版本号.
     *
     * @return 一个无符号的整数
     */
    public int getIncremental() {
        return vers[2];
    }

    /**
     * 版本限定符.
     *
     * @return 一个字符串, 可能为{@code null }
     */
    public String getQualifier() {
        return qualifier;
    }

    @Override
    public int compareTo(Version o) {
        if (o == null) {
            return -1;
        }

        int r = Integer.compare(getMajor(), o.getMajor());
        if (r != 0) {
            return r;
        }

        r = Integer.compare(getMinor(), o.getMinor());
        if (r != 0) {
            return r;
        }

        r = Integer.compare(getIncremental(), o.getIncremental());
        return r;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Version) {
            return compareTo((Version) obj) == 0;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return getMajor() + getMinor() + getIncremental();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getMajor());
        sb.append(".").append(getMinor());

        if (getIncremental() > 0) {
            sb.append(".").append(getIncremental());
        }
        if (getQualifier() != null && !getQualifier().isEmpty()) {
            sb.append("-").append(getQualifier());
        }
        return sb.toString();
    }

}
