package com.prince.jobmanagementsystem.domain;

public enum Priority {
    HIGH(3),
    MEDIUM(2),
    LOW(1);

    private final int numVal;

    Priority(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }
}
