package org.jobrunr.devoxx.common;

public interface BeerService {

    default boolean isBeerOnTap(String beerName) {
        return beerName.equalsIgnoreCase("duvel") || beerName.equalsIgnoreCase("obuz");
    }

    default void brewBeer(String beerName) throws Exception {
        this.brewBeer(beerName, "");
    }

    void brewBeer(String beerName, String isSomethingGoingWrong) throws Exception;

    void checkIfBarrelIsEmpty(String beerName);

     void drinkBeer(String beerName);
}
