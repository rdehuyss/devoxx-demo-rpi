package org.jobrunr.devoxx.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A BeerService that will be started when NOT running on RaspberryPi in the {@link org.jobrunr.devoxx.DevoxxDemoRPiConfiguration}.
 */
public class LocalBeerService implements BeerService {

    private final Logger LOGGER = LoggerFactory.getLogger(LocalBeerService.class);

    @Override
    public void checkIfBarrelIsEmpty(String beerName) {
        if(!isBeerOnTap(beerName)) {
            throw new UnsupportedBeerException("The beer " + beerName + " is unsupported - we don't have it on tap!");
        }
        System.out.println("Checking whether we have enough " + beerName + ".");
    }

    @Override
    public void drinkBeer(String beerName) {
        System.out.println("Relaxing and drinking some nice " + beerName + " locally");
    }

    @Override
    public void brewBeer(String beerName, String isSomethingGoingWrong) throws Exception {
        LOGGER.info("Getting all the a ingredients to brew " + beerName);
        Thread.sleep(15_000);

        LOGGER.info("Starting the magic chemistry process for " + beerName);
        Thread.sleep(15_000);
        LOGGER.info("Fermenting everything so we have a nice percentage of alcohol");
        Thread.sleep(15_000);
        LOGGER.info("Bottling our " + beerName);
        Thread.sleep(15_000);
        LOGGER.info("Celebrating our new " + beerName);
    }
}
