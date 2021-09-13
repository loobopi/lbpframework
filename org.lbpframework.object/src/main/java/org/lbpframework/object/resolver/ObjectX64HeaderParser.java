package org.lbpframework.object.resolver;

import org.lbpframework.HexConverter;

public class ObjectX64HeaderParser extends ObjectHeader{

    public ObjectX64HeaderParser(String header) {
        this.header = header;
    }

    @Override
    public ObjectHeader parserMarkWord() {
        String hashCodeBinaryString = header.substring(33,40) + header.substring(24,32) + header.substring(16,24) + header.substring(8,16);
        String s = HexConverter.convertToHex(HexConverter.convertToBinaryArray(hashCodeBinaryString));
        int hashCode = HexConverter.convertBinaryStringToInt(hashCodeBinaryString);

        markWord = new MarkWord();
        //二进制数组
        markWord.setBytes(HexConverter.convertToBinaryArray(hashCodeBinaryString));
        //lock
        markWord.setLock(header.substring(6,8));
        //biasedLock
        markWord.setBiasedLock(header.substring(5,6));
        //hashCode
        if(MarkWord.BiasedLock.getBiasedLock(markWord.getLock(),markWord.getBiasedLock()).equals(MarkWord.BiasedLock.BIASED_LOCK_01_0)){
            markWord.setHashCode(hashCode);
        }else{
            markWord.setHashCode(-1);
        }
        //分代年龄
        markWord.setAge(String.valueOf(HexConverter.convertBinaryStringToInt(header.substring(1,5))));

        return this;
    }
}
