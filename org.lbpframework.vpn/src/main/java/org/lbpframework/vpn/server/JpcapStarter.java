package org.lbpframework.vpn.server;

import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import org.lbpframework.logging.LoggerUtil;
import org.lbpframework.vpn.lcp.EventSource;
import org.lbpframework.vpn.lcp.LcpNetworkInterfaceListener;
import org.lbpframework.vpn.lcp.NetworkInterfaceUtils;
import org.lbpframework.vpn.properties.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JpcapStarter implements AbstractServer {

    Thread thread = null;

    @Autowired
    ApplicationProperties properties;

    @Override
    public void stopInterval() {
        thread.stop();
    }

    /**
     * 启动监听服务网络接口卡
     */
    @Override
    public void startInterval() {
        LoggerUtil.info("启动JpcapStarter..............");
        thread = new Thread(()->{

            NetworkInterface networkInterface = NetworkInterfaceUtils.getNetworkInterfaceByName(properties.getNetworkInterfaceName());

            JpcapCaptor jpcapCaptor = null;
            try {
                jpcapCaptor = JpcapCaptor.openDevice(networkInterface, 65535, false, 20);

                EventSource eventSource = new EventSource(networkInterface);
                eventSource.register(new LcpNetworkInterfaceListener(properties.getDst(),properties.getSrc()));

                do{
                    //监听处理;
                    eventSource.handler();
                }while(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        LoggerUtil.info("启动JpcapStarter成功..............");
    }
}
