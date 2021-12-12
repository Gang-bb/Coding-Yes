package com.gangbb.copybean.beancopier;

/**
 * @Author Gangbb
 * @Description BeanCopier工具类
 * cglib的BeanCopier功能很强大，不过频繁的create太占用资源，降低服务器性能，所以写了下面的代码进行优化。以达到提升性能的目的。主要就是用缓存将类型相同的copier缓存起来，后续copy的时候就不用再继续创建了。
 * 这样的话如果有频繁用到BeanCopier的地方，将是很有用处滴。
 * @Date 2021/8/7
 **/

import org.springframework.cglib.beans.BeanCopier;

import java.util.concurrent.ConcurrentHashMap;


public class BeanCopierUtil {
    /**
     * BeanCopier的缓存
     */
    static final ConcurrentHashMap<String, BeanCopier> BEAN_COPIER_CACHE = new ConcurrentHashMap<>();

    /**
     * BeanCopier的copy
     * @param source 源文件的
     * @param target 目标文件
     */
    public static void copy(Object source, Object target) {
        String key = genKey(source.getClass(), target.getClass());
        BeanCopier beanCopier;
        if (BEAN_COPIER_CACHE.containsKey(key)) {
            beanCopier = BEAN_COPIER_CACHE.get(key);
        } else {
            beanCopier = BeanCopier.create(source.getClass(), target.getClass(), false);
            BEAN_COPIER_CACHE.put(key, beanCopier);
        }
        beanCopier.copy(source, target, null);
    }

    /**
     * 生成key
     * @param srcClazz 源文件的class
     * @param tgtClazz 目标文件的class
     * @return string
     */
    private static String genKey(Class<?> srcClazz, Class<?> tgtClazz) {
        return srcClazz.getName() + tgtClazz.getName();
    }

}