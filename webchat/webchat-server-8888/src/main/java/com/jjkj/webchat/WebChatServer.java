package com.jjkj.webchat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by jinxin on 2018/6/5.
 */
public class WebChatServer {

    private int port;

    public WebChatServer(int port) {
        this.port = port;
    }

    public void start() {
        //定义两个线程组
        EventLoopGroup boss = new NioEventLoopGroup();//处理新客户端的接入
        EventLoopGroup worker = new NioEventLoopGroup();//处理客户端的网络请求

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new WebChatServerInit())
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            System.out.println("+++++++++++++++++++++++++++++服务器已经启动");
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

}
