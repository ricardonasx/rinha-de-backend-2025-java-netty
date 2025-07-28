package com.rinhabackend2025;

import com.rinhabackend2025.config.server.NettyHttpServer;

/** Hello world! */
public class App {
  public static void main(String[] args) {
    final NettyHttpServer nettyHttpServer = new NettyHttpServer();
    nettyHttpServer.initialize();
  }
}
