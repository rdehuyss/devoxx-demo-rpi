package org.jobrunr.devoxx.common;

public interface BeerService {

    default boolean isBeerOnTap(Beer beer) {
        return beer.isOnTap();
    }

    default void brewBeer(Beer beer) throws Exception {
        this.brewBeer(beer, "");
    }

    void brewBeer(Beer beer, String isSomethingGoingWrong) throws Exception;

    void checkIfBarrelIsEmpty(Beer beer);

    void drinkBeer(Beer beer);

    void setLedState(Beer beer, boolean state);

    void toggleLedState(Beer beer);

    void setLcdText(Integer line, String text);
}
