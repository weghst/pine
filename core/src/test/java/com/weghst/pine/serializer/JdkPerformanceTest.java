package com.weghst.pine.serializer;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class JdkPerformanceTest {

    @Test
    public void serialize() throws IOException {
        long begin = System.currentTimeMillis();
        long size = 0;
        for (int i = 0; i < Constants.MAX_ROUND; i++) {
            Bean b = BeanFactory.newBean();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            ObjectOutputStream outputStream = new ObjectOutputStream(out);
            outputStream.writeObject(b);
            outputStream.flush();
            outputStream.close();

            size += out.size();
        }
        long end = System.currentTimeMillis();
        System.out.println("----- JDK Ser ------------------------B");
        System.out.println("序列化大小: " + FileUtils.byteCountToDisplaySize(size));
        System.out.println("消耗时间: " + (end - begin) / 1000D + "s");
        System.out.println("----- JDK Ser ------------------------E");
    }

    @Test
    public void deserialize() throws IOException, ClassNotFoundException {
        List<byte[]> datas = new ArrayList<>();
        long size = 0;
        for (int i = 0; i < Constants.MAX_ROUND; i++) {
            Bean b = BeanFactory.newBean();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            ObjectOutputStream outputStream = new ObjectOutputStream(out);
            outputStream.writeObject(b);
            outputStream.flush();
            outputStream.close();

            size += out.size();
            datas.add(out.toByteArray());
        }

        long begin = System.currentTimeMillis();
        for (byte[] bytes : datas) {
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            ObjectInputStream inputStream = new ObjectInputStream(in);
            Bean b = (Bean) inputStream.readObject();
//            System.out.println(b);
        }
        long end = System.currentTimeMillis();
        System.out.println("----- JDK Des ------------------------B");
        System.out.println("反序列化大小: " + FileUtils.byteCountToDisplaySize(size));
        System.out.println("消耗时间: " + (end - begin) / 1000D + "s");
        System.out.println("----- JDK Des ------------------------E");
    }
}
