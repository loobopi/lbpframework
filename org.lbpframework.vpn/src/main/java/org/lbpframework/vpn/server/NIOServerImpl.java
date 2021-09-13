package org.lbpframework.vpn.server;

import org.lbpframework.logging.LoggerUtil;
import org.lbpframework.vpn.handler.NIOSocketAcceptableChannelHandler;
import org.lbpframework.vpn.handler.NIOSocketReadableChannelHandler;
import org.lbpframework.vpn.properties.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

@Component
public class NIOServerImpl implements Server{

    @Autowired
    ApplicationProperties properties;
    //选择器
    Selector selector;
    //服务器ServerSocketChannel
    ServerSocketChannel serverSocketChannel;

    Thread thread = null;

    public NIOServerImpl(){
    }

    public void stopInterval() {

    }

    public void startInterval() {
        LoggerUtil.info("启动NIOServerImpl..............");
        thread = new Thread(()->{
            try{
                //创建ServerSocketChannel
                serverSocketChannel = ServerSocketChannel.open();
                //设置监听
                serverSocketChannel.socket().bind(new InetSocketAddress(properties.getPort()));
                //设置非阻塞模式
                serverSocketChannel.configureBlocking(false);
                //创建选择器
                selector = Selector.open();
                //注册channel 到selector
                serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);

                int selectCount = 0;
                do{
                    selectCount = selector.select(3000);
                    if(selectCount == 0){
                        continue;
                    }

                    //获取到selectionKey 集合
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while(iterator.hasNext()){
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        //激活事件集合;
                        int interestOpsNum = key.readyOps();
                        if(interestOpsNum > 0){
                            if(key.isAcceptable()){
                                NIOSocketAcceptableChannelHandler
                                        .builder()
                                        .build()
                                        .handler(selector,serverSocketChannel.accept());
                            }
                            if(key.isReadable()){
                                NIOSocketReadableChannelHandler
                                        .builder()
                                        .build()
                                        .handler(selector,key.channel());
                            }
                        }
                    }
                    selectionKeys.clear();
                }while(true);


            }catch(Exception e){
                e.printStackTrace();
            }
        });
        thread.start();
        LoggerUtil.info("启动NIOServerImpl成功..............");
    }

}
