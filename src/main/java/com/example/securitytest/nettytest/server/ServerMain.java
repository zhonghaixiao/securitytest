package com.example.securitytest.nettytest.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.util.HashMap;

public class ServerMain {

    public static void main(String[] args) throws InterruptedException {
//        NioEventLoop
        System.setProperty("io.netty.noKeySetOptimization", "true");
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        ServerSimpleHandler handler = new ServerSimpleHandler();
        bootstrap.group(bossGroup, workGroup)
                .localAddress(new InetSocketAddress("127.0.0.1", 5000))
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(handler);
                    }
                }).childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true);
        ChannelFuture future = bootstrap.bind().sync();
        future.channel().closeFuture().sync();
//        NioEventLoop
    }

}
