package org.lbpframework.vpn.processor;

/**
 * 请求处理器
 */
public abstract class RequestProcessorFactory extends Processor{


    public static RequestProcessor getProcessor(int controlMessageType)throws Exception{
        //start-control-connection-request请求
        if(CMT_START_CONTROL_CONNECTION_REQUEST == controlMessageType){
            return new StartControlConnectionRequestProcessor();
        }else if(CMT_OUTGOING_CALL_REQUEST_ == controlMessageType){
            return new OutgoingCallRequestProcessor();
        }
        throw new RuntimeException("未找到处理器");
    }

}
