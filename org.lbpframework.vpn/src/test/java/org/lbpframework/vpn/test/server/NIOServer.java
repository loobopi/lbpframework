package org.lbpframework.vpn.test.server;

import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import jpcap.NetworkInterfaceAddress;
import jpcap.packet.Packet;
import org.junit.Test;
import org.lbpframework.vpn.lcp.EventSource;
import org.lbpframework.vpn.lcp.LcpNetworkInterfaceListener;
import sun.nio.ch.ChannelInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {

    public static void main(String[] args) throws Exception{
        //创建ServerSocketChannel -> ServerSocket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //创建selector对象
        Selector selector = Selector.open();
        //绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(8088));
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //将ServerSocketChannel注册到selector上；
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //循环等待客户端链接
        while(true){
            //等待一秒，如果一秒后无事件发生；
            if(selector.select(1000)==0){
                /*System.out.println("服务器等待1秒，无连接");*/
                continue;
            }

            //如果返回>0,获取到相关的SelectionKey集合，
            //表示已经获取到关注的事件；
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            //迭代器遍历；
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while(iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                //如果有新的客户端进行链接;
                if(selectionKey.isAcceptable()){
                    //给该客户端生成一个SocketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //设置为非阻塞模型；
                    socketChannel.configureBlocking(false);
                    //将当前的socketChannel 注册到selector 上，关注事件为OP_READ，同时给socketChannel 关联一个buffer
                    socketChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }else if(selectionKey.isConnectable()){

                }else if(selectionKey.isReadable()){
                    //发生OP_READ事件
                    SocketChannel readChannel = (SocketChannel)selectionKey.channel();
                    //获取该channel关联的buffer
                    ByteBuffer buffer = ((ByteBuffer) selectionKey.attachment());
                    //读取通道数据
                    int read = readChannel.read(buffer);
                    System.out.println("from client:" + new String(buffer.array()));
                }else if(selectionKey.isWritable()){

                }
                iterator.remove();
            }

        }
    }

    @Test
    public void test3() throws IOException {
        NetworkInterface[] deviceList = JpcapCaptor.getDeviceList();
        for (NetworkInterface networkInterface : deviceList) {
            NetworkInterfaceAddress[] addresses = networkInterface.addresses;
            String datalink_description = networkInterface.datalink_description;
            String datalink_name = networkInterface.datalink_name;
            String description = networkInterface.description;
            boolean loopback = networkInterface.loopback;
            byte[] mac_address = networkInterface.mac_address;
            String name = networkInterface.name;

        }

        JpcapCaptor jpcapCaptor = JpcapCaptor.openDevice(deviceList[0], 0, true, 1);
        Packet packet = jpcapCaptor.getPacket();

        EventSource eventSource = new EventSource(deviceList[0]);
        eventSource.register(new LcpNetworkInterfaceListener("",""));
        eventSource.handler();
    }

}
