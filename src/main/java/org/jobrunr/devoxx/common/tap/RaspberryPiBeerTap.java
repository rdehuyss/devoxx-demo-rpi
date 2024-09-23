package org.jobrunr.devoxx.common.tap;

import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalState;
import org.jobrunr.devoxx.common.Beer;
import org.jobrunr.devoxx.common.UnsupportedBeerException;
import org.jobrunr.devoxx.common.lcd.LcdDisplay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumMap;
import java.util.stream.Stream;

/**
 * A RaspberryPiBeerTap that will be started when running on RaspberryPi (see {@link org.jobrunr.devoxx.DevoxxDemoRPiConfiguration}).
 */
public class RaspberryPiBeerTap implements BeerTap {

    private static final Logger LOGGER = LoggerFactory.getLogger(RaspberryPiBeerTap.class);
    private final EnumMap<Beer, DigitalOutput> outputs = new EnumMap<>(Beer.class);
    private final Context pi4j;
    private LcdDisplay lcd = null;

    public RaspberryPiBeerTap(Context pi4JContext) {
        this.pi4j = pi4JContext;

        try {
             /*
             The LCD example is based on https://www.pi4j.com/examples/components/lcddisplay/
             Make sure to enable I2C with `sudo raspi-config` > `Interface Options`
             Once connected, make sure the I2C device is detected, this code uses address 0x27

             $ i2cdetect -y 1
                     0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f
                00:                         -- -- -- -- -- -- -- --
                10: -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
                20: -- -- -- -- -- -- -- 27 -- -- -- -- -- -- -- --
                30: -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
                40: -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
                50: -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
                60: -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
                70: -- -- -- -- -- -- -- --
             */
            lcd = new LcdDisplay(pi4j, 2, 16);
            LOGGER.info("LCD display initialized on I2C");
        } catch (Exception e) {
            LOGGER.error("Error while initializing the LCD: {}", e.getMessage());
        }

        try {
            // Configure the relay outputs
            Stream.of(Beer.values()).filter(Beer::isOnTap).forEach(this::initLed);
        } catch (Exception e) {
            LOGGER.error("Error while initializing the LED: {}", e.getMessage());
        }

        LOGGER.info("Staring RaspberryPiBeerTap");
    }

    private void initLed(Beer beer) {
        try {
            var beerLedConfig = DigitalOutput.newConfigBuilder(pi4j)
                    .id(beer.name())
                    .name("LED for " + beer.getLabel())
                    .address(beer.getTapNumber())
                    .shutdown(DigitalState.LOW)
                    .initial(DigitalState.LOW);
            var beerLed = pi4j.create(beerLedConfig);
            this.outputs.put(beer, beerLed);
            LOGGER.info("DigitalOutput initialized for {} on tap number {}", beer.getLabel(), beer.getTapNumber());
        } catch (Exception e) {
            LOGGER.error("Can't initialize led {}: {}", beer.getTapNumber(), e.getMessage());
        }
    }

    @Override
    public void brewBeer(Beer beer, String isSomethingGoingWrong) throws Exception {
        setLedState(beer, true);

        LOGGER.info("Getting all the a ingredients to brew {}", beer.getLabel());
        setLcdText(0, "Ingredients for");
        setLcdText(1, "   " + beer.getLabel());
        Thread.sleep(15_000);

        LOGGER.info("Starting the magic chemistry process for {}", beer.getLabel());
        setLcdText(0, "Starting brewing");
        setLcdText(1, "   " + beer.getLabel());
        Thread.sleep(15_000);

        LOGGER.info("Fermenting everything so we have a nice percentage of alcohol");
        setLcdText(0, "Fermenting");
        setLcdText(1, "   " + beer.getLabel());
        Thread.sleep(15_000);

        LOGGER.info("Bottling our {}", beer.getLabel());
        setLcdText(0, "Bottling");
        setLcdText(1, "   " + beer.getLabel());
        Thread.sleep(15_000);

        setLedState(beer, false);

        LOGGER.info("Celebrating our new {}", beer.getLabel());
        setLcdText(0, "Ready to drink");
        setLcdText(1, "   " + beer.getLabel());

        for (var i = 0; i < 10; i++) {
            setLedState(beer, true);
            Thread.sleep(150);
            setLedState(beer, false);
            Thread.sleep(150);
        }
    }

    @Override
    public void checkIfBarrelIsEmpty(Beer beer) {
        if (!beer.isOnTap()) {
            throw new UnsupportedBeerException("The beer " + beer.getLabel() + " is unsupported - we don't have it on tap!");
        }
        LOGGER.info("Checking whether we have enough {}", beer.getLabel());
        setLcdText(0, "Checking barrel");
        setLcdText(1, "   " + beer.getLabel());
    }

    @Override
    public void drinkBeer(Beer beer) throws Exception {
        LOGGER.info("Relaxing and drinking some nice {}", beer.getLabel());
        setLedState(beer, true);
        setLcdText(0, "Drinking");
        setLcdText(1, "   " + beer.getLabel());
        Thread.sleep(2000);
        setLedState(beer, false);
    }

    @Override
    public void setLedState(Beer beer, boolean state) {
        var output = this.outputs.get(beer);
        if (output == null) {
            LOGGER.error("Can't set the LED state, not defined for {}", beer.getLabel());
            return;
        }
        output.setState(state);
        LOGGER.info("LED {} is set to {}", output.getName(), state);
    }

    @Override
    public void toggleLedState(Beer beer) {
        var output = this.outputs.get(beer);
        if (output == null) {
            LOGGER.error("Can't set the LED state, not defined for {}", beer.getLabel());
            return;
        }
        output.toggle();
        LOGGER.info("LED {} is toggled", output.getName());
    }

    @Override
    public void setLcdText(Integer line, String text) {
        if (lcd == null) {
            LOGGER.error("LCD is not initialized");
            return;
        }
        try {
            lcd.displayLineOfText(text, line);
            LOGGER.info("LCD text on line {} is set to {}", line, text);
        } catch (Exception e) {
            LOGGER.error("Error while changing the LCD text: {}", e.getMessage());
        }
    }
}
