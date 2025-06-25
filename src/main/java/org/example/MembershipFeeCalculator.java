package org.example;

import java.util.HashMap;
import java.util.Map;

/**
 * This class calculates membership fees based on membership grade.
 * It also includes a fixed journal fee added to the base fee.
 */
public class MembershipFeeCalculator implements FeeCalculator {
    // A map to store membership grades and their base fees
    private Map<String, Double> feeTable;
    // Fixed fee for the journal
    private double journalFee;
    // Constructor: initializes the fee table and sets the journal fee
    public MembershipFeeCalculator() {
        feeTable = new HashMap<>();  // Create a new empty fee table

        // Add membership grades with corresponding base fees
        feeTable.put("standard", 100.0);
        feeTable.put("premium", 150.0);
        feeTable.put("vip", 200.0);


        // Set the journal fee
        journalFee = 8.0;
    }


    // Returns the base membership fee for a given grade
    @Override
    public double getMembershipFee(String grade) {
        return feeTable.getOrDefault(grade.toLowerCase(), 0.0);

    }
    // Calculates the total fee (base fee + journal fee) for a given grade
    @Override
    public double calculateTotalFee(String grade) {
        return getMembershipFee(grade) + journalFee;
    }
    // Getter method: returns the fixed journal fee
    public double getJournalFee() {
        return journalFee;
    }
}
