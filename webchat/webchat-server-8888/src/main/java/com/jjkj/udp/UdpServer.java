package com.jjkj.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 * Created by jinxin on 2018/6/28.
 */
public class UdpServer {

    public void run(int port){
        System.out.println("+++++++++++++++++++++++++++++++udpstart+++++++++++++++++++++++++++++++++");
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST,true)
                    .handler(new UdpServerHandler());
            bootstrap.bind(port).sync().channel().closeFuture().await();
            System.out.println("++++++++++++++++++++++++++++udpend++++++++++++++++++++++++++++++++++++");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

}