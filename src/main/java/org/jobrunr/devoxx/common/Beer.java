package org.jobrunr.devoxx.common;

public enum Beer {
    DUVEL("Duvel", 1),
    STELLA("Stella", 2),
    OBUZ("Obuz", 3),
    WESTVLETEREN("Westvleteren", 0),
    UNKNOWN("Unknown", 0);

    private final String label;
    private final int tapNumber;

    Beer(String label, int tapNumber) {
        this.label = label;
        this.tapNumber = tapNumber;
    }

    public static Beer fromLabel(String label) {
        for (Beer beer : values()) {
            if (beer.label.equalsIgnoreCase(label.trim())) {
                return beer;
            }
        }
        return UNKNOWN;
    }

    public String getLabel() {
        return label;
    }

    public int getTapNumber() {
        return tapNumber;
    }

    public boolean isOnTap() {
        return tapNumber > 0;
    }
}
