package org.jobrunr.devoxx.common;

public enum Beer {
    DUVEL(1, "Duvel", 21),
    STELLA(2, "Stella", 16),
    OBUZ(3, "Obuz", 12),
    CHIMAY(4, "Chimay", 25),
    STRAFFE_HENDRIK(5, "Straffe Hendrik", 24),
    LA_CHOUFFE(6, "La Chouffe", 18),
    WESTVLETEREN(7, "Westvleteren", 0),
    UNKNOWN(0, "Unknown", 0);

    private final int id;
    private final String label;
    private final int tapNumber;

    Beer(int id, String label, int tapNumber) {
        this.id = id;
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

    public int getId() {
        return id;
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

    public String asJson() {
        return "{ \"id\": " + id + ", \"label\": \"" + label + "\" }";
    }
}
