package org.lbpframework.web.test;

import java.io.File;

public class ListFileTest {

    public static void listFile(String filePath){
        File file = new File(filePath);
        File[] files = file.listFiles();
        for (File f : files) {
            if(f.isDirectory()){
                listFile(f.getPath());
            }
            System.out.println(f);
        }
    }

    public static void main(String[] args) {
        listFile("D:\\work");
    }
}
