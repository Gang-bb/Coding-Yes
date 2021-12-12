package com.gangbb.copybean.pojo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author Gangbb
 * @Description TODO
 * @Date 2021/8/8
 **/
@SpringBootTest
@Slf4j
public class UserListTest {

//    // 初始化UserRequest
//    List<UserRequest> list = initData();
//
//    private List<UserRequest> initData(){
//        List<UserRequest> list = new ArrayList<>();
//        for(int i=0;i<100000;i++){
//            UserRequest userRequest =
//                    new UserRequest(i, "名字"+ i, "密码" + i, DateUtil.now(), new OtherInfo("备注"+ i));
//            list.add(userRequest);
//        }
//        return list;
//    }
//
//    @Test
//    public void listTest(){
//        // set and get
//        long startTime1 = System.currentTimeMillis();
//        List<User> users = new ArrayList<>();
//        for (UserRequest userRequest : list) {
//            User user1 = new User();
//            user1.setId(userRequest.getId());
//            user1.setLoginName(userRequest.getName());
//            user1.setPassword(userRequest.getPassword());
//            user1.setBirthday(DateUtil.parse(userRequest.getBirthday(), "yyyy-MM-dd"));
//            user1.setOtherInfo(userRequest.getOtherInfo());
//            users.add(user1);
//        }
//        log.info("set and get，耗时(ms):{}", System.currentTimeMillis() - startTime1);
//        //log.info(users.toString());
//
//        // spring.BeanUtils
//        long startTime2 = System.currentTimeMillis();
//        List<User> users2 = new ArrayList<>();
//        for (UserRequest userRequest : list) {
//            User user1 = new User();
//            BeanUtils.copyProperties(userRequest, user1);
//            users2.add(user1);
//        }
//        log.info("spring.BeanUtils，耗时(ms):{}", System.currentTimeMillis() - startTime2);
//        //log.info(users2.toString());
//
//
//        //cglib.BeanCopier
//        long startTime = System.currentTimeMillis();
//        List<User> users3 = new ArrayList<>();
//        for (UserRequest userRequest : list) {
//            User user1 = new User();
//            BeanCopierUtil.copy(userRequest, user1);
//            users3.add(user1);
//        }
//        log.info("自己基于cglib.BeanCopier封装的BeanCopierUtil，耗时(ms):{}", System.currentTimeMillis() - startTime);
//        //log.info(user.toString());
//
//
//        // MapStruct
//        long startTime4 = System.currentTimeMillis();
//        List<User> users4 = new ArrayList<>();
//        ConvertMapper mapper = Mappers.getMapper(ConvertMapper.class);
//        for (UserRequest userRequest : list) {
//            User user8 = mapper.user2userRequest(userRequest);
//            users4.add(user8);
//        }
//        log.info("MapStruct，耗时(ms):{}", System.currentTimeMillis() - startTime4);
//
//
//
//    }
}