package com.yap.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.util.List;

public class HttpFileServer {
    List<Integer> list ;

    private static final String DEFAULT_URL = "/src/com/phei/netty/";

    public void run(final int port, final String url) throws Exception {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast("http-decoder", new HttpRequestDecoder());
                            socketChannel.pipeline().addLast("http-aggregator", new HttpResponseEncoder());
                            socketChannel.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
                            socketChannel.pipeline().addLast("fileServerHandler", new HttpFileServerHandler(url));
                        }
                    });
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }


    public static void main(String[] args) throws Exception{
        int port = 8080;
        if (args.length > 0){
            try {
                port = Integer.parseInt(args[0]);
            }catch (NumberFormatException e){
                e.printStackTrace();;
            }
        }

        String url = DEFAULT_URL;
        if (args.length > 1){
            url = args[1];
        }
        new HttpFileServer().run(port, url);
    }


 }
