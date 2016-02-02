package com.weghst.pine.util.redis;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.ByteArrayOutputStream;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class KryoRedisSerializer<T> implements RedisSerializer<T> {

    private Kryo kryo;

    public KryoRedisSerializer() {
        kryo = new Kryo();
        kryo.setAsmEnabled(true);
        kryo.setReferences(false);
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        Output output = new Output(2048, -1);
        kryo.writeClassAndObject(output, t);
        // output.close();
        return output.toBytes();
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        Input input = new Input(bytes);
        T object = (T) kryo.readClassAndObject(input);
        return object;
    }
}
