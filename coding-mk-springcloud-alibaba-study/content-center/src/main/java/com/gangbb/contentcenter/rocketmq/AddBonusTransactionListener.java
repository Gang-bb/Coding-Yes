package com.gangbb.contentcenter.rocketmq;

import com.gangbb.contentcenter.dao.messaging.RocketmqTransactionLogMapper;
import com.gangbb.contentcenter.domain.dto.ShareAuditDTO;
import com.gangbb.contentcenter.domain.entity.messaging.RocketmqTransactionLog;
import com.gangbb.contentcenter.service.ShareService;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

@RocketMQTransactionListener(txProducerGroup = "tx-add-bonus-group")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddBonusTransactionListener implements RocketMQLocalTransactionListener {
    private final ShareService shareService;
    private final RocketmqTransactionLogMapper rocketmqTransactionLogMapper;

    /**
     * 执行本地事务
     * @param msg
     * @param arg
     * @return
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        MessageHeaders headers = msg.getHeaders();

        String transactionId = (String) headers.get(RocketMQHeaders.TRANSACTION_ID);
        Integer shareId = Integer.valueOf((String) headers.get("share_id"));

        try {
            this.shareService.auditByIdWithRocketMqLog(shareId, (ShareAuditDTO) arg, transactionId);
            // 本地事务成功
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            // 本地事务失败
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    /**
     * 检查本地事务有没有执行成功
     * 比如上面代码 要执行到 return RocketMQLocalTransactionState.COMMIT; 时断网了，所以获取不到事务成功提交的信息 所以需要回查
     * 通过本地执行完一条事务后，记录一条记录到mysql的表中，方便rocketMq回查
     *
     * @param msg
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        MessageHeaders headers = msg.getHeaders();
        String transactionId = (String) headers.get(RocketMQHeaders.TRANSACTION_ID);

        // select * from xxx where transaction_id = xxx
        RocketmqTransactionLog transactionLog = this.rocketmqTransactionLogMapper.selectOne(
            RocketmqTransactionLog.builder()
                .transactionId(transactionId)
                .build()
        );
        if (transactionLog != null) {
            return RocketMQLocalTransactionState.COMMIT;
        }
        return RocketMQLocalTransactionState.ROLLBACK;
    }
}
