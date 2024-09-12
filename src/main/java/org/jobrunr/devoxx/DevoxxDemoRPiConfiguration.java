package org.jobrunr.devoxx;

import org.jobrunr.devoxx.common.BeerService;
import org.jobrunr.devoxx.common.LocalBeerService;
import org.jobrunr.devoxx.common.RaspberryPiBeerService;
import org.jobrunr.devoxx.common.conditional.IsNotRunningOnRPi;
import org.jobrunr.devoxx.common.conditional.IsRunningOnRPi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DevoxxDemoRPiConfiguration {

    @Bean
    @Conditional(IsRunningOnRPi.class)
    public BeerService beerServiceOnRPi() {
        return new RaspberryPiBeerService();
    }

    @Bean
    @Conditional(IsNotRunningOnRPi.class)
    public BeerService beerServiceNotOnRPi() {
        return new LocalBeerService();
    }
}
