package org.lbpframework.object.resolver;

import lombok.*;

/**
 * 对象头MarkWord 信息
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MarkWord {

    /**
     * MarkWord 对应二进制数据
     */
    @Setter
    byte[] bytes;

    /**
     * 对象hashCode
     */
    @Setter
    int hashCode;
    /**
     * GC 分代年龄
     */
    @Setter
    String age;
    /**
     * 锁状态标志
     */
    @Setter
    String biasedLock;
    /**
     * 线程持有的锁
     */
    @Setter
    String lock;
    /**
     * 偏向线程ID
     */
    @Setter
    String towardThreadId;
    /**
     * 偏向时间戳
     */
    @Setter
    String epoch;
    /**
     * 中不同的锁标识位，代表着不同的锁状态：
     */
    @Setter
    String markOop;

    enum BiasedLock{

        BIASED_LOCK_01_0("01","0","无锁"),
        BIASED_LOCK_01_1("01","1","偏向锁"),
        BIASED_LOCK_00_0("00","0","轻量级锁"),
        BIASED_LOCK_10_0("10","0","重量级锁"),
        BIASED_LOCK_11_0("11","0","GC标记")
        ;

        BiasedLock(String lock, String biasedLock, String name) {
            this.lock = lock;
            this.biasedLock = biasedLock;
            this.name = name;
        }

        private String lock;
        private String biasedLock;
        private String name;

        public static BiasedLock getBiasedLock(String lock,String biasedLock){
            for (BiasedLock biasedLockValue : BiasedLock.values()) {
                if(biasedLockValue.biasedLock.equals(biasedLock) && biasedLockValue.lock.equals(lock)){
                    return biasedLockValue;
                }
            }
            return null;
        }

        public static String getName(String lock,String biasedLock){
            return getBiasedLock(lock,biasedLock).name;
        }


    }


    public String toString() {
        return "MarkWord(hashCode="
                + this.getHashCode()
                + ", age="
                + this.getAge()
                + ", 锁信息=【lock="
                + this.lock + ",biasedLock=" + this.biasedLock + ","
                + BiasedLock.getName(this.lock,this.biasedLock)
                + "】" +
                ", towardThreadId="
                + this.getTowardThreadId()
                + ", epoch=" + this.getEpoch()
                + ", markOop="
                + this.getMarkOop() + ")";
    }

}
