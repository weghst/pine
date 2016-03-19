package com.weghst.pine.util.redis;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

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
        try {
            kryo.writeClassAndObject(output, t);
            return output.toBytes();
        } finally {
            output.close();
        }
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        Input input = new Input(bytes);
        try {
            T object = (T) kryo.readClassAndObject(input);
            return object;
        } finally {
            input.close();
        }
    }
}
