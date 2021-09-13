package org.lbpframework.object.test;

import cn.hutool.crypto.digest.MD5;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Md5Test {

    /**
     * MD5防止文件篡改
     * @throws IOException
     */
    @Test
    public void test1() throws IOException {
        //b35b0abcc0cdfb5290f2d3e0fff73706 -> d446bfc4c121b667f0f5c5865675707d
        System.out.println(MD5.create().digestHex(FileUtils.readFileToByteArray(new File("D://2.txt"))));
        System.out.println(MD5.create().setSalt("".getBytes()).digestHex(FileUtils.readFileToByteArray(new File("D://2.txt"))));
    }

    @Test
    public void hash(){
        User u1 = new User("lbp","123456");
        User u2 = new User("lbp","123456");
        Map<Object,Object> userMaps = new HashMap<Object,Object>();
        userMaps.put(u1,u1);
        userMaps.put(u2,u2);
        Object o = userMaps.get(u2);
        System.out.println(u1.equals(u2));

        /***
         * 两个一样的对象是可以存在一个HashMap 里面的
         *
         */
    }

    @Test
    public void test3(){
        String s1 = new String("aa"),s2 = new String("aa");
        System.out.println(s1.hashCode() + "-" + s2.hashCode());
        System.out.println(s1.equals(s2));
        System.out.println((s1 == s2));
    }
}
