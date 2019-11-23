package com.yybt.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;


/**
 * @author liuzehong
 *    反射工具
 */
public class ReflectHelper {

	/**
	 * 获取obj对象fieldName的Field
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static Field getFieldByFieldName(Object obj, String fieldName) {
		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
			}
		}
		return null;
	}

	/**
	 * 获取obj对象fieldName的属性值
	 * 
	 * @param obj
	 * @param fieldName
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Object getValueByFieldName(Object obj, String fieldName)
			throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		Field field = getFieldByFieldName(obj, fieldName);
		Object value = null;
		if (field != null) {
			if (field.isAccessible()) {
				value = field.get(obj);
			} else {
				field.setAccessible(true);
				value = field.get(obj);
				field.setAccessible(false);
			}
		}
		return value;
	}

	/**
	 * 设置obj对象fieldName的属性值
	 * 
	 * @param obj
	 * @param fieldName
	 * @param value
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static void setValueByFieldName(Object obj, String fieldName, Object value)
			throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		try {
			Field field = obj.getClass().getDeclaredField(fieldName);
			if (field.isAccessible()) {
				field.set(obj, value);
			} else {
				field.setAccessible(true);
				field.set(obj, value);
				field.setAccessible(false);
			}
		} catch (Exception e) {
			//这里可以获取到父类的公共属性，需要特别注意一下
			Field field = obj.getClass().getField(fieldName);
			if (field.isAccessible()) {
				field.set(obj, value);
			} else {
				field.setAccessible(true);
				field.set(obj, value);
				field.setAccessible(false);
			}
		}

	}

	/**
	 * 设置obj对象fieldName的属性值
	 * 
	 * @param obj
	 * @param fieldName
	 * @param value
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static void setSyperValueByFieldName(Object obj, String fieldName, Object value)
			throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		// obj.getClass().getField(name)
		Field field = obj.getClass().getDeclaredField(fieldName);
		if (field.isAccessible()) {
			field.set(obj, value);
		} else {
			field.setAccessible(true);
			field.set(obj, value);
			field.setAccessible(false);
		}
	}
	
    
    /**
     *  实例化一个class
     * @param clazz
     * @return
     */
    public static <T> T instanceClass(Class<T> clazz){
        if(!clazz.isInterface()){
            try {
                return clazz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    /**
     * 通过构造函数实例化
     * @param ctor
     * @param args
     * @return
     * @throws IllegalArgumentException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static <T> T instanceClass(Constructor<T> ctor, Object... args){
        makeAccessible(ctor);
        try {
			return ctor.newInstance(args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//调用构造方法实例化
        return null;
    }
    /**
     * 查找某个class的方法
     * @param clazz
     * @param methodName
     * @param paramTypes
     * @return
     */
    public static  Method findMethod(Class<?> clazz, String methodName, Class<?>... paramTypes){
        try {
            return clazz.getMethod(methodName, paramTypes);
        } catch (NoSuchMethodException e) {
            return findDeclaredMethod(clazz, methodName, paramTypes);//返回共有的方法
        }
    }

    public static Method findDeclaredMethod(Class<?> clazz, String methodName, Class<?>[] paramTypes){
        try {
            return clazz.getDeclaredMethod(methodName, paramTypes);
        }
        catch (NoSuchMethodException ex) {
            if (clazz.getSuperclass() != null) {
                return findDeclaredMethod(clazz.getSuperclass(), methodName, paramTypes);
            }
            return null;
        }
    }

    public static Method[] findDeclaredMethods(Class<?> clazz){
        return clazz.getDeclaredMethods();
    }

    public static void makeAccessible(Constructor<?> ctor) {
        if ((!Modifier.isPublic(ctor.getModifiers())
                || !Modifier.isPublic(ctor.getDeclaringClass().getModifiers()))
                && !ctor.isAccessible()) {
            ctor.setAccessible(true);//如果是私有的 设置为true 使其可以访问
        }
    }

    public static Field[] findDeclaredFields(Class<?> clazz){
        return clazz.getDeclaredFields();
    }
    
    /**
     * 测试主函数
     * @param args
     */
    public static void main(String[] args) {
        try {
            Class cls = Class.forName("com.yybt.common.page.PageBean");
            Field field;
            try {
                // 得到一个类的实例
                Object PageBean = cls.newInstance();
                // 调用根据字段名得到字段的方法
                field = ReflectHelper.getFieldByFieldName(PageBean, "pageSize");
                System.out.println(field.getName());
                
                 // 根据字段名给字段赋值
                ReflectHelper.setValueByFieldName(PageBean, "pageSize",12);
                 
                // 根据字段名获取到字段值
                Object pageSize = ReflectHelper.getValueByFieldName(PageBean, "pageSize");
                System.out.println(pageSize);
                
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }  
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
}

