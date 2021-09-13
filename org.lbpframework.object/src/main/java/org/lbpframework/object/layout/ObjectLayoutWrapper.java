package org.lbpframework.object.layout;

import org.lbpframework.BeanUtils;
import org.lbpframework.logging.LoggerUtil;
import org.mapstruct.ap.shaded.freemarker.template.ObjectWrapper;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;
import org.openjdk.jol.vm.VirtualMachine;

public class ObjectLayoutWrapper {

    ObjectLayout objectLayout = null;
    int hashCode = 0;

    public ObjectLayoutWrapper(int hashCode,ObjectLayout objectLayout) {
        this.hashCode = hashCode;
        this.objectLayout = objectLayout;
    }

    /**
     * 打印对象信息
     */
    public void toPrintable(){
        LoggerUtil.info(objectLayout.toPrintable());
    }

    /**
     * 获取对象头
     * @return
     */
    public String getFomartHeader(){
        VirtualMachine vm = VM.current();
        StringBuilder header = new StringBuilder();
        for(long off = 0L; off < (long)objectLayout.headerSize(); off += 4L) {
            int word = vm.getInt(objectLayout.getClassData().instance(), off);
            header.append(" (" + objectLayout.toBinary(word >> 0 & 255) + " " + objectLayout.toBinary(word >> 8 & 255) + " " + objectLayout.toBinary(word >> 16 & 255) + " " + objectLayout.toBinary(word >> 24 & 255) + ") \n");
        }
        return new String(header);
    }

    public String getHeader(){
        VirtualMachine vm = VM.current();
        StringBuilder header = new StringBuilder();
        for(long off = 0L; off < (long)objectLayout.headerSize(); off += 4L) {
            int word = vm.getInt(objectLayout.getClassData().instance(), off);
            header.append(objectLayout.toBinary(word >> 0 & 255) + objectLayout.toBinary(word >> 8 & 255) + objectLayout.toBinary(word >> 16 & 255) + objectLayout.toBinary(word >> 24 & 255));
        }
        return new String(header);
    }

    public void parserHeader(){

    }
}
