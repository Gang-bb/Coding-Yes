package com.gangbb.usercenter.rocketmq;


import com.gangbb.usercenter.dao.bonus.BonusEventLogMapper;
import com.gangbb.usercenter.dao.user.UserMapper;
import com.gangbb.usercenter.domain.dto.UserAddBonusMsgDTO;
import com.gangbb.usercenter.domain.entity.bonus.BonusEventLog;
import com.gangbb.usercenter.domain.entity.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@RocketMQMessageListener(consumerGroup = "user-group", topic = "add-bonus")
public class AddBonusStreamConsumer implements RocketMQListener<UserAddBonusMsgDTO> {
    private final UserMapper userMapper;
    private final BonusEventLogMapper bonusEventLog;


    /**
     *  当收到消息的时候执行的业务
     */
    @Override
    public void onMessage(UserAddBonusMsgDTO userAddBonusMsgDto) {
        //1、为用户加积分
        User user = userMapper.selectByPrimaryKey(userAddBonusMsgDto.getUserId());
        user.setBonus(user.getBonus() + userAddBonusMsgDto.getBonus());
        userMapper.updateByPrimaryKeySelective(user);
        //2、记录日志到bonus_event_log表里
        bonusEventLog.insert(BonusEventLog.builder()
                .userId(userAddBonusMsgDto.getUserId())
                .value(userAddBonusMsgDto.getBonus())
                .createTime(new Date())
                .event("contribute")
                .description("投稿加积分")
                .build());

    }
}
