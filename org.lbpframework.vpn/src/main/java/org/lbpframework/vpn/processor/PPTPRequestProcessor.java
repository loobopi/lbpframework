package org.lbpframework.vpn.processor;


import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class PPTPRequestProcessor implements RequestProcessor{


    @Override
    public PPTPRequestWrapper parser(SocketChannel channel, ByteBuffer buffer) throws Exception {
        return null;
    }
}
