package org.lbpframework.vpn.handler;

import java.nio.channels.Channel;
import java.nio.channels.Selector;

public interface RequestHandler {

    public void handler(Selector selector, Channel channel);
}
