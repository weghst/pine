package com.weghst.pine.serializer;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class ProtosbufRuntimePerformanceTest {

    @Test
    public void serialize() {
        long begin = System.currentTimeMillis();
        long size = 0;

        Schema<Bean> schema = RuntimeSchema.getSchema(Bean.class);
        for (int i = 0; i < Constants.MAX_ROUND; i++) {
            Bean b = BeanFactory.newBean();
            LinkedBuffer buffer = LinkedBuffer.allocate(2048);
            byte[] bytes = ProtostuffIOUtil.toByteArray(b, schema, buffer);
            size += bytes.length;
        }
        long end = System.currentTimeMillis();
        System.out.println("----- Protosbuf Runtime Ser ------------------------B");
        System.out.println("序列化大小: " + FileUtils.byteCountToDisplaySize(size));
        System.out.println("消耗时间: " + (end - begin) / 1000D + "s");
        System.out.println("----- Protosbuf Runtime Ser ------------------------E");
    }

    @Test
    public void deserialize() {
        List<byte[]> datas = new ArrayList<>();
        long size = 0;
        Schema<Bean> schema = RuntimeSchema.getSchema(Bean.class);
        for (int i = 0; i < Constants.MAX_ROUND; i++) {
            Bean b = BeanFactory.newBean();
            LinkedBuffer buffer = LinkedBuffer.allocate(2048);
            byte[] bytes = ProtostuffIOUtil.toByteArray(b, schema, buffer);
            size += bytes.length;
            datas.add(bytes);
        }

        long begin = System.currentTimeMillis();
        for (byte[] bytes : datas) {
            Bean b = new Bean();
            ProtostuffIOUtil.mergeFrom(bytes, b, schema);
        }
        long end = System.currentTimeMillis();
        System.out.println("----- Protosbuf Runtime Des ------------------------B");
        System.out.println("反序列化大小: " + FileUtils.byteCountToDisplaySize(size));
        System.out.println("消耗时间: " + (end - begin) / 1000D + "s");
        System.out.println("----- Protosbuf Runtime Des ------------------------E");
    }
}
