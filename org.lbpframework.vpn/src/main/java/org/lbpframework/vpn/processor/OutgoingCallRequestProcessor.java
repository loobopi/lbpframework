package org.lbpframework.vpn.processor;

import org.lbpframework.HexConverter;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class OutgoingCallRequestProcessor implements RequestProcessor{

    @Override
    public PPTPRequestWrapper parser(SocketChannel channel, ByteBuffer buffer)throws Exception{
        byte[] array = buffer.array();
        String hex = HexConverter.convertToHex(array);

        //解析 OutgoingCallRequest 数据
        int length = HexConverter.convertToInt(hex.substring(0, 4));
        int messageType =  HexConverter.convertToInt(hex.substring(4, 8));
        String magicCookie = hex.substring(8, 16);
        int controlMessageType = HexConverter.convertToInt(hex.substring(16, 20));

        String reserved0 = hex.substring(20, 24);
        int callId = HexConverter.convertToInt(hex.substring(24,28));
        int callSerialNumber = HexConverter.convertToInt(hex.substring(28,32));
        int minimumBPS = HexConverter.convertToInt(hex.substring(32,40));
        int maximumBPS = HexConverter.convertToInt(hex.substring(40,48));
        int bearerType = HexConverter.convertToInt(hex.substring(48,56));
        int framingType = HexConverter.convertToInt(hex.substring(56,64));
        int packetReceiveWindowSize = HexConverter.convertToInt(hex.substring(64,68));
        int packetProcessingDelay = HexConverter.convertToInt(hex.substring(68,72));
        int phoneNumberLength = HexConverter.convertToInt(hex.substring(72,76));
        String reserved1 = hex.substring(76, 80);
        String phoneNumber = hex.substring(80,208);
        String subaddress = hex.substring(208,336);

        PPTPRequest pptpRequest = PPTPRequest.builder()
                .length(length)
                .messageType(messageType)
                .magicCookie(magicCookie)
                .controlMessageType(controlMessageType)
                .reserved0(reserved0)
                .callId(callId)
                .callSerialNumber(callSerialNumber)
                .minimumBPS(minimumBPS)
                .maximumBPS(maximumBPS)
                .bearerType(bearerType)
                .framingType(framingType)
                .packetReceiveWindowSize(packetReceiveWindowSize)
                .packetProcessingDelay(packetProcessingDelay)
                .reserved1(reserved1)
                .phoneNumberLength(phoneNumberLength)
                .phoneNumber(phoneNumber)
                .subaddress(subaddress)
                .build();

        PPTPRequestWrapper pptpRequestWrapper = PPTPRequestWrapper.builder()
                .length(hex.substring(0, 4))
                .messageType(hex.substring(4, 8))
                .magicCookie(hex.substring(8, 16))
                .controlMessageType(hex.substring(16, 20))
                .reserved0(hex.substring(20, 24))
                .callId(hex.substring(24,28))
                .callSerialNumber(hex.substring(28,32))
                .minimumBPS(hex.substring(32,40))
                .maximumBPS(hex.substring(40,48))
                .bearerType(hex.substring(48,56))
                .framingType(hex.substring(56,64))
                .packetReceiveWindowSize(hex.substring(64,68))
                .packetProcessingDelay(hex.substring(68,72))
                .reserved1(hex.substring(76, 80))
                .phoneNumberLength(hex.substring(72,76))
                .phoneNumber(hex.substring(80,208))
                .subaddress(hex.substring(208,336))
                .build();

        return pptpRequestWrapper;
    }
}
