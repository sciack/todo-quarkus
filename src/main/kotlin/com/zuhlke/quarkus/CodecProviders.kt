package com.zuhlke.quarkus

import org.bson.BsonReader
import org.bson.BsonWriter
import org.bson.codecs.Codec
import org.bson.codecs.DecoderContext
import org.bson.codecs.EncoderContext
import org.bson.codecs.configuration.CodecProvider
import org.bson.codecs.configuration.CodecRegistry
import java.time.Instant
import java.time.ZoneOffset
import java.time.ZonedDateTime

class ZonedDateTimeCodec : Codec<ZonedDateTime> {
    override fun encode(writer: BsonWriter, value: ZonedDateTime, encoderContext: EncoderContext) {
        writer.writeDateTime(value.toInstant().toEpochMilli())
    }

    override fun decode(reader: BsonReader, decoderContext: DecoderContext?): ZonedDateTime {
        return Instant.ofEpochMilli(reader.readDateTime()).atZone(ZoneOffset.systemDefault())
    }

    override fun getEncoderClass(): Class<ZonedDateTime> = ZonedDateTime::class.java

}


class ZoneDateTimeCodecProvider: CodecProvider {

    override fun <T : Any?> get(clazz: Class<T>, registry: CodecRegistry): Codec<T>? =
            if (clazz == ZonedDateTime::class.java) {
                ZonedDateTimeCodec() as Codec<T>
            } else {
                null
            }


}