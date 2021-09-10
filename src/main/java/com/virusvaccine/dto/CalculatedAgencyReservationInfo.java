package com.virusvaccine.dto;

import java.util.Arrays;

public class CalculatedAgencyReservationInfo {

    private final long[] amount = new long[5];
    private final long[] bookedAmount = new long[5];

    public long[] getAmount() {
        return amount;
    }

    public long[] getBookedAmount() {
        return bookedAmount;
    }

    @Override
    public String toString() {
        return "CalculatedAgencyReservationInfo{" +
                "amount=" + Arrays.toString(amount) +
                ", bookedAmount=" + Arrays.toString(bookedAmount) +
                '}';
    }
}
