package com.virusvaccine.dto;

public enum VaccineType {

    PF(1),
    MD(2),
    AZ(3),
    JS(4),
    NV(5);

    private final int type;

    VaccineType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
