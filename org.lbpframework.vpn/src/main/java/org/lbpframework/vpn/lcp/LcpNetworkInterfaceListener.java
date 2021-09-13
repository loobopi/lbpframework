package org.lbpframework.vpn.lcp;

import jpcap.packet.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.lbpframework.HexConverter;
import org.lbpframework.logging.LoggerUtil;

import java.net.InetAddress;

@Data
@AllArgsConstructor
public class LcpNetworkInterfaceListener implements EventListener{

    String dst;
    String src;

    @Override
    public void handler(Event event) {
        Packet packet = event.getPacket();
        //测试环境，只监控手机和电脑之间的流量;
        if(packet.toString().contains(dst) && packet.toString().contains(src)){
            //按照不同的封包类型进行处理;
            if(packet instanceof TCPPacket){
                LoggerUtil.info("TCPPacket-{}",packet);
            }else if(packet instanceof UDPPacket){
               /* LoggerUtil.info("UDPPacket-{}",packet);*/
            }else if(packet instanceof ARPPacket){
                /*LoggerUtil.info("ARPPacket-{}",packet);*/
            }else if(packet instanceof ICMPPacket){
                /*LoggerUtil.info("ICMPPacket-{}",packet);*/
            }else if(packet instanceof IPPacket){
                IPPacket ipPacket = (IPPacket) packet;
                InetAddress src_ip = ipPacket.src_ip;
                InetAddress dst_ip = ipPacket.dst_ip;
                InetAddress temp_ip = ipPacket.src_ip;
                ipPacket.dst_ip = temp_ip;
                ipPacket.src_ip = dst_ip;
                byte[] data = ipPacket.data;
                EthernetPacket datalink = (EthernetPacket)ipPacket.datalink;
                byte[] dst_mac = datalink.dst_mac;
                datalink.dst_mac = datalink.src_mac;
                datalink.src_mac = dst_mac;
                String dataStr  = HexConverter.convertToHex(data);
                LoggerUtil.info("IPPacket-{}",packet);

            }

        }

    }
}
