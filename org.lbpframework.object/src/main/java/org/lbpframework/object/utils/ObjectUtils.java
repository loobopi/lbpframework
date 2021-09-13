package org.lbpframework.object.utils;

import org.lbpframework.HexConverter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class ObjectUtils {



    /**
     * 将对象转为字节数组
     * @return
     */
    public static byte[] readObject(Object object){
        ByteArrayOutputStream bos = null;
        ObjectOutputStream os = null;
        try {
            bos = new ByteArrayOutputStream();
            os = new ObjectOutputStream(bos);

            os.writeObject(object);
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 将对象转为十六进制
     * @return
     */
    public static String readObjectHex(Object object){
        return HexConverter.convertToHex(readObject(object));
    }

    /**
     * 将对象写入文件
     */
    public static void write(OutputStream os,Object object){
        try {
            os.write(readObject(object));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
