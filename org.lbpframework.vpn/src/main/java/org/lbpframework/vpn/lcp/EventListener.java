package org.lbpframework.vpn.lcp;



/**
 *
 */
public interface EventListener {

    /**
     * 注册监听网络接口卡
     * @param packet
     */
    public void handler(Event packet);
}
