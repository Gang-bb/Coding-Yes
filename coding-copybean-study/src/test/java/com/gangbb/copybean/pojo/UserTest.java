package com.gangbb.copybean.pojo;

import cn.hutool.core.date.DateUtil;
import cn.hutool.extra.cglib.CglibUtil;
import com.gangbb.copybean.MapSturct.ConvertMapper;
import com.gangbb.copybean.MapSturct.MultiMapper;
import com.gangbb.copybean.MapSturct.Use3Mapper;
import com.gangbb.copybean.MapSturct.UtilMapper;
import com.gangbb.copybean.beancopier.BeanCopierUtil;
import com.gangbb.copybean.enums.TypeEnum;
import com.gangbb.copybean.pojo.multi.Address;
import com.gangbb.copybean.pojo.multi.DeliveryAddress;
import com.gangbb.copybean.pojo.multi.Person;
import com.gangbb.copybean.pojo.util.TestUtil;
import com.gangbb.copybean.pojo.util.TestUtilVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.beans.BeanCopier;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author Gangbb
 * @Description TODO
 * @Date 2021/8/7
 **/
@SpringBootTest
@Slf4j
public class UserTest {
    // 初始化UserRequest
    UserRequest userRequest =
            new UserRequest(1, "名字", "密码", DateUtil.now(), new OtherInfo("备注"));



//    UserRequest userRequest =
//            new UserRequest(1, "名字", "密码");

    @Test
    public void testSingle(){

        // set and get
        User user1 = new User();
        long startTime1 = System.currentTimeMillis();
        user1.setId(userRequest.getId());
        //.setLoginName(userRequest.getName());
        user1.setPassword(userRequest.getPassword());
        user1.setBirthday(DateUtil.parse(userRequest.getBirthday(), "yyyy-MM-dd"));
        //user1.setOtherInfo(userRequest.getOtherInfo());
        log.info("set and get，耗时(ms):{}", System.currentTimeMillis() - startTime1);
        log.info(user1.toString());

        // spring.BeanUtils
        User user2 = new User();
        long startTime2 = System.currentTimeMillis();
        BeanUtils.copyProperties(userRequest, user2);
        log.info("spring.BeanUtils，耗时(ms):{}", System.currentTimeMillis() - startTime2);
        log.info(user2.toString());

        // dozer
//        User user3 = new User();
//        long startTime3 = System.currentTimeMillis();
//        DozerMapper.get().map(userRequest, user3);
//        log.info(user3.toString());
//        log.info("dozer，耗时(ms):{}", System.currentTimeMillis() - startTime3);

        // cglib.BeanCopier
        User user4 = new User();
        long startTime4 = System.currentTimeMillis();
        BeanCopier copier = BeanCopier.create(UserRequest.class, User.class, false);
        copier.copy(userRequest, user4, null);
//        BeanCopier copier = BeanCopier.create(UserRequest.class, User.class, true);
//        copier.copy(userRequest, user4, new CopyConverter());
        log.info("cglib.BeanCopier，耗时(ms):{}", System.currentTimeMillis() - startTime4);
        log.info(user4.toString());


        // hutool.CglibUtil
        long startTime5 = System.currentTimeMillis();
        User user5 = CglibUtil.copy(userRequest, User.class);
        log.info("hutool.CglibUtil，耗时(ms):{}", System.currentTimeMillis() - startTime5);
        log.info(user5.toString());


        // Orika
//        User user7 = new User();
//        long startTime7 = System.currentTimeMillis();
//        new DefaultMapperFactory.Builder().build().getMapperFacade().map(userRequest,user7);
//        log.info("Orika，耗时(ms):{}", System.currentTimeMillis() - startTime7);
//        log.info(user7.toString());

        // MapStruct
        long startTime8 = System.currentTimeMillis();
        ConvertMapper mapper = Mappers.getMapper(ConvertMapper.class);
        User user8 = mapper.user2userRequest(userRequest);
        log.info("MapStruct，耗时(ms):{}", System.currentTimeMillis() - startTime8);
        log.info(user8.toString());



    }

    /**
     * @Author Gangbb
     * @Description // commons.BeanUtils
     * @Date 2021/8/8
     **/
    @Test
    public void testCommonsBeanUtils() throws InvocationTargetException, IllegalAccessException {
        User user = new User();
        long startTime6 = System.currentTimeMillis();

        ConvertUtils.register(new Converter() {

            public Object convert(Class type, Object value) {

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    return simpleDateFormat.parse(value.toString());
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return null;
            }
        }, Date.class);
        org.apache.commons.beanutils.BeanUtils.copyProperties(user, userRequest);
        log.info("commons.BeanUtils，耗时(ms):{}", System.currentTimeMillis() - startTime6);
        log.info(user.toString());
    }

    /**
     * @Author Gangbb
     * @Description 自己基于cglib.BeanCopier封装的BeanCopierUtil(单次复制)
     * 因为多次会跟的结果相互影响 cglib.BeanCopier
     * @Date 2021/8/7
     **/
    @Test
    public void testMyBeanCopierUtil(){

        User user = new User();
        long startTime = System.currentTimeMillis();
        BeanCopierUtil.copy(userRequest, user);
        log.info("自己基于cglib.BeanCopier封装的BeanCopierUtil，耗时(ms):{}", System.currentTimeMillis() - startTime);
        log.info(user.toString());
    }


    @Test
    public void testMapStruct(){
        User user = new User();
        long startTime = System.currentTimeMillis();
        user = User.INSTANCE.toModel(userRequest);
        log.info("MapStruct，耗时(ms):{}", System.currentTimeMillis() - startTime);
        log.info(user.toString());
    }

    @Test
    public void testMapStruct2(){
        User2 user2 =
                new User2(1, "登录名", "密码", new Date(),
                        TypeEnum.ADMIN, BigDecimal.valueOf(12.9643), "17.9632", "20.9684");
        UserVO userVO = User2.INSTANCE.user2userVO(user2);
        log.info(userVO.toString());
    }

    //

    @Autowired
    private Use3Mapper use3Mapper;

    @Test
    public void testUser3(){
        UserRequest3 userRequest3 = new UserRequest3(1, "xx", "qq");
         User3 user3 = use3Mapper.user2userRequest(userRequest3);
        log.info(user3.toString());
    }

    @Test
    public void multiTest(){
        Address address = new Address("街道", 123456, 1111, "这是一个地址信息");
        Person person = new Person("美丽", "王", 176, "这是一个人员信息");
        DeliveryAddress deliveryAddress = MultiMapper.INSTANCE.personAndAddressToDeliveryAddressDto(person, address);
        log.info(deliveryAddress.toString());
    }

    @Test
    public void multiTest2(){
        Person person = new Person("美丽", "王", 176, "这是一个人员信息");
        DeliveryAddress deliveryAddress = MultiMapper.INSTANCE.person2deliveryAddress(person);
        log.info("获取地址前：" + deliveryAddress.toString());

        Address address = new Address("街道", 123456, 1111, "这是一个地址信息");
        MultiMapper.INSTANCE.updateDeliveryAddressFromAddress(address, deliveryAddress);
        log.info("获取地址后：" + deliveryAddress.toString());
    }


    @Test
    public void utilTest(){
        TestUtil testUtil = new TestUtil();
        testUtil.setAddTime(new Date());

        TestUtilVO testUtilVO = UtilMapper.INSTANCE.toVO(testUtil);
        log.info(testUtilVO.getAddTime());
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }



    @Test
    public void testValidator(){

    }


}

