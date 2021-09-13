package org.lbpframework;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectUtils {

    /**
     * 获取类的某个属性
     * @param clazz
     * @param fieldName
     * @return
     */
    public static Field getField(Class clazz,String fieldName){
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取属性值
     * @param instance
     * @param fieldName
     * @return
     */
    public static Object getFieldValue(Object instance,String fieldName){
        Field field = getField(instance.getClass(), fieldName);
        field.setAccessible(true);
        try {
            return field.get(instance);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 反射执行某个方法
     * @param method
     * @param instance
     * @param objects
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static Object invoke(Method method, Object instance,Object...objects) {
        try {
            return method.invoke(instance,objects);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 获取某个方法
     * @param clazz
     * @param methodName
     * @param parameterClass
     * @return
     */
    public static Method getMethod(Class clazz,String methodName,Class...parameterClass){
        try {
            return clazz.getDeclaredMethod(methodName,parameterClass);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

}
