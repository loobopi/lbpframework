package org.lbpframework.vpn.processor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * PPTP协议 controlConnectionRequest
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PPTPRequest {

    Map<Object,Object> contentMaps = new HashMap<Object,Object>();

    int length;
    int messageType;
    String magicCookie;
    int controlMessageType;
    String reserved0;
    int protocalVersion = 0;
    String reserved1;
    int frameCapabilities;
    int bearerCapabilities;
    int maximunChanels;
    int firewareRevision;
    String hostName;
    String vendorName;
    String content;

    int callId ;
    int callSerialNumber;
    int minimumBPS;
    int maximumBPS;
    int bearerType;
    int framingType;
    int packetReceiveWindowSize;
    int packetProcessingDelay;
    int phoneNumberLength;
    String phoneNumber;
    String subaddress;

}
