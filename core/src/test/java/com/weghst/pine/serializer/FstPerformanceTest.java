package com.weghst.pine.serializer;

import org.apache.commons.io.FileUtils;
import org.nustaq.serialization.FSTConfiguration;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class FstPerformanceTest {

    @Test
    public void serialize() {
        FSTConfiguration conf = FSTConfiguration.createFastBinaryConfiguration();
        long begin = System.currentTimeMillis();
        long size = 0;
        for (int i = 0; i < Constants.MAX_ROUND; i++) {
            Bean b = BeanFactory.newBean();
            size += conf.asByteArray(b).length;
        }
        long end = System.currentTimeMillis();
        System.out.println("----- FST Ser ------------------------B");
        System.out.println("序列化大小: " + FileUtils.byteCountToDisplaySize(size));
        System.out.println("消耗时间: " + (end - begin) / 1000D + "s");
        System.out.println("----- FST Ser ------------------------E");
    }

    @Test
    public void deserialize() {
        FSTConfiguration conf = FSTConfiguration.createFastBinaryConfiguration();
        List<byte[]> datas = new ArrayList<>();
        long size = 0;
        for (int i = 0; i < Constants.MAX_ROUND; i++) {
            Bean b = BeanFactory.newBean();
            byte[] bytes = conf.asByteArray(b);
            size += bytes.length;
            datas.add(bytes);
        }

        long begin = System.currentTimeMillis();
        for (byte[] bytes : datas) {
            Bean b = (Bean) conf.asObject(bytes);
//            System.out.println(b);
        }
        long end = System.currentTimeMillis();
        System.out.println("----- FST Des ------------------------B");
        System.out.println("反序列化大小: " + FileUtils.byteCountToDisplaySize(size));
        System.out.println("消耗时间: " + (end - begin) / 1000D + "s");
        System.out.println("----- FST Des ------------------------E");
    }
}
