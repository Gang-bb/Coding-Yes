package object.two;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import object.two.Comparision;

import java.lang.reflect.Field;
import java.util.*;

/**
 * ClassName: 对象比较类
 * @Description: 对比两个Bean对象或者两个同类型bean对象的list
 * @author dy
 * @date 2021-06-16
 */
public class CompareBeanUtil {
    public static List<Comparision> comparisionObjList(String compareBase, List<Object> beforeObjs, List<Object> afterObjs) throws Exception {
        if(beforeObjs == null && afterObjs==null) {throw new Exception("两个对比对象链表不能同时为空");}
        List<Comparision> differents = new ArrayList<Comparision>();

        Map<String, Object> beforeMap = new HashMap<>();
        Map<String, Object> afterMap = new HashMap<>();
        Set<String> sameSet = new HashSet<>();
        Set<String> beforeOnlySet = new HashSet<>();
        Set<String> afterOnlySet = new HashSet<>();
        for (Object a: beforeObjs) {
            String fiName = getFieldValueByFieldName(compareBase, a);
            beforeMap.put(fiName,a);
            beforeOnlySet.add(fiName);
        }
        for (Object b: afterObjs) {
            String fiName = getFieldValueByFieldName(compareBase, b);
            afterMap.put(fiName,b);
            afterOnlySet.add(fiName);
        }
        // beforeOnlySet和afterOnlySet交集
        sameSet.addAll(beforeOnlySet);
        sameSet.retainAll(afterOnlySet);

        // beforeOnlySet-sameSet的差集
        beforeOnlySet.removeAll(sameSet);
        // afterOnlySet-sameSet的差集
        afterOnlySet.removeAll(sameSet);

        for (String co:sameSet) {
            differents.addAll(compareObj(beforeMap.get(co), afterMap.get(co)));
        }
        for (String co:beforeOnlySet) {
            differents.addAll(compareObj(beforeMap.get(co), beforeMap.get(co).getClass().newInstance()));
        }
        for (String co:afterOnlySet) {
            differents.addAll(compareObj(afterMap.get(co).getClass().newInstance(),afterMap.get(co)));
        }
        return differents;
    }

    /**
     * 根据属性名获取属性值
     *
     * @param fieldName
     * @param object
     * @return
     */
    public static String getFieldValueByFieldName(String fieldName, Object object) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            //设置对象的访问权限，保证对private的属性的访问
            field.setAccessible(true);
            return  (String)(field.get(object)+"");//+“” 为了兼容int 瑕疵是null会转换为0
        } catch (Exception e) {
            return null;
        }
    }

    public static List<Comparision> compareObj(Object beforeObj,Object afterObj) throws Exception{
        List<Comparision> differents = new ArrayList<Comparision>();

        if(beforeObj == null) {
            throw new Exception("原对象不能为空");
        }
        if(afterObj == null){
            throw new Exception("新对象不能为空");
        }
        if(!beforeObj.getClass().isAssignableFrom(afterObj.getClass())){
            throw new Exception("两个对象不相同，无法比较");
        }

        //取出属性
        Field[] beforeFields = beforeObj.getClass().getDeclaredFields();
        Field[] afterFields = afterObj.getClass().getDeclaredFields();
        Field.setAccessible(beforeFields, true);
        Field.setAccessible(afterFields, true);

        //遍历取出差异值
        if(beforeFields != null && beforeFields.length > 0){
            for(int i=0; i<beforeFields.length; i++){
                Object beforeValue = beforeFields[i].get(beforeObj);
                Object afterValue = afterFields[i].get(afterObj);
                if((beforeValue != null && !"".equals(beforeValue) && !beforeValue.equals(afterValue)) ||
                        ((beforeValue == null || "".equals(beforeValue)) && afterValue != null)){
                    Comparision comparison = new Comparision();
                    comparison.setField(beforeFields[i].getName());
                    comparison.setBefore(beforeValue);
                    comparison.setAfter(afterValue);
                    differents.add(comparison);
                }
            }
        }
        return differents;
    }
    public static void main(String[] args){
        Person p1 = new Person("1","a");
        Person p2 = new Person("1","b");
        Person p3 = new Person("2","a");
//        Person p1 = new Person(1,"a");
//        Person p2 = new Person(1,"b");
//        Person p3 = new Person(2,"a");
        List<Object> l1 = new ArrayList<>();
        List<Object> l2 = new ArrayList<>();
        l1.add(p1);
        l1.add(p3);
        l2.add(p2);
        try{
            //[{"after":"b","before":"a","field":"name"},{"before":"2","field":"id"},{"before":"a","field":"name"}]
            System.out.println(comparisionObjList("id",l1,l2));
        }catch (Exception e)
        {
            System.out.println(e);
        }

    }
}

class Person{
    String id;
    //int id;
    String name;
    public Person(String id, String name) {
        //public Person(int id, String name) {
        //super();

        this.id = id;
        this.name = name;
    }
    public Person(){};
}