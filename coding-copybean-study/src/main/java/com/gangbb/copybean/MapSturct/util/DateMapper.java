package com.gangbb.copybean.MapSturct.util;

import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author Gangbb
 * @Description TODO
 * @Date 2021/8/9
 **/

@Component
@Named("DateMapper")
public class DateMapper {

    /**
     * @Description 年月日时分秒 格式
     **/
    private static final String DATETIME = "yyyy-MM-dd HH:mm:ss";

    /**
     * @Description 年月日 格式
     **/
    private static final String DATE = "yyyy-MM-dd";



    /**
     * @Description Date 转成  字符串yyyy-MM-dd HH:mm:ss
     **/
    @Named("dateTime2String")
    public String dateTime2String(Date date) {
        return date != null ? new SimpleDateFormat(DATETIME)
                .format(date) : null;
    }

    /**
     * @Description Date 转成  字符串yyyy-MM-dd
     **/
    @Named("date2String")
    public String date2String(Date date) {
        return date != null ? new SimpleDateFormat(DATE)
                .format(date) : null;
    }

    /**
     * @Description 字符串yyyy-MM-dd HH:mm:ss格式日期 转成 Date
     **/
    @Named("str2DateTime")
    public Date str2DateTime(String date) {
        try {
            return date != null ? new SimpleDateFormat(DATETIME)
                    .parse(date) : null;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description 字符串yyyy-MM-dd格式日期 转成 Date
     **/
    @Named("str2Date")
    public Date str2Date(String date) {
        try {
            return date != null ? new SimpleDateFormat(DATE)
                    .parse(date) : null;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description 时间戳转成Date类型
     **/
    @Named("stamp2Date")
    public Date stamp2Date(Long date) {
        try {
            return new Date(date);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}