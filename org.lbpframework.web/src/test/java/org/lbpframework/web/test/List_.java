package org.lbpframework.web.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * 在尾部插入数据，数据量较小时LinkedList比较快，因为ArrayList要频繁扩容，当数据量大时ArrayList比较快，因为ArrayList扩容是当前容量*1.5，大容量扩容一次就能提供很多空间，当ArrayList不需扩容时效率明显比LinkedList高，因为直接数组元素赋值不需new Node
 * 在首部插入数据，LinkedList较快，因为LinkedList遍历插入位置花费时间很小，而ArrayList需要将原数组所有元素进行一次System.arraycopy
 * 插入位置越往中间，LinkedList效率越低，因为它遍历获取插入位置是从两头往中间搜，index越往中间遍历越久，因此ArrayList的插入效率可能会比LinkedList高
 * 插入位置越往后，ArrayList效率越高，因为数组需要复制后移的数据少了，那么System.arraycopy就快了，因此在首部插入数据LinkedList效率比ArrayList高，尾部插入数据ArrayList效率比LinkedList高
 * LinkedList可以实现队列，栈等数据结构，这是它的优势
 */
public class List_ {

    /**
     * 头插法
     */
    @Test
    public void test1(){
        List linkedList01 = new LinkedList();
        long l = System.currentTimeMillis();
        for(int i=0;i<100000;i++){
            ((LinkedList) linkedList01).addFirst(i);
        }
        System.out.println(System.currentTimeMillis() - l);

        List list = new ArrayList();
        l = System.currentTimeMillis();
        for(int i=0;i<100000;i++){
            list.add(0,i);
        }
        System.out.println(System.currentTimeMillis() - l);

        /**
         * 采用头插法时 LinkedList 快于 ArrayList ；
         *
         *
         */
    }

    /**
     * 尾插法 采用尾插法时 LinkedList 慢于 ArrayList ;
     * ArrayList 尾插时，是不需要数据位移的，比较耗时的是数据的扩容时，需要拷贝迁移。
     * LinkedList 尾插时，与头插相比耗时点会在对象的实例化上。
     *
     */
    @Test
    public void test2(){
        List linkedList01 = new LinkedList();
        long l = System.currentTimeMillis();
        for(int i=0;i<10000000;i++){
            ((LinkedList) linkedList01).addLast(i);
        }
        System.out.println(System.currentTimeMillis() - l);

        List list = new ArrayList();
        l = System.currentTimeMillis();
        for(int i=0;i<10000000;i++){
            list.add(i);
        }
        System.out.println(System.currentTimeMillis() - l);
    }

    /**
     * 中插法：
     * LinkedList 耗时大于 ArrayList;
     */
    @Test
    public void test_addCenter() {
        LinkedList linkedList01 = new LinkedList();
        long  startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            linkedList01.add(linkedList01.size() >> 1, i);
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime));


        ArrayList list = new ArrayList();
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            list.add(list.size() >> 1, i);
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
    }
}
