package com.example.securitytest.nettytest.client;

import com.example.securitytest.nettytest.server.ServerSimpleHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

public class ClientMain {

    public static void main(String[] args) throws InterruptedException {
//        Channel
//        ChannelFuture
//        NioServerSocketChannel

        EventLoopGroup workGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        ChannelFuture future = bootstrap.group(workGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ClientSimpleHandler());
                    }
                }).connect("127.0.0.1", 5000).sync();
        future.channel().writeAndFlush(Unpooled.copiedBuffer("hello server", CharsetUtil.UTF_8)).sync();
        future.channel().closeFuture().sync();

    }

}
