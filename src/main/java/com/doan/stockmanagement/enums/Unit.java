package com.doan.stockmanagement.enums;

public enum Unit {
    UNIT_CHAI(1, "Chai"), UNIT_KG(2, "Kg"), UNIT_CAI(3, "Cái");

    public int getUnitCode() {
        return unitCode;
    }

    public String getDescription() {
        return description;
    }

    private final int unitCode;
    private final String description;

    private Unit(int unitCode, String description) {
        this.unitCode = unitCode;
        this.description = description;
    }

}
