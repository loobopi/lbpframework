package org.lbpframework.object.resolver;

import lombok.Builder;
import lombok.Data;
import org.lbpframework.logging.LoggerUtil;
import org.lbpframework.object.layout.ObjectLayout;
import org.lbpframework.object.layout.ObjectLayoutWrapper;

/**
 * 对象解析器
 */
@Data
public class ObjectResolver {



    ObjectLayoutWrapper objectLayoutWrapper = null;

    /**
     * 对象头
     */
    public ObjectHeader header;

    public ObjectResolver(ObjectLayoutWrapper objectLayoutWrapper) {
        this.objectLayoutWrapper = objectLayoutWrapper;
    }

    public ObjectResolver resolve(){
        //解析对象头
        String header = objectLayoutWrapper.getHeader();
        ObjectHeader objectHeader = new ObjectX64HeaderParser(header).parserMarkWord();

        //打印markWord信息
        LoggerUtil.info(this.getClass(),objectHeader.markWord);
        return this;
    }
    public void toPrintable(){
        objectLayoutWrapper.toPrintable();
    }
}
