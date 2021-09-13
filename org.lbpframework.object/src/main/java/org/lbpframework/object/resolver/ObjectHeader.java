package org.lbpframework.object.resolver;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.lbpframework.HexConverter;
import org.lbpframework.logging.LoggerUtil;

/**
 * 对象头信息
 */
public abstract class ObjectHeader {

    String header;

    /**
     * 对象头markword 标志
     */
    MarkWord markWord;



    /**
     * 解析对象头
     */
    public ObjectHeader parser(){
        //
        return this;
    }

    public abstract ObjectHeader parserMarkWord();

}
