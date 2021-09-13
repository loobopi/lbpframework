package org.lbpframework.vpn.processor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.net.SocketAddress;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PPTPRequestWrapper {

    PPTPRequest request;

    String length;
    String messageType;
    String magicCookie;
    String controlMessageType;
    String reserved0;
    String protocalVersion;
    String reserved1;
    String frameCapabilities;
    String bearerCapabilities;
    String maximunChanels;
    String firewareRevision;
    String hostName;
    String vendorName;
    String content;
    String callId;
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
