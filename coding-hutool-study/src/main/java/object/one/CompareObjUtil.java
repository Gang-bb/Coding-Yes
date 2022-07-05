package object.one;

/**
 * TODO:deprecated
 *
 * @author lyx
 * @date 2022/6/8
 **/
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CompareObjUtil {

    public static List<Comparison> compareObj(Object beforeObj, Object afterObj) throws Exception{
        List<Comparison> diffs = new ArrayList<>();

        if(beforeObj == null) {
            throw new RuntimeException("原对象不能为空");
        }
        if(afterObj == null) {
            throw new RuntimeException("新对象不能为空");
        }
        if(!beforeObj.getClass().isAssignableFrom(afterObj.getClass())){
            throw new RuntimeException("两个对象不相同，无法比较");
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
                if((beforeValue != null && !"".equals(beforeValue) && !beforeValue.equals(afterValue)) || ((beforeValue == null || "".equals(beforeValue)) && afterValue != null)){
                    Comparison comparison = new Comparison();
                    comparison.setField(beforeFields[i].getName());
                    comparison.setBefore(beforeValue);
                    comparison.setAfter(afterValue);
                    comparison.setIsUpdate(true);
                    diffs.add(comparison);
                }
            }
        }

        return diffs;
    }

    public static void main(String[] args) throws Exception {
        ApIData apIData = new ApIData()
                .setName("张三")
                .setMonth("5")
                .setHh("1");
        ApIData apIData1 = new ApIData()
                .setName("张三")
                .setMonth("9")
                .setHh("35");
        List<Comparison> list = CompareObjUtil.compareObj(apIData, apIData1);
        System.out.println(list);
    }
}
