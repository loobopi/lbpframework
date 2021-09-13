package org.lbpframework.vpn;

import org.lbpframework.logging.LoggerUtil;
import org.lbpframework.vpn.aware.ApplicationWrapper;
import org.lbpframework.vpn.properties.ApplicationProperties;
import org.lbpframework.vpn.server.JpcapStarter;
import org.lbpframework.vpn.server.NIOServerImpl;
import org.lbpframework.vpn.server.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * VPN
 */
@SpringBootApplication
public class VPNApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(VPNApplication.class,args);
        //启动服务
        ((NIOServerImpl) ApplicationWrapper.getBean(NIOServerImpl.class)).startInterval();
        //启动服务
        ((JpcapStarter) ApplicationWrapper.getBean(JpcapStarter.class)).startInterval();
    }
}
