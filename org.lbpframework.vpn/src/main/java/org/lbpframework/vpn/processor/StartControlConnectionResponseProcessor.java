package org.lbpframework.vpn.processor;

import org.lbpframework.HexConverter;

import java.nio.ByteBuffer;

public class StartControlConnectionResponseProcessor implements ResponseProcessor{
    @Override
    public ByteBuffer parserEntity(PPTPReplyWrapper replyWrapper) throws Exception {
        StringBuilder response = new StringBuilder();
        response.append(replyWrapper.length);
        response.append(replyWrapper.messageType);
        response.append(replyWrapper.magicCookie);
        response.append(HexConverter.convertToHex(2,4));
        response.append(replyWrapper.reserved0);
        response.append(replyWrapper.protocalVersion);
        response.append(HexConverter.convertToHex(replyWrapper.STATUS_SUCCESS,2));
        response.append(HexConverter.convertToHex(replyWrapper.ERROR_CODE,2));
        response.append(replyWrapper.frameCapabilities);
        response.append(replyWrapper.bearerCapabilities);
        response.append(replyWrapper.maximunChanels);
        response.append(replyWrapper.firewareRevision);
        response.append(replyWrapper.hostName);
        response.append(replyWrapper.vendorName);
        return ByteBuffer.wrap(
                HexConverter.convertToByteArray(response.toString())
        );
    }
}
