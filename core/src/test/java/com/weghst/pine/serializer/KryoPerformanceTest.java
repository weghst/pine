package com.weghst.pine.serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class KryoPerformanceTest {

    @Test
    public void serialize() {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setAsmEnabled(true);

        long begin = System.currentTimeMillis();
        long size = 0;
        for (int i = 0; i < Constants.MAX_ROUND; i++) {
            Bean b = BeanFactory.newBean();
            Output output = new Output(2048);
            kryo.writeObject(output, b);
//            kryo.writeClassAndObject(output, b);

            size += output.position();
        }
        long end = System.currentTimeMillis();
        System.out.println("----- Kryo Ser ------------------------B");
        System.out.println("序列化大小: " + FileUtils.byteCountToDisplaySize(size));
        System.out.println("消耗时间: " + (end - begin) / 1000D + "s");
        System.out.println("----- Kryo Ser ------------------------E");
    }

    @Test
    public void deserialize() {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setAsmEnabled(true);

        List<byte[]> datas = new ArrayList<>();
        long size = 0;
        for (int i = 0; i < Constants.MAX_ROUND; i++) {
            Bean b = BeanFactory.newBean();
            Output output = new Output(2048);
            kryo.writeObject(output, b);
//            kryo.writeClassAndObject(output, b);

            size += output.position();
            datas.add(output.toBytes());
        }

        long begin = System.currentTimeMillis();
        for (byte[] bytes : datas) {
            Input input = new Input(bytes);
            Bean b = kryo.readObject(input, Bean.class);
//            System.out.println(b);
        }
        long end = System.currentTimeMillis();
        System.out.println("----- Kryo Des ------------------------B");
        System.out.println("反序列化大小: " + FileUtils.byteCountToDisplaySize(size));
        System.out.println("消耗时间: " + (end - begin) / 1000D + "s");
        System.out.println("----- Kryo Des ------------------------E");
    }
}
