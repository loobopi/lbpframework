package org.lbpframework;

import java.util.List;

public enum DataType {

    String(String.class),
    Integer(Integer.class),
    List(List.class)
    ;

    DataType(Class type) {
        this.type = type;
    }

    private Class type;

    public static boolean isBaseType(Class clazz){
        for (DataType dataType : DataType.values()) {
            if(dataType.type == clazz){
                return true;
            }
        }
        return false;
    }


}
