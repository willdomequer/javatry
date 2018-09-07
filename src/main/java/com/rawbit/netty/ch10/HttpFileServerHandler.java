//package com.rawbit.netty.ch10;
//
//import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.SimpleChannelInboundHandler;
//import io.netty.handler.codec.http.FullHttpRequest;
//import io.netty.handler.codec.http.HttpMethod;
//
//import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
//import static io.netty.handler.codec.http.HttpResponseStatus.FORBIDDEN;
//import static io.netty.handler.codec.http.HttpResponseStatus.METHOD_NOT_ALLOWED;
//
//public class HttpFileServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
//
//    @Override
//    protected void messageReceived(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
//
//        if (! request.getDecoderResult().isSuccess()) {
//            sendError(ctX, BAD_REQUEST);
//            return;
//        }
//
//        if (request.getMethod() != HttpMethod.GET) {
//            sendError(ctx, METHOD_NOT_ALLOWED);
//            return;
//        }
//
//        final String uri = request.getUri();
//        final String path = sanitizeUri(uri);
//
//        if (path == null) {
//            sendError(ctx, FORBIDDEN);
//            return;
//        }
//    }
//
//}
