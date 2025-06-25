package org.example;

// Import necessary libraries
import java.io.Serializable;   // Allows the object to be saved to a file or sent over a network
import java.time.LocalDate;    // Used to handle dates
import java.time.format.DateTimeFormatter;  // (Imported but not used here) Could be used to format dates
/**
 * Represents a member who enrolls with a membership grade and registration date.
 * Can calculate fees and generate a receipt.
 */

public class Member implements Serializable {
    // Member's full name
    private String name;
    // Grade of the membership (e.g., Basic, Premium)
    private String membershipGrade;
    // Date when the member registered
    private LocalDate registrationDate;
    // Total fee the member has to pay
    private double membershipFee;

    // Constructor: creates a new Member with name, membership grade, and registration date
    public Member(String name, String membershipGrade, LocalDate registrationDate) {
        this.name = name;
        this.membershipGrade = membershipGrade;
        this.registrationDate = registrationDate;
    }

    // Sets the total membership fee for the member
    public void setMembershipFee(double fee) {
        this.membershipFee = fee;
    }

    // Returns the total fee for the membership
    public double calculateFee() {
        return membershipFee;
    }

    // Generates and returns a receipt as a formatted string
    public String generateReceipt() {
        return "Statement for " + registrationDate.getMonth() + " " + registrationDate.getYear()
                + " for " + name + " - NEW " + membershipGrade + " Membership\n"
                + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n"
                + membershipGrade + " Member Enrolment\n"
                + "   Date: " + registrationDate + "\n\n"
                + "Purchases:\n"
                + "   1: Annual Membership: £" + String.format("%.2f", membershipFee - 8) + "\n"
                + "   2: Journal Fee:        £8.00\n\n"
                + "Total Price: £" + String.format("%.2f", membershipFee) + "\n\n"
                + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++";
    }

    // Getter method: returns the member's name
    public String getName() {
        return name;
    }
    // Getter method: returns the membership grade
    public String getMembershipGrade() {
        return membershipGrade;
    }
    // Getter method: returns the registration date
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    // Getter method: returns the total membership fee
    public double getMembershipFee() {
        return membershipFee;
    }

}
