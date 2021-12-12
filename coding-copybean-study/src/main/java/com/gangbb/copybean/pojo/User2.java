package com.gangbb.copybean.pojo;

import com.gangbb.copybean.enums.TypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author Gangbb
 * @Description TODO
 * @Date 2021/8/9
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User2 implements Serializable {

    private static final long serialVersionUID = -1108107267346753671L;
    private Integer id;
    private String loginName;
    private String password;
    private Date birthday;
    private TypeEnum typeEnum;
    private BigDecimal rate;
    private String rate2;
    private String rate3;

    @Mapper
    public interface UserMapper {

        @Mappings({
                @Mapping(source = "loginName", target = "name"),
                @Mapping(source = "birthday", target = "birthday", dateFormat = "yyyy-MM-dd HH:mm:ss"),
                @Mapping(target = "type", expression = "java(user2.getTypeEnum().getCode())"),
                @Mapping(source = "rate", target = "rate",  numberFormat = "0.00"),
                @Mapping(source = "rate2", target = "rate2",  numberFormat = "0.00"),
                @Mapping(source = "rate3", target = "rate3",  numberFormat = "0.00"),
        })
        UserVO user2userVO(User2 user2);
    }

    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
}