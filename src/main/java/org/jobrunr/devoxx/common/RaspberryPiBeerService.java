package org.jobrunr.devoxx.common;

import com.pi4j.Pi4J;
import com.pi4j.boardinfo.definition.BoardModel;
import com.pi4j.boardinfo.util.BoardInfoHelper;
import com.pi4j.context.Context;
import com.pi4j.plugin.mock.platform.MockPlatform;
import com.pi4j.plugin.mock.provider.i2c.MockI2CProvider;
import org.jobrunr.devoxx.common.lcd.LcdDisplay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A BeerService that will be started when running on RaspberryPi in the {@link org.jobrunr.devoxx.DevoxxDemoRPiConfiguration}.
 */
public class RaspberryPiBeerService implements BeerService {

    private final Logger LOGGER = LoggerFactory.getLogger(LocalBeerService.class);
    private Context pi4j;
    private LcdDisplay lcd = null;

    public RaspberryPiBeerService() {
        try {
            if (BoardInfoHelper.current().getBoardModel() == BoardModel.UNKNOWN) {
                LOGGER.warn("The Pi4J library could not detect that this system is a Raspberry Pi board.");
                LOGGER.warn("For this reason, Mock implementations will be loaded for all I/O.");
                LOGGER.warn("This means, you can test most functionality of the Pi4J library, but it will not try to interact with I/Os.");
                this.pi4j = Pi4J.newContextBuilder()
                        .add(new MockPlatform())
                        .add(MockI2CProvider.newInstance())
                        .build();
            } else {
                this.pi4j = Pi4J.newAutoContext();
            }
        } catch (Error e) {
            LOGGER.error("Pi4J library failed to load: {}", e.getMessage());
        }

        try {
            // LCD example is based on https://www.pi4j.com/examples/components/lcddisplay/
            // Make sure to enable I2C with `sudo raspi-config` > `Interface Options`
            lcd = new LcdDisplay(pi4j, 2, 16);
        } catch (Exception e) {
            LOGGER.error("Error while initializing the LCD: {}", e.getMessage());
        }
    }

    @Override
    public void checkIfBarrelIsEmpty(String beerName) {
        if(!isBeerOnTap(beerName)) {
            throw new UnsupportedBeerException("The beer " + beerName + " is unsupported - we don't have it on tap!");
        }
        LOGGER.info("Checking whether we have enough {}", beerName);
        setLcdText(0, "Checking barrel");
        setLcdText(1, "   " + beerName);
    }

    @Override
    public void drinkBeer(String beerName) {
        LOGGER.info("Relaxing and drinking some nice {} locally", beerName);
        setLcdText(0, "Drinking");
        setLcdText(1, "   " + beerName);
    }

    @Override
    public void brewBeer(String beerName, String isSomethingGoingWrong) throws Exception {
        LOGGER.info("Getting all the a ingredients to brew {}", beerName);
        setLcdText(0, "Ingredients for");
        setLcdText(1, "   " + beerName);
        Thread.sleep(15_000);

        LOGGER.info("Starting the magic chemistry process for {}", beerName);
        setLcdText(0, "Starting brewing");
        setLcdText(1, "   " + beerName);
        Thread.sleep(15_000);

        LOGGER.info("Fermenting everything so we have a nice percentage of alcohol");
        setLcdText(0, "Fermenting");
        setLcdText(1, "   " + beerName);
        Thread.sleep(15_000);

        LOGGER.info("Bottling our {}", beerName);
        setLcdText(0, "Bottling");
        setLcdText(1, "   " + beerName);
        Thread.sleep(15_000);

        LOGGER.info("Celebrating our new {}", beerName);
        setLcdText(0, "Ready to drink");
        setLcdText(1, "   " + beerName);
    }

    private boolean setLcdText(Integer line, String text) {
        if (lcd == null) {
            LOGGER.error("LCD is not initialized");
            return false;
        }

        try {
            lcd.displayLineOfText(text, line);
            LOGGER.info("LCD text on line {} is set to {}", line, text);
            return true;
        } catch (Exception e) {
            LOGGER.error("Error while changing the LCD text: {}", e.getMessage());
        }
        return false;
    }
}
