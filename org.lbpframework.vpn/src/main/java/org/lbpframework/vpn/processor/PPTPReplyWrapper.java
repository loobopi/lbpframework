package org.lbpframework.vpn.processor;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.lbpframework.HexConverter;

import java.net.SocketAddress;
import java.nio.ByteBuffer;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PPTPReplyWrapper {

    //成功
    public static final int STATUS_SUCCESS = 1;
    //通用错误
    public static final int STATUS_ERROR = 2;
    //channel 已存在
    public static final int STATUS_EXIST = 3;
    //未授权
    public static final int STATUS_UN_AUTH = 4;
    //版本不支持
    public static final int STATUS_VERSION_ERROR = 5;
    //一般情况
    public static final int ERROR_CODE = 0;
    //一般情况
    public static final int CAUSE_CODE = 0;

    PPTPReply response;

    String length;
    String messageType;
    String magicCookie;
    String controlMessageType;//值为2；
    String reserved0 ;
    String protocalVersion;
    String resultCode; //成功为1，2为通用错误，3 channel已存在，4 未授权  5 PPTP协议版本不支持
    String errorCode;//表示错误码，一般值为0，除非Result Code值为2，不同的错误码表示不同的含义。
    String frameCapabilities;
    String bearerCapabilities;
    String maximunChanels;
    String firewareRevision;
    String hostName;
    String vendorName;
    String content;

    String callId;
    String peerCallId;
    String callSerialNumber;
    String minimumBPS;;
    String maximumBPS;
    String bearerType;
    String framingType;
    String packetReceiveWindowSize;
    String packetProcessingDelay;
    String phoneNumberLength;
    String phoneNumber;
    String subaddress;

    SocketAddress remoteSocketAddress;
    SocketAddress localSocketAddress;
}
