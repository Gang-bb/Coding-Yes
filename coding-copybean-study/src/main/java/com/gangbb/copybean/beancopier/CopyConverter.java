package com.gangbb.copybean.beancopier;

import net.sf.cglib.core.Converter;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author Gangbb
 * @Description TODO
 * @Date 2021/8/7
 **/
public class CopyConverter implements Converter, org.springframework.cglib.core.Converter {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Object convert(Object value, Class target, Object context) {

        String s = value.toString();
        if (target.equals(int.class) || target.equals(Integer.class)) {
            return Integer.parseInt(s);
        }
        if (target.equals(long.class) || target.equals(Long.class)) {
            return Long.parseLong(s);
        }
        if (target.equals(float.class) || target.equals(Float.class)) {
            return Float.parseFloat(s);
        }
        if (target.equals(double.class) || target.equals(Double.class)) {
            return Double.parseDouble(s);
        }
        if (target.equals(Date.class)) {
            while (s.indexOf("-") > 0) {
                s = s.replace("-", "/");
            }
            return new Date(s);
        }
        if (target.equals(BigDecimal.class)) {
            if (!StringUtils.isEmpty(s) && !s.equals("NaN")) {
                return new BigDecimal(s);
            }
        }
        return value;
    }
}