package qqzsh.top.usercenter.rocketmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;
import qqzsh.top.usercenter.domain.dto.messaging.UserAddBonusMsgDTO;
import qqzsh.top.usercenter.service.user.UserService;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-11-10 15:33
 * @Description
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AddBonusStreamConsumer {

    private final UserService userService;

    @StreamListener(Sink.INPUT)
    public void receive(UserAddBonusMsgDTO message){
        message.setEvent("CONTRIBUTE");
        message.setDescription("投稿加积分..");
        this.userService.addBonus(message);
    }
}
