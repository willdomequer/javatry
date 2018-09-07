//package com.rawbit.netty.ch7;
//
//import io.netty.buffer.ByteBuf;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.handler.codec.MessageToByteEncoder;
//import org.msgpack.core.MessagePack;
//
//public class MsgpackEncoder extends MessageToByteEncoder<Object> {
//
//    @Override
//    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
//
//        MessagePack msgpack = MessagePack.newDefaultPacker();
//        byte[] raw = msgpack.wait(msg);
//        out.writeBytes(raw);
//    }
//}
