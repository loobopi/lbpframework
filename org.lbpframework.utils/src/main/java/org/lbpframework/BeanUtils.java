package org.lbpframework;

import cn.hutool.json.JSONObject;
import sun.misc.Unsafe;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;

public class BeanUtils {

    private static Unsafe UNSAFE = null;
    private static final String EXT_PROPERTY_NAME = "class";
    private static final String MODIFIERS_NAME = "modifiers";

    static {
        Field field = null;
        try {
            field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            UNSAFE = (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将object 对象转换为Map
     *
     * @param object
     * @return
     */
    public static Map convertBeanToMap(Object object) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Class clazz = object.getClass();
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String propertiesName = propertyDescriptor.getName();
            if (propertiesName.equals("class")) {
                continue;
            }
            Method readMethod = propertyDescriptor.getReadMethod();
            jsonObject.put(propertiesName, readMethod.invoke(object));
        }
        return jsonObject;
    }

    /**
     * 根据源bean的值，和目标对象class 返回实例化目标对象；
     *
     * @param sourceBean
     * @param targetClass
     * @return
     * @throws Exception
     */
    public static Object copyToTargetBean(Object sourceBean, Class<?> targetClass) throws Exception {
        PropertyDescriptor[] sourcePropertyDescriptors = Introspector.getBeanInfo(sourceBean.getClass()).getPropertyDescriptors();
        PropertyDescriptor[] targetPropertyDescriptors = Introspector.getBeanInfo(targetClass).getPropertyDescriptors();

        Object targetBean = create(targetClass);
        for (PropertyDescriptor sourcePropertyDescriptor : sourcePropertyDescriptors) {
            String sourceName = sourcePropertyDescriptor.getName();
            if (!sourceName.equals(EXT_PROPERTY_NAME)) {
                for (PropertyDescriptor targetPropertyDescriptor : targetPropertyDescriptors) {
                    String targetName = targetPropertyDescriptor.getName();
                    if (sourceName.equals(targetName)) {
                        Method sourceReadMethod = sourcePropertyDescriptor.getReadMethod();
                        Method targetWriteMethod = targetPropertyDescriptor.getWriteMethod();
                        targetWriteMethod.invoke(targetBean, sourceReadMethod.invoke(sourceBean));
                    }
                }
            }
        }
        return targetBean;
    }

    /**
     * 根据Field 复制对象信息；
     * @param sourceObject
     * @param targetClass
     * @param externalField
     * @return
     * @throws Exception
     */
    public static Object copy(Object sourceObject,Class targetClass,Object...externalField)throws Exception{
        Class sourceClass = sourceObject.getClass();
        Field[] sourceClassDeclaredFields = sourceClass.getDeclaredFields();
        Constructor declaredConstructor = null;
        try{
            declaredConstructor = targetClass.getDeclaredConstructor();
        }catch (Exception e){};
        Object targetObject = null;
        if(declaredConstructor != null){
            targetObject = declaredConstructor.newInstance();
        }else{
            targetObject = create(targetClass);
        }

        for (Field sourceClassDeclaredField : sourceClassDeclaredFields) {
            sourceClassDeclaredField.setAccessible(true);
            if(sourceClassDeclaredField != null){
                Field targetField = ReflectUtils.getField(targetClass, sourceClassDeclaredField.getName());
                if(targetField != null){
                    /*targetField.setAccessible(true);
                    Field modifiers = sourceClassDeclaredField.getClass().getDeclaredField(MODIFIERS_NAME);
                    modifiers.setAccessible(true);
                    modifiers.setInt(sourceClassDeclaredField,sourceClassDeclaredField.getModifiers() & ~Modifier.FINAL);*/

                    setModifiers(sourceClassDeclaredField);
                    setModifiers(targetField);

                    //判断是否是基本数据类型

                    Object value = sourceClassDeclaredField.get(sourceObject);
                    targetField.set(targetObject, value);
                }
            }
        }
        return targetObject;
    }

    private static void setModifiers(Field field){
        try{
            Field declaredField = field.getClass().getDeclaredField(MODIFIERS_NAME);
            declaredField.setAccessible(true);
            declaredField.setInt(field,Modifier.PUBLIC);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    private static Object create(Class<?> clazz) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        return UNSAFE.allocateInstance(clazz);
    }
}
