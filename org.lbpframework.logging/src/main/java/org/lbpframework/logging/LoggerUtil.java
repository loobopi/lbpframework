package org.lbpframework.logging;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 日志打印工具类;
 */
public class LoggerUtil {

    static Logger logger = LoggerFactory.getLogger(LoggerUtil.class);
    static ConcurrentHashMap loggerMaps = new ConcurrentHashMap();

    public static Logger getLogger(Class clazz){
        if(loggerMaps.containsKey(clazz)){
            return (Logger) loggerMaps.get(clazz);
        }else{
            loggerMaps.putIfAbsent(clazz,LoggerFactory.getLogger(clazz));
            return getLogger(clazz);
        }
    }

    public static void debug(Object msg,Object...objects){
        logger.info(
                msg.toString()
                        + "\n"
                        + LoggerLine.LINE
                ,objects);
    }

    public static void info(Class clazz,Object msg,Object...objects){
        Logger LOG = getLogger(clazz);
        LOG.info(
                msg.toString()
                        + "\n"
                        + LoggerLine.LINE
                ,objects);
    }

    public static void info(Object msg,Object...objects){
        logger.info(
                msg.toString()
                + "\n"
                + LoggerLine.LINE
                ,objects);
    }
}
