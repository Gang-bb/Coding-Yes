package object.three;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

/**
 * TODO:deprecated
 *
 * @author lyx
 * @date 2022/6/8
 **/
public class ReflectUtil {
    /**
     *  比较两个对象属性值是否相同
     *  如果不同返回修改过的属性信息
     * @param oldObj
     * @param newObj
     * @param ignoreProperties
     * @param <T>
     * @return 修改过的属性字段
     */
    public static <T> List<ModifiedPropertyInfo> getDifferentProperty(T oldObj , T newObj , String... ignoreProperties){
        if (oldObj != null && newObj != null) {
            // 比较全部字段
            if (ignoreProperties == null || ignoreProperties.length > 0) {
                if (oldObj.equals(newObj)) {
                    return Collections.emptyList();
                }
            }
            List<PropertyModelInfo> oldObjectPropertyValue = getObjectPropertyValue(oldObj, ignoreProperties);
            if (!CollectionUtil.isEmpty(oldObjectPropertyValue)) {
                List<ModifiedPropertyInfo> modifiedPropertyInfos = new ArrayList<>(oldObjectPropertyValue.size());

                List<PropertyModelInfo> newObjectPropertyValue = getObjectPropertyValue(newObj, ignoreProperties);
                Map<String , Object> objectMap = new HashMap<>(newObjectPropertyValue.size());
                // 获取新对象所有属性值
                for (PropertyModelInfo propertyModelInfo : newObjectPropertyValue) {
                    String propertyName = propertyModelInfo.getPropertyName();
                    Object value = propertyModelInfo.getValue();
                    objectMap.put(propertyName , value);
                }

                for (PropertyModelInfo propertyModelInfo : oldObjectPropertyValue) {
                    String propertyName = propertyModelInfo.getPropertyName();
                    Object value = propertyModelInfo.getValue();
                    if (objectMap.containsKey(propertyName)) {
                        Object newValue = objectMap.get(propertyName);
                        ModifiedPropertyInfo modifiedPropertyInfo = new ModifiedPropertyInfo();
                        if (value != null && newValue != null) {
                            // 是否相等
                            boolean equal = false;
                            if (value instanceof BigDecimal) {
                                // BigDecimal 用 equal  比较
                                if (((BigDecimal) value).compareTo((BigDecimal) newValue) != 0) {
                                    equal = true;
                                }
                            }else if (!value.equals(newValue)) {
                                equal = true;
                            }
                            if (equal) {
                                modifiedPropertyInfo.setProperty(propertyName);
                                modifiedPropertyInfo.setOldValue(value);
                                modifiedPropertyInfo.setNewValue(newValue);
                                modifiedPropertyInfos.add(modifiedPropertyInfo);
                            }
                        }else if (value != null || newValue != null){
                            modifiedPropertyInfo.setProperty(propertyName);
                            modifiedPropertyInfo.setOldValue(value);
                            modifiedPropertyInfo.setNewValue(newValue);
                            modifiedPropertyInfos.add(modifiedPropertyInfo);
                        }
                    }
                }
                return modifiedPropertyInfos;
            }
        }
        return Collections.emptyList();
    }


    /**
     *  通过反射获取对象的属性名称、getter返回值类型、属性值等信息
     * @param obj
     * @param ignoreProperties
     * @param <T>
     * @return
     */
    public static  <T> List<PropertyModelInfo> getObjectPropertyValue(T obj , String... ignoreProperties){
        if (obj != null) {

            Class<?> objClass = obj.getClass();
            PropertyDescriptor[] propertyDescriptors = BeanUtil.getPropertyDescriptors(objClass);
            List<PropertyModelInfo> modelInfos = new ArrayList<>(propertyDescriptors.length);

            List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                Method readMethod = propertyDescriptor.getReadMethod();
                String name = propertyDescriptor.getName();
                if (readMethod != null && (ignoreList == null || !ignoreList.contains(name))) {
                    Object invoke = null;
                    Class<?> returnType = readMethod.getReturnType();
                    try {
                        invoke = readMethod.invoke(obj);
                        PropertyModelInfo propertyModelInfo = new PropertyModelInfo();
                        propertyModelInfo.setPropertyName(name);
                        propertyModelInfo.setValue(invoke);
                        propertyModelInfo.setReturnType(returnType);
                        modelInfos.add(propertyModelInfo);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        //log.error("反射获取类【"+objClass.getName()+"】方法异常，" , e);
                    }
                }
            }
            return modelInfos;
        }
        return Collections.emptyList();
    }

    public static void main(String[] args) {
        HouseInfo houseInfo = new HouseInfo();
        houseInfo.setAddress("test1");

        HouseInfo houseInfo2 = new HouseInfo();
        houseInfo2.setAddress("test2");

        //改变值
        List<ModifiedPropertyInfo> modifiedPropertyInfos = ReflectUtil.getDifferentProperty(houseInfo, houseInfo2);
        System.out.println(modifiedPropertyInfos);
        // 不改变值
        houseInfo2.setAddress("test1");
        modifiedPropertyInfos = ReflectUtil.getDifferentProperty(houseInfo, houseInfo2);
        System.out.println(modifiedPropertyInfos);
    }

    @Data
    static
    class HouseInfo {
        private String address;
    }
}
