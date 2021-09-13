package org.lbpframework.vpn.processor;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public interface RequestProcessor {

    public PPTPRequestWrapper parser(SocketChannel channel, ByteBuffer buffer)throws Exception;
}
