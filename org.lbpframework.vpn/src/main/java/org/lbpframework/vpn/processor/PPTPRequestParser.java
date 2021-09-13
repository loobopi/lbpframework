package org.lbpframework.vpn.processor;

import org.lbpframework.HexConverter;
import java.nio.ByteBuffer;

public class PPTPRequestParser {

    public static int getControlMessageType(ByteBuffer byteBuffer){
        byte[] array = byteBuffer.array();
        String hex = HexConverter.convertToHex(array);
        //解析出请求类型；
        return HexConverter.convertToInt(hex.substring(16, 20));
    }
}
