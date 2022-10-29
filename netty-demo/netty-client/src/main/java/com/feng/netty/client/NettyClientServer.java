package com.feng.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author f
 * @date 2022/10/29 14:56
 */
public class NettyClientServer {

    /** 服务端的ip */
    private String ip;

    /** 服务端的端口 */
    private int port;

    public NettyClientServer(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    private void runServer() throws InterruptedException {
        // 1.定义 BOSS 监听组
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        // 2.定义服务启动类
        Bootstrap bs = new Bootstrap();
        // 3.配置服务的启动参数
        bs.group(bossGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast("decode", new StringDecoder());
                        pipeline.addLast("encode", new StringEncoder());
                        pipeline.addLast(new NettyClientHandler());
                    }
                });
        System.out.println("-------------------- client start -------------------");
        // 4.启动客户端
        ChannelFuture cf = bs.connect(ip, port).sync();
        String reqStr = "客户端发起连接请求";
        Channel channel = cf.channel();
        // 5.客户端发送数据
        channel.writeAndFlush(reqStr);
        // 6.通过监听线程，将输入的信息发送至服务端
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                        String msg = in.readLine();
                        channel.writeAndFlush(msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) throws InterruptedException {
        new NettyClientServer("localhost", 9911).runServer();
    }
}
