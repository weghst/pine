package com.weghst.pine.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.msgpack.jackson.dataformat.MessagePackFactory;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class Jackson2MsgPackPerformanceTest {

    @Test
    public void serialize() throws IOException {
        MessagePackFactory f = new MessagePackFactory();
        ObjectMapper objectMapper = new ObjectMapper(f);

        long begin = System.currentTimeMillis();
        long size = 0;
        for (int i = 0; i < Constants.MAX_ROUND; i++) {
            Bean b = BeanFactory.newBean();

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            objectMapper.writeValue(out, b);
            size += out.size();
        }
        long end = System.currentTimeMillis();
        System.out.println("----- Jackson2 MessagePack Ser ------------------------B");
        System.out.println("序列化大小: " + FileUtils.byteCountToDisplaySize(size));
        System.out.println("消耗时间: " + (end - begin) / 1000D + "s");
        System.out.println("----- Jackson2 MessagePack Ser ------------------------E");
    }

    @Test
    public void deserialize() throws IOException, ClassNotFoundException {
        MessagePackFactory f = new MessagePackFactory();
        ObjectMapper objectMapper = new ObjectMapper(f);

        List<byte[]> datas = new ArrayList<>();
        long size = 0;
        for (int i = 0; i < Constants.MAX_ROUND; i++) {
            Bean b = BeanFactory.newBean();

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            objectMapper.writeValue(out, b);

            size += out.size();
            datas.add(out.toByteArray());
        }

        long begin = System.currentTimeMillis();
        for (byte[] bytes : datas) {
            Bean b = objectMapper.readValue(bytes, Bean.class);
//            System.out.println(b);
        }
        long end = System.currentTimeMillis();
        System.out.println("----- Jackson2 MessagePack Des ------------------------B");
        System.out.println("反序列化大小: " + FileUtils.byteCountToDisplaySize(size));
        System.out.println("消耗时间: " + (end - begin) / 1000D + "s");
        System.out.println("----- Jackson2 MessagePack Des ------------------------E");
    }
}
