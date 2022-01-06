package ribbonconfiguration;

import com.gangbb.contentcenter.configuration.NacosSameClusterWeightedRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Gangbb
 * @date 2021/4/22 9:45
 * @Description:
 */
@Configuration
public class RibbonConfiguration {

    @Bean
    public IRule ribbonRule(){
        return new NacosSameClusterWeightedRule();
    }
}
