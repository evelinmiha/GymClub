package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
class MemberTest {

    @Test
    void testSetAndGetMembershipFee() {
        Member m = new Member("Alice", "Premium", LocalDate.of(2024, 5, 10));
        m.setMembershipFee(158.0); // £150 + £8
        assertEquals(158.0, m.getMembershipFee(), 0.001);
    }

    @Test
    void testCalculateFeeReturnsCorrectValue() {
        Member m = new Member("Bob", "VIP", LocalDate.of(2024, 3, 1));
        m.setMembershipFee(208.0);
        assertEquals(208.0, m.calculateFee(), 0.001);
    }

    @Test
    void testReceiptGenerationIncludesCorrectDetails() {
        Member m = new Member("Charlie", "Standard", LocalDate.of(2024, 1, 1));
        m.setMembershipFee(108.0); // £100 + £8
        String receipt = m.generateReceipt();
        assertTrue(receipt.contains("Charlie"));
        assertTrue(receipt.contains("Standard"));
        assertTrue(receipt.contains("108.00"));
    }

    @Test
    void testMemberFieldsAreNotNullAfterConstruction() {
        Member m = new Member("Diana", "VIP", LocalDate.now());
        assertNotNull(m.getName());
        assertNotNull(m.getMembershipGrade());
        assertNotNull(m.getRegistrationDate());
    }

    @Test
    void testIncorrectFeeDoesNotMatchExpected() {
        Member m = new Member("Eli", "Standard", LocalDate.of(2023, 12, 31));
        m.setMembershipFee(120.0);
        assertNotEquals(108.0, m.getMembershipFee());
    }
}