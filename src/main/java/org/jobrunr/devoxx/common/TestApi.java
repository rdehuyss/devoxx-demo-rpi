package org.jobrunr.devoxx.common;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("test")
public class TestApi {

    private static final Random RANDOM = new Random();
    private final BeerService beerService;

    public TestApi(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping("/led/{value}")
    public String setLedsOn(@PathVariable String value) {
        if (value.equalsIgnoreCase("on") || value.equalsIgnoreCase("off")) {
            for (Beer beer : Beer.values()) {
                beerService.setLedState(beer, value.equalsIgnoreCase("on"));
            }
        } else {
            try {
                beerService.toggleLedState(Beer.fromId(Integer.valueOf(value)));
            } catch (Exception e) {
                return "Invalid value: " + value;
            }
        }
        return "Turned all on";
    }

    @GetMapping("/led/off")
    public String setLedsOff() {
        for (Beer beer : Beer.values()) {
            beerService.setLedState(beer, false);
        }
        return "Turned all off";
    }

    @GetMapping("/lcd")
    public String setRandomTextOnLcd() {
        var line1 = generateRandomText();
        var line2 = generateRandomText();
        beerService.setLcdText(line1, line2);
        return "Set on LCD: " + line1 + ", " + line2;
    }

    @GetMapping("/lcd/{line1}/{line2}")
    public String setRandomTextOnLcd(@PathVariable String line1, @PathVariable String line2) {
        beerService.setLcdText(line1, line2);
        return "Set on LCD: " + line1 + "/" + line2;
    }

    private String generateRandomText() {
        var chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 ";

        StringBuilder text = new StringBuilder();

        for (int i = 0; i < 16; i++) {
            int index = RANDOM.nextInt(chars.length());
            text.append(chars.charAt(index));
        }

        return text.toString();
    }
}

