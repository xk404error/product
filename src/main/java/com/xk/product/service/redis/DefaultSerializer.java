package com.xk.product.service.redis;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

public class DefaultSerializer implements RedisSerializer<Object> {
    private final Charset charset;

    public DefaultSerializer() {
        this(Charset.forName("UTF8"));
    }

    public DefaultSerializer(Charset charset) {
        this.charset = charset;
    }

    @Override
    public byte[] serialize(Object o) throws SerializationException {
        return o==null? null:String.valueOf(o).getBytes(charset);
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        return bytes==null?null:new String(bytes,charset);
    }
}
