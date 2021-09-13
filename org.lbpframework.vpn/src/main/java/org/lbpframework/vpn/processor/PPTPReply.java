package org.lbpframework.vpn.processor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PPTPReply {


    int length;
    int messageType;
    String magicCookie;
    int controlMessageType;//值为2；
    String reserved0 ;
    int protocalVersion = 0;
    int resultCode; //成功为1，2为通用错误，3 channel已存在，4 未授权  5 PPTP协议版本不支持
    int errorCode;//表示错误码，一般值为0，除非Result Code值为2，不同的错误码表示不同的含义。
    String frameCapabilities;
    String bearerCapabilities;
    int maximunChanels;
    int firewareRevision;
    String hostName;
    String vendorName;
    String content;

}
