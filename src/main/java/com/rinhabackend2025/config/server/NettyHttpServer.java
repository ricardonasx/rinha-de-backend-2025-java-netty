package com.rinhabackend2025.config.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.MultiThreadIoEventLoopGroup;
import io.netty.channel.nio.NioIoHandler;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyHttpServer {

  public static final int PORT = 9999;

  public void initialize() {
    final EventLoopGroup bossGroup = new MultiThreadIoEventLoopGroup(1, NioIoHandler.newFactory());
    final EventLoopGroup workerGroup =
        new MultiThreadIoEventLoopGroup(0, NioIoHandler.newFactory());

    try {
      final ServerBootstrap serverBootstrap = new ServerBootstrap();
      serverBootstrap
          .group(bossGroup, workerGroup)
          .channel(NioServerSocketChannel.class)
          .childHandler(new NettyServerConfig());

      Channel channel = serverBootstrap.bind(PORT).sync().channel();
      channel.closeFuture().sync();
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    } finally {
      bossGroup.shutdownGracefully();
      workerGroup.shutdownGracefully();
    }
  }
}
