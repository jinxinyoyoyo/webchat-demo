package com.jjkj.webchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * Created by jinxin on 2018/6/5.
 */
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 有客户端连接时，自动执行，将连接上的客户端记录下来
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel isComing = ctx.channel();

        System.out.println(isComing.remoteAddress()+"+++++++++++++++连接上服务器");

        //进入聊天室
        for(Channel channel : channelGroup) {
            if(channel != isComing) {
                channel.writeAndFlush(new TextWebSocketFrame("[欢迎："+isComing.remoteAddress()+"进入交流群]"));
            }
        }

        channelGroup.add(isComing);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel isComing = ctx.channel();

        System.out.println(isComing.remoteAddress()+"+++++++++++++++断开连接");

        //进入聊天室
        for(Channel channel : channelGroup) {
            if(channel != isComing) {
                channel.writeAndFlush(new TextWebSocketFrame("[再见："+isComing.remoteAddress()+"离开交流群]"));
            }
        }

        channelGroup.remove(isComing);
    }

    /**
     * 当客户端发来消息时自动执行
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        Channel isComing = ctx.channel();
        //进入聊天室
        for(Channel channel : channelGroup) {
            if(channel != isComing) {
                channel.writeAndFlush(new TextWebSocketFrame("用户"+isComing.remoteAddress()+"说："+msg.text()+"\n"));
            }else {
                channel.writeAndFlush(new TextWebSocketFrame("我说："+msg.text()+"\n"));
            }
        }
    }

}
