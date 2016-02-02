package com.weghst.pine.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class FastjsonPerformanceTest {

    @Test
    public void serialize() {
        long begin = System.currentTimeMillis();
        long size = 0;
        for (int i = 0; i < Constants.MAX_ROUND; i++) {
            Bean b = BeanFactory.newBean();
            size += JSON.toJSONBytes(b).length;
        }
        long end = System.currentTimeMillis();
        System.out.println("----- Fastjson Ser ------------------------B");
        System.out.println("序列化大小: " + FileUtils.byteCountToDisplaySize(size));
        System.out.println("消耗时间: " + (end - begin) / 1000D + "s");
        System.out.println("----- Fastjson Ser ------------------------E");
    }

    @Test
    public void deserialize() throws IOException, ClassNotFoundException {
        List<byte[]> datas = new ArrayList<>();
        long size = 0;
        for (int i = 0; i < Constants.MAX_ROUND; i++) {
            Bean b = BeanFactory.newBean();

            byte[] bytes = JSON.toJSONBytes(b);
            size += bytes.length;
            datas.add(bytes);
        }

        long begin = System.currentTimeMillis();
        for (byte[] bytes : datas) {
            Bean b = JSON.parseObject(bytes, Bean.class);
        }
        long end = System.currentTimeMillis();
        System.out.println("----- Fastjson Des ------------------------B");
        System.out.println("反序列化大小: " + FileUtils.byteCountToDisplaySize(size));
        System.out.println("消耗时间: " + (end - begin) / 1000D + "s");
        System.out.println("----- Fastjson Des ------------------------E");
    }
}
