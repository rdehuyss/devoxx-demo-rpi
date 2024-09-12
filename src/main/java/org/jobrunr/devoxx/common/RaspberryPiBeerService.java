package org.jobrunr.devoxx.common;

public class RaspberryPiService {


    public void showMessage(String message) {
        // todo: if not on RPI => then throw exception so it is retried on RPI
        System.out.println(message);
    }

}
