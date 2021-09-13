package org.lbpframework.vpn.processor;

import org.lbpframework.HexConverter;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class StartControlConnectionRequestProcessor implements RequestProcessor {

    @Override
    public PPTPRequestWrapper parser(SocketChannel channel, ByteBuffer buffer) throws Exception{

        byte[] array = buffer.array();
        String hex = HexConverter.convertToHex(array);

        //解析 StartControlConnectionRequest 数据
        int length = HexConverter.convertToInt(hex.substring(0, 4));
        int messageType =  HexConverter.convertToInt(hex.substring(4, 8));
        String magicCookie = hex.substring(8, 16);
        int controlMessageType = HexConverter.convertToInt(hex.substring(16, 20));

        String reserved0 = hex.substring(20, 24);
        int protocalVersion = HexConverter.convertToInt(hex.substring(24, 28));
        String reserved1 = hex.substring(28, 32);
        int framingCapabilities = HexConverter.convertToInt(hex.substring(32, 40));
        int bearerCapabilities = HexConverter.convertToInt(hex.substring(40, 48));
        int maximunChannles = HexConverter.convertToInt(hex.substring(48, 52));
        int firewareRevision = HexConverter.convertToInt(hex.substring(52, 56));
        String hostName = hex.substring(56, 184);
        String vendorName = hex.substring(184, 312);


        PPTPRequest pptpRequest = PPTPRequest.builder()
                .length(length)
                .messageType(messageType)
                .magicCookie(magicCookie)
                .controlMessageType(controlMessageType)
                .reserved0(reserved0)
                .protocalVersion(protocalVersion)
                .reserved1(reserved1)
                .frameCapabilities(framingCapabilities)
                .bearerCapabilities(bearerCapabilities)
                .maximunChanels(maximunChannles)
                .firewareRevision(firewareRevision)
                .hostName(hostName)
                .vendorName(vendorName)
                .content(hex)
                .build();

        PPTPRequestWrapper pptpRequestWrapper = PPTPRequestWrapper.builder()
                .length(hex.substring(0, 4))
                .messageType(hex.substring(4, 8))
                .magicCookie(hex.substring(8, 16))
                .controlMessageType(hex.substring(16, 20))
                .reserved0(hex.substring(20, 24))
                .protocalVersion(hex.substring(24, 28))
                .reserved1(hex.substring(28, 32))
                .frameCapabilities(hex.substring(32, 40))
                .bearerCapabilities(hex.substring(40, 48))
                .maximunChanels(hex.substring(48, 52))
                .firewareRevision(hex.substring(52, 56))
                .hostName(hostName)
                .vendorName(vendorName)
                .content(hex)
                .request(pptpRequest)
                .remoteSocketAddress(channel.getRemoteAddress())
                .localSocketAddress(channel.getLocalAddress())
                .build();

        return pptpRequestWrapper;
    }
}
