package org.jobrunr.devoxx.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A BeerService that will be started when running on RaspberryPi in the {@link org.jobrunr.devoxx.DevoxxDemoRPiConfiguration}.
 */
public class RaspberryPiBeerService implements BeerService {

    private final Logger LOGGER = LoggerFactory.getLogger(LocalBeerService.class);

    @Override
    public void checkIfBarrelIsEmpty(String beerName) {
        if(!isBeerOnTap(beerName)) {
            throw new UnsupportedBeerException("The beer " + beerName + " is unsupported - we don't have it on tap!");
        }
        // TODO: show on LCD?
        System.out.println("Checking whether we have enough " + beerName + ".");
    }

    @Override
    public void drinkBeer(String beerName) {
        // TODO: show on LCD?
        System.out.println("Relaxing and drinking some nice " + beerName + " locally");
    }

    @Override
    public void brewBeer(String beerName, String isSomethingGoingWrong) throws Exception {
        // TODO: show on LCD? Should we lock once we're brewing somehow as we're working multithreaded with a single LCD?
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
