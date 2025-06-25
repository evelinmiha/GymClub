package org.example;
/**
 * This interface defines methods for calculating membership fees.
 * Any class that implements this interface must provide these methods.
 */
public interface FeeCalculator {
 // Returns the base membership fee for the given membership grade
 double getMembershipFee(String grade);
 // Returns the total fee (base fee + any additional fees like journal fee)
 double calculateTotalFee(String grade);
}
