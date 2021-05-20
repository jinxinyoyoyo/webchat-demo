package com.jjkj.webchat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

/**
 * Created by jinxin on 2018/6/5.
 */
public class WebChatServerInit extends ChannelInitializer<SocketChannel> {

    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline channelPipeline = socketChannel.pipeline();

        channelPipeline.addLast(new HttpServerCodec())//将请求和应答编码或解码为http消息
                .addLast(new HttpObjectAggregator(64*1024))
                .addLast(new WebSocketServerProtocolHandler("/chat"))
                .addLast(new TextWebSocketFrameHandler());
    }

}
