参考文章：
    https://blog.csdn.net/qq_36434742/article/details/106854061
    https://www.cnblogs.com/tiancai/p/12630305.html
    https://blog.csdn.net/u011433316/article/details/109158233
    
官网解释：http://openjdk.java.net/groups/hotspot/docs/HotSpotGlossary.html

指针压缩：-XX:-UseCompressOops

对象布局
    对象头：object header(12B)
        Markword(8B)
        Klassword(开启指针压缩4B，不开8B)
    对象实例数据
    填充数据

对象类型:(java.lang.Object)
 OFFSET SIZE(字节)   TYPE DESCRIPTION                                     VALUE
      0     4        (对象头 - Markword)                                           90 e7 a7 02 (10010000 11100111 10100111 00000010) (44558224)
      4     4        (对象头 - Markword)                                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4        (对象头 - Klassword)                                          e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
     12     4        ((填充数据)loss due to the next object alignment)
Instance size(字节): 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

10010000 11100111 10100111 00000010
00000000 00000000 00000000 00000000

Markword(小端存储)
    (25位) - unused未使用 对应位(56-64 48-56 40-48 32)
    (31位) - hashcode 对应位(33-40 24-32 16-24 8-16)
    ()  - 分代年龄