package org.lbpframework;

import cn.hutool.core.util.StrUtil;
import com.google.common.io.BaseEncoding;

import javax.xml.bind.DatatypeConverter;
import java.math.BigInteger;

/**
 * 进制转换器
 */
public class HexConverter {

    public final static char[] HEX_CHARS = "0123456789ABCDEF".toCharArray();


    /**
     * 将二进制字符串转为二进制数组
     * @param binaryString
     * @return
     */
    public static byte[] convertToBinaryArray(String binaryString){
        int length = binaryString.length();
        byte[] bytes = new byte[length];
        for (int i=0;i<length;i++){
            bytes[i] = ((byte) binaryString.charAt(i));
        }
        return bytes;
    }

    /**
     * 字符串转为十六进制
     * @param str
     * @return
     */
    public static String convertToHexString(String str){
        StringBuilder sb = new StringBuilder();
        byte[] bs = str.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(HEX_CHARS[bit]);
            bit = bs[i] & 0x0f;
            sb.append(HEX_CHARS[bit]);
        }
        return sb.toString().trim();
    }

    /**
     * 十六进制转为十进制;
     * @param hex
     * @return
     */
    public static int convertToInt(String hex){
        BigInteger bigint = new BigInteger(hex, 16);
        int numb = bigint.intValue();
        return numb;
    }

    /**
     * 将二进制数组转为十六进制;
     * @param bytes
     * @return
     */
    public static String convertToHex(byte[] bytes){
        return BaseEncoding.base16().encode(bytes);
    }

    public static int convertToInt(byte[] bytes){
        return convertToInt(convertToHex(bytes));
    }

    /**
     * 将int 类型转为2进制;
     * @param n
     * @return
     */
    public static String convertToBinary(int n){
        String s = Integer.toBinaryString(n);
        int deficit = 8 - s.length();
        for(int c = 0; c < deficit; ++c) {
            s = "0" + s;
        }
        return s;
    }

    /**
     * 16进制转换成为string类型字符串
     * @param hexString
     * @return
     */
    public static String convertToString(String hexString,String charset) {
        if (StrUtil.isEmpty(hexString)) {
            throw new RuntimeException("字符串不能为空;=");
        }
        hexString = hexString.replace(" ", "");
        byte[] baKeyword = new byte[hexString.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(hexString.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            hexString = new String(baKeyword, charset);
            new String();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return hexString;
    }

    /**
     * 将16进制串转成二进制数组，用于解密(方式1)
     *
     * @param hexStr
     * @return
     */
    public static byte[] convertToByteArray(String hexStr) {
        return BaseEncoding.base16().decode(hexStr);
    }

    /**
     * int 类型转16进制
     * @param i
     * @return
     */
    public static String convertToHex(int i){
        return Integer.toHexString(i);
    }

    /**
     * int 转为定长16进制数据
     * @param n
     * @param size
     * @return
     */
    public static String convertToHex(int n,int size) {
        StringBuffer s = new StringBuffer();
        String a;
        char []b = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        while(n != 0){
            s = s.append(b[n%16]);
            n = n/16;
        }
        a = s.reverse().toString();
        a  = addZore(a,size);
        return a;
    }

    public static int convertBinaryStringToInt(String binaryString){
        return Integer.parseInt(binaryString, 2);
    }

    /**
     * 补0
     * @param str
     * @param size
     * @return
     */
    public static String addZore(String str, int size){
        if (str.length()<size){
            str= "0"+str;
            str=addZore(str,size);
            return str;
        }else {
            return str;
        }
    }


    public static String convertToBinaryString(int x) {
        String s = Integer.toBinaryString(x);
        int deficit = 8 - s.length();

        for(int c = 0; c < deficit; ++c) {
            s = "0" + s;
        }

        return s;
    }
}
