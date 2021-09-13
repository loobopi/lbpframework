package org.lbpframework.vpn.processor;

import java.nio.ByteBuffer;

public interface ResponseProcessor {

    public ByteBuffer parserEntity(PPTPReplyWrapper replyWrapper)throws Exception;
}
