package org.lbpframework.object.test;

import org.junit.Test;
import org.lbpframework.HexConverter;
import org.lbpframework.object.layout.ObjectLayout;
import org.lbpframework.object.layout.ObjectLayoutWrapper;
import org.lbpframework.object.resolver.ObjectResolver;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ObjectHashCodeTest {

    private final static String tt1 = "1";
    private String tt2 = "2";


    @Test
    public void hashCodeTest(){
        TestObject object = new TestObject(new Object());
        System.out.println(object.hashCode());
        ObjectResolver resolver = new ObjectResolver(new ObjectLayoutWrapper(object.hashCode(),ObjectLayout.parseInstance(object))).resolve();
    }

    @Test
    public void test11(){
        String x = "00000000 00000101 10101011 1100001";
        int x1 = HexConverter.convertBinaryStringToInt(x);
        System.out.println(x1);
        System.out.println(HexConverter.convertToHex(x1));
    }

    @Test
    public void hashcode(){
        try {
            Object obj = new Object();
            ObjectResolver resolver = new ObjectResolver(new ObjectLayoutWrapper(obj.hashCode(),ObjectLayout.parseInstance(obj)));

            synchronized (obj){
                resolver.resolve();
                resolver.toPrintable();
            }
            synchronized (obj){
                resolver.resolve();
                resolver.toPrintable();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test2(){
        int i = 100;
        System.out.println(i>>5);
        System.out.println(HexConverter.convertToHex(i));
        System.out.println(HexConverter.convertToBinary(100));
        byte[] bytes = HexConverter.convertToByteArray(HexConverter.convertToHex(100));
        System.out.println(HexConverter.convertToInt(bytes));

        //2048425748
        String binaryString = "1110010010111110000000100000000";
        System.out.println("测试" + Integer.parseInt("1110010010111110000000100000000",2));
        System.out.println(HexConverter.convertBinaryStringToInt(binaryString));
    }

    @Test
    public void test3(){
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = df.format(day);

        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM");
        String format = dateFormat.format(day);
        System.out.println(format);
    }
}
