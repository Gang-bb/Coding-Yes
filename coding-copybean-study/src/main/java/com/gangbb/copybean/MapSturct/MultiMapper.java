package com.gangbb.copybean.MapSturct;

import com.gangbb.copybean.pojo.multi.Address;
import com.gangbb.copybean.pojo.multi.DeliveryAddress;
import com.gangbb.copybean.pojo.multi.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * @Author Gangbb
 * @Description 多对象转单对象mapper
 * @Date 2021/8/9
 **/
@Mapper
public interface MultiMapper {
    MultiMapper INSTANCE = Mappers.getMapper(MultiMapper.class);

    @Mapping(source = "person.description", target = "description")
    @Mapping(source = "address.houseNo", target = "houseNumber")
    DeliveryAddress personAndAddressToDeliveryAddressDto(Person person, Address address);

    /**
     * Person->DeliveryAddress, 缺失地址信息
     * @param person
     * @return
     */
    DeliveryAddress person2deliveryAddress(Person person);

    /**
     * 更新， 使用 Address 来补全 DeliveryAddress 信息。 注意注解 @MappingTarget
     * @param address
     * @param deliveryAddress
     */
    @Mapping(target = "description", ignore = true)
    @Mapping(source = "address.houseNo", target = "houseNumber")
    void updateDeliveryAddressFromAddress(Address address,
                                          @MappingTarget DeliveryAddress deliveryAddress);
}
