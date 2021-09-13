package org.lbpframework.vpn.test.server;

import com.google.common.io.BaseEncoding;
import org.junit.Test;
import org.lbpframework.HexConverter;

public class HexLengthTest {

    @Test
    public void test() {
        String str1 = "002000011A2B3C4D000800002F772F770100000005F5E100004000000000";
        String str2 = "616e6f6e796d6f757300000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";

        System.out.println(str1.length());
        System.out.println(str2.length());
        HexConverter.convertToInt("0080");

    }

    @Test
    public void test2(){
        String str1 = "009C00011A2B3C4D00010000010000010000000000030000000300010000616E6F6E796D6F75730000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        System.out.println(str1.length());
        byte[] decode = BaseEncoding.base16().decode(str1);
        String encode = BaseEncoding.base16().encode(decode);
        System.out.println(str1);
        System.out.println(encode);
    }
}
