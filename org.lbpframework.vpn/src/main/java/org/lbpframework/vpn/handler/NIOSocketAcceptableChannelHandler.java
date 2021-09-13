package org.lbpframework.vpn.handler;

import lombok.Builder;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

@Builder
public class NIOSocketAcceptableChannelHandler implements RequestHandler {

    public void handler(Selector selector, Channel channel) {
        SocketChannel socketChannel = (SocketChannel) channel;
        try {
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
