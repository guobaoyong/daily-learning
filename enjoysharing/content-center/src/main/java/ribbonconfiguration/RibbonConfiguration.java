package ribbonconfiguration;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import qqzsh.top.contentcenter.configuration.NacosSameClusterWeightedRule;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-11-09 15:44
 * @Description
 */
@Configuration
public class RibbonConfiguration {

    @Bean
    public IRule ribbonRule() {
        return new NacosSameClusterWeightedRule();
    }
}
