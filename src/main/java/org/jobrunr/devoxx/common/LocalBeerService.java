package org.jobrunr.devoxx.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A BeerService that will be started when NOT running on RaspberryPi in the {@link org.jobrunr.devoxx.DevoxxDemoRPiConfiguration}.
 */
public class LocalBeerService implements BeerService {

    private final Logger LOGGER = LoggerFactory.getLogger(LocalBeerService.class);

    public LocalBeerService() {
        LOGGER.info("The LocalBeerService got initialized");
    }

    @Override
    public void brewBeer(Beer beer, String isSomethingGoingWrong) throws Exception {
        LOGGER.info("Getting all the a ingredients to brew " + beer.getLabel());
        Thread.sleep(15_000);

        LOGGER.info("Starting the magic chemistry process for " + beer.getLabel());
        Thread.sleep(15_000);
        LOGGER.info("Fermenting everything so we have a nice percentage of alcohol");
        Thread.sleep(15_000);
        LOGGER.info("Bottling our " + beer.getLabel());
        Thread.sleep(15_000);
        LOGGER.info("Celebrating our new " + beer.getLabel());
    }

    @Override
    public void checkIfBarrelIsEmpty(Beer beer) {
        if (!beer.isOnTap()) {
            throw new UnsupportedBeerException("The beer " + beer.getLabel() + " is unsupported - we don't have it on tap!");
        }
        System.out.println("Checking whether we have enough " + beer.getLabel() + ".");
    }

    @Override
    public void drinkBeer(Beer beer) {
        System.out.println("Relaxing and drinking some nice " + beer.getLabel() + " locally");
    }
}
