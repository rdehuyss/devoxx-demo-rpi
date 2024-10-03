package org.jobrunr.devoxx.common.tap;

import org.jobrunr.devoxx.common.Beer;
import org.jobrunr.devoxx.common.UnsupportedBeerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A LocalBeerTap that will be used when NOT running on RaspberryPi (see {@link org.jobrunr.devoxx.DevoxxDemoRPiConfiguration}).
 */
public class LocalBeerTap implements BeerTap {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalBeerTap.class);

    public LocalBeerTap() {
        LOGGER.info("Starting LocalBeerTap");
    }

    @Override
    public void brewBeer(Beer beer, String isSomethingGoingWrong) throws Exception {
        LOGGER.info("Getting all the a ingredients to brew {}", beer.getLabel());
        Thread.sleep(15_000);

        LOGGER.info("Starting the magic chemistry process for {}", beer.getLabel());
        Thread.sleep(15_000);
        LOGGER.info("Fermenting everything so we have a nice percentage of alcohol");
        Thread.sleep(15_000);
        LOGGER.info("Bottling our {}", beer.getLabel());
        Thread.sleep(15_000);
        LOGGER.info("Celebrating our new {}", beer.getLabel());
    }

    @Override
    public void checkIfBarrelIsEmpty(Beer beer) {
        if (!beer.isOnTap()) {
            throw new UnsupportedBeerException("The beer " + beer.getLabel() + " is unsupported - we don't have it on tap!");
        }
        LOGGER.info("Checking whether we have enough {}", beer.getLabel());
    }

    @Override
    public void drinkBeer(Beer beer) {
        LOGGER.info("Relaxing and drinking some nice {} locally", beer.getLabel());
    }

    @Override
    public void setLedState(Beer beer, boolean state) {
        LOGGER.warn("LED state can't be set with the local beer tap");
    }

    @Override
    public void toggleLedState(Beer beer) {
        LOGGER.warn("LED state can't be toggled with the local beer tap");
    }

    @Override
    public void setLcdText(String line1, String line2) {
        LOGGER.warn("LCD text can't be set with the local beer tap");
    }
}
