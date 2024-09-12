package org.jobrunr.devoxx.common;

import org.jobrunr.jobs.lambdas.JobRequest;
import org.jobrunr.jobs.lambdas.JobRequestHandler;
import org.springframework.stereotype.Component;

@Component
public class DrinkBeerRequestHandler implements JobRequestHandler<DrinkBeerRequestHandler.DrinkBeerRequest> {

    @Override
    public void run(DrinkBeerRequest drinkBeerRequest) throws Exception {
        System.out.println("Relaxing and drinking a " + drinkBeerRequest.beerType);
    }

    public record DrinkBeerRequest(String beerType) implements JobRequest {

        @Override
        public Class<DrinkBeerRequestHandler> getJobRequestHandler() {
            return DrinkBeerRequestHandler.class;
        }
    }
}