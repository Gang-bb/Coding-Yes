package com.gangbb.copybean.enums;

import lombok.Getter;

/**
 * @Author Gangbb
 * @Description TODO
 * @Date 2021/8/9
 **/
@Getter
public enum TypeEnum {

    ADMIN(1, "管理员"),  COMMON(2, "普通用户"),  MANAGER(3, "经理");

    TypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    private final int code;

    private final String name;
}
