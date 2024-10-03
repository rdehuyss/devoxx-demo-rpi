package org.jobrunr.devoxx.common;

import org.jobrunr.devoxx.common.tap.BeerTap;

public class BeerService {

    private final BeerTap beerTap;

    public BeerService(BeerTap beerTap) {
        this.beerTap = beerTap;
    }

    public boolean isBeerOnTap(Beer beer) {
        return beerTap.isBeerOnTap(beer);
    }

    public void brewBeer(Beer beer) throws Exception {
        beerTap.brewBeer(beer);
    }

    public void brewBeer(Beer beer, String isSomethingGoingWrong) throws Exception {
        beerTap.brewBeer(beer, isSomethingGoingWrong);
    }

    public void checkIfBarrelIsEmpty(Beer beer) {
        beerTap.checkIfBarrelIsEmpty(beer);
    }

    public void drinkBeer(Beer beer) throws Exception {
        beerTap.drinkBeer(beer);
    }

    public void setLedState(Beer beer, boolean state) {
        beerTap.setLedState(beer, state);
    }

    public void toggleLedState(Beer beer) {
        beerTap.toggleLedState(beer);
    }

    public void setLcdText(String line1, String line2) {
        beerTap.setLcdText(line1, line2);
    }
}
