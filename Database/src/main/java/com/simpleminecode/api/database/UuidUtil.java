package com.simpleminecode.api.database;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.UUID;

public class UuidUtil {
    public static InputStream getBytes(UUID uuid) {
        final ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());

        return new ByteArrayInputStream(bb.array());
    }

    public static UUID fromBytes(InputStream stream) {
        try {
            final byte[] bytes = stream.readAllBytes();
            final ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
            final long high = byteBuffer.getLong();
            final long low = byteBuffer.getLong();

            return new UUID(high, low);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
