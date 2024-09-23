package org.jobrunr.devoxx.common;

import org.jobrunr.jobs.lambdas.JobRequest;
import org.jobrunr.jobs.lambdas.JobRequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DrinkBeerRequestHandler implements JobRequestHandler<DrinkBeerRequestHandler.DrinkBeerRequest> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DrinkBeerRequestHandler.class);

    private final BeerService beerService;

    public DrinkBeerRequestHandler(BeerService beerService) {
        this.beerService = beerService;
    }

    @Override
    public void run(DrinkBeerRequest drinkBeerRequest) throws Exception {
        LOGGER.info("Request handler run for {}", drinkBeerRequest.beer());
        beerService.drinkBeer(drinkBeerRequest.beer());
    }

    public record DrinkBeerRequest(Beer beer) implements JobRequest {

        @Override
        public Class<DrinkBeerRequestHandler> getJobRequestHandler() {
            return DrinkBeerRequestHandler.class;
        }
    }
}