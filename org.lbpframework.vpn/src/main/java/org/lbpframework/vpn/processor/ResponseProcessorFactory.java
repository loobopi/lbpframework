package org.lbpframework.vpn.processor;

import org.lbpframework.vpn.processor.OutgoingCallRequestProcessor;
import org.lbpframework.vpn.processor.RequestProcessor;
import org.lbpframework.vpn.processor.StartControlConnectionRequestProcessor;

/**
 * 相应处理器
 */
public class ResponseProcessorFactory extends Processor{

    public static ResponseProcessor getProcessor(int controlMessageType)throws Exception{
        //start-control-connection-request请求
        if(CMT_START_CONTROL_CONNECTION_REQUEST == controlMessageType){
            return new StartControlConnectionResponseProcessor();
        }else if(CMT_OUTGOING_CALL_REQUEST_ == controlMessageType){
            return new OutgoingResponseProcessor();
        }
        throw new RuntimeException("未找到处理器");
    }
}
