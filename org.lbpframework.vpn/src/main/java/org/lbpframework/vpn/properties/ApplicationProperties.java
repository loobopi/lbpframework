package org.lbpframework.vpn.properties;

import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component("properties")
public class ApplicationProperties {

    @Value("${vpn.type}")
    String type;
    @Value("${vpn.port}")
    int port;
    @Value("${vpn.networkInterfaceName}")
    String networkInterfaceName;
    @Value("${vpn.filter.dst}")
    String dst;
    @Value("${vpn.filter.src}")
    String src;

}
