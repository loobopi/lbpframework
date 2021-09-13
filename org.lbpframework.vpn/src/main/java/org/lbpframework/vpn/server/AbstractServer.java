package org.lbpframework.vpn.server;

public interface AbstractServer extends Server{


    public default void start(){
        this.startInterval();
    }

    public abstract void stopInterval();

    public abstract void startInterval();
}
