package com.virusvaccine.lookupReservation.dto;

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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CalculatedAgencyReservationInfo)) {
            return false;
        }
        CalculatedAgencyReservationInfo that = (CalculatedAgencyReservationInfo) o;
        return Arrays.equals(amount, that.amount) && Arrays.equals(bookedAmount,
            that.bookedAmount);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(amount);
        result = 31 * result + Arrays.hashCode(bookedAmount);
        return result;
    }

    @Override
    public String toString() {
        return "CalculatedAgencyReservationInfo{" +
                "amount=" + Arrays.toString(amount) +
                ", bookedAmount=" + Arrays.toString(bookedAmount) +
                '}';
    }
}
