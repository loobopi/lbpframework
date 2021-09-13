package org.lbpframework.vpn.handler;

import lombok.Builder;
import org.lbpframework.HexConverter;
import org.lbpframework.logging.LoggerUtil;
import org.lbpframework.vpn.mapper.PPTPMapper;
import org.lbpframework.vpn.processor.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
@Builder
public class NIOSocketReadableChannelHandler implements RequestHandler{

    @Override
    public void handler(Selector selector, Channel channel) {
        try{
            SocketChannel socketChannel = (SocketChannel) channel;
            socketChannel.register(selector, SelectionKey.OP_READ);
            socketChannel.configureBlocking(false);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = 0;
            do{
                count = socketChannel.read(buffer);
                if(count <= 0 ){
                    break;
                }

                //获取解析器;
                int controlMessageType = PPTPRequestParser.getControlMessageType(buffer);
                RequestProcessor requestProcessor = RequestProcessorFactory.getProcessor(controlMessageType);

                //解析出PPTPStartControlConnectionRequest
                PPTPRequestWrapper requestWrapper = requestProcessor.parser(socketChannel,buffer);
                //返回数据
                PPTPReplyWrapper replyWrapper = PPTPMapper.INSTANCE.toPPTPStartControlConnectionReplyWrapper(requestWrapper);
                //设置
                PPTPReply pptpReply = PPTPMapper.INSTANCE.toPPTPStartControlConnectionReply(requestWrapper.getRequest());
                replyWrapper.setResponse(pptpReply);

                ResponseProcessor responseProcessor = ResponseProcessorFactory.getProcessor(controlMessageType);
                ByteBuffer replyBuffer = responseProcessor.parserEntity(replyWrapper);

                //输出请求数据
                LoggerUtil.info(requestWrapper);
                //输出结果数据
                LoggerUtil.info(replyWrapper);
                //打印输出结果
                LoggerUtil.info(HexConverter.convertToHex(replyBuffer.array()));
                //输出流到channel中；
                socketChannel.write(replyBuffer);
            }while(true);
        }catch(Exception e){
            LoggerUtil.info(e.getMessage());
        }
    }
}