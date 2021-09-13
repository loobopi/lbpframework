package org.lbpframework.vpn.processor;

import org.lbpframework.HexConverter;

import java.nio.ByteBuffer;

public class OutgoingResponseProcessor extends Processor implements ResponseProcessor{


    /**\
     * Result Code：表示响应Outgoing-call-request握手是否成功，
     *值为1表示成功;
     *值为2表示通用错误，暗示着有问题；
     *值为3表示无载波；
     *值为4表示服务器忙，无法及时响应；
     *值为5表示无拔号音；
     *值为6表示呼号超时；
     *值为7表示未授权。
     */
    @Override
    public ByteBuffer parserEntity(PPTPReplyWrapper replyWrapper) throws Exception {
        StringBuilder response = new StringBuilder();
        response.append(HexConverter.convertToHex(32,4));
        response.append(replyWrapper.messageType);
        response.append(replyWrapper.magicCookie);
        response.append(HexConverter.convertToHex(CMT_OUTGOING_CALL_REPLY_,4));
        response.append(replyWrapper.reserved0);
        response.append(replyWrapper.callId);
        response.append(replyWrapper.callId);
        response.append(HexConverter.convertToHex(replyWrapper.STATUS_SUCCESS,2));
        response.append(HexConverter.convertToHex(replyWrapper.ERROR_CODE,2));
        response.append(HexConverter.convertToHex(replyWrapper.CAUSE_CODE,4));
        response.append(replyWrapper.maximumBPS);
        response.append(HexConverter.convertToHex(64,4));
        response.append(replyWrapper.packetProcessingDelay);
        response.append(HexConverter.convertToHex(64,8));
        return ByteBuffer.wrap(
                HexConverter.convertToByteArray(response.toString())
        );
    }
}
