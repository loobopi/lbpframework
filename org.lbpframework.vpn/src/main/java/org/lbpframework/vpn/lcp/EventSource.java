package org.lbpframework.vpn.lcp;

import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import jpcap.packet.DatalinkPacket;
import jpcap.packet.Packet;
import jpcap.packet.TCPPacket;

import java.io.IOException;
import java.util.Date;

public class EventSource {

    public static final int snaplen = 0;
    public static final boolean promisc = true;
    public static final int toms = 1;

    EventListener eventListener = null;

    NetworkInterface networkInterface;

    JpcapCaptor jpcapCaptor;

    /**
     * 监听器
     * @param networkInterface
     */
    public EventSource(NetworkInterface networkInterface) {
        this.networkInterface = networkInterface;
        try {
            this.jpcapCaptor = JpcapCaptor.openDevice(networkInterface,snaplen,promisc,toms);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void register(EventListener eventListener){
        this.eventListener = eventListener;
    }

    /**
     * 事件监听实现;
     */
    public void handler(){
        Packet packet = jpcapCaptor.getPacket();
        if(packet != null){
            Event event = new Event(packet,new Date());
            this.eventListener.handler(event);

            jpcapCaptor.getJpcapSenderInstance().sendPacket(packet);
        }
    }
}
