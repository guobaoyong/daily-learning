package qqzsh.top.contentcenter.rocketmq;

import com.alibaba.fastjson.JSON;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import qqzsh.top.contentcenter.dao.messaging.RocketmqTransactionLogMapper;
import qqzsh.top.contentcenter.domain.dto.content.ShareAuditDTO;
import qqzsh.top.contentcenter.domain.entity.content.Share;
import qqzsh.top.contentcenter.domain.entity.messaging.RocketmqTransactionLog;
import qqzsh.top.contentcenter.service.content.ShareService;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-11-10 11:35
 * @Description
 */
@RocketMQTransactionListener(txProducerGroup = "tx-add-bonus-group")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddBonusTransactionListener implements RocketMQLocalTransactionListener {

    private final ShareService shareService;
    private final RocketmqTransactionLogMapper rocketmqTransactionLogMapper;

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        MessageHeaders headers = msg.getHeaders();

        String transactionId = (String) headers.get(RocketMQHeaders.TRANSACTION_ID);
        Integer shareId = Integer.valueOf((String) headers.get("share_id"));

        /*try {
            this.shareService.auditById(shareId, (ShareAuditDTO) arg);
            return RocketMQLocalTransactionState.COMMIT;
        }catch (Exception e) {
            return RocketMQLocalTransactionState.ROLLBACK;
        }*/

        String dtoString = (String) headers.get("dto");
        ShareAuditDTO auditDTO = JSON.parseObject(dtoString, ShareAuditDTO.class);

        try {
            this.shareService.auditByIdWithRocketMqLog(shareId, auditDTO, transactionId);
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

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

