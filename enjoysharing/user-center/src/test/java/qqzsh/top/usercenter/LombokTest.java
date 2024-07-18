package qqzsh.top.usercenter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-11-08 20:36
 * @Description lombok测试
 */
@Slf4j
public class LombokTest {
    //日志
    //    public static final Logger LOGGER = LoggerFactory.getLogger(LombokTest.class);
    public static void main(String[] args) {
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setAgreement(true);
        userRegisterDTO.setEmail("xx");
        // ...
        // 建造者模式 （Feign）
        UserRegisterDTO build = UserRegisterDTO.builder()
                .email("xx")
                .password("x")
                .confirmPassword("x")
                .agreement(true)
                .build();

        log.info("构造出来的UserRegisterDTO = {}", build);
    }
}

@Data
@NoArgsConstructor //无参构造方法
@AllArgsConstructor //全参构造方法
//@RequiredArgsConstructor 为final参数生成构造方法
@Builder
class UserRegisterDTO {
    private String email;
    private String password;
    private String confirmPassword;
    private String mobile;
    private boolean agreement;
}