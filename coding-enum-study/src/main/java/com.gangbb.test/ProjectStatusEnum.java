package com.gangbb.test;

/**
 * 项目状态 枚举
 *
 * @author Gangbb
 * @date 2022/2/2
 **/
public enum ProjectStatusEnum {

    ACADEMY_VERIFY(15, "学院审核中(提交申请)"),
    STD_VERIFY(20, "科技处审核中"),
    DED_VERIFY(25, "区级教育厅审核中"),
    VERIFY_FAIL(30, "审核失败"),
    VERIFY_SUCCESS(35, "审核成功"),

    ;
    private final int code;
    private final String name;

    ProjectStatusEnum(int code, String info) {
        this.code = code;
        this.name = info;
    }

    public static Integer nextStatus(int currentCode, int passFlag) {
        if(0 == passFlag){
            return VERIFY_FAIL.code;
        }
        ProjectStatusEnum[] enums = values();
        for (ProjectStatusEnum item : enums) {
            if (item.code > currentCode) {
                return item.getCode();
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
