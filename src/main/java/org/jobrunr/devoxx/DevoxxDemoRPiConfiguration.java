package org.jobrunr.devoxx;

import com.pi4j.Pi4J;
import com.pi4j.boardinfo.definition.BoardModel;
import com.pi4j.boardinfo.util.BoardInfoHelper;
import org.jobrunr.devoxx.common.BeerService;
import org.jobrunr.devoxx.common.tap.LocalBeerTap;
import org.jobrunr.devoxx.common.tap.RaspberryPiBeerTap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DevoxxDemoRPiConfiguration {
    private final Logger LOGGER = LoggerFactory.getLogger(DevoxxDemoRPiConfiguration.class);

    @Bean
    public BeerService beerService() {
        var boardModel = BoardInfoHelper.current().getBoardModel();
        if(boardModel != BoardModel.UNKNOWN) {
            LOGGER.info("Detected board: {}", boardModel);
            return new BeerService(new RaspberryPiBeerTap(Pi4J.newAutoContext()));
        }
        return new BeerService(new LocalBeerTap());
    }
}
