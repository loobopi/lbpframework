package org.lbpframework.web.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

public class TestClass {

    @Test
    public void test1(){
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("123");
        strings.add("456");
        Iterator<String> iterator = strings.iterator();
        while(iterator.hasNext()){
            String next = iterator.next();
            System.out.println(next);
        }
        strings.add("789");

        iterator = strings.iterator();
        while(iterator.hasNext()){
            String next = iterator.next();
            System.out.println(next);
        }

    }
}
