package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;


class MembershipFeeCalculatorTest {

    private MembershipFeeCalculator calc;

    @BeforeEach
    void setUp() {
        calc = new MembershipFeeCalculator();
    }

    @Test
    void testGetMembershipFee_Standard() {
        assertEquals(100.0, calc.getMembershipFee("standard"), 0.001);
        assertEquals(100.0, calc.getMembershipFee("Standard"), 0.001);
    }

    @Test
    void testGetMembershipFee_Premium() {
        assertEquals(150.0, calc.getMembershipFee("premium"), 0.001);
    }

    @Test
    void testGetMembershipFee_VIP() {
        assertEquals(200.0, calc.getMembershipFee("vip"), 0.001);
    }

    @Test
    void testGetMembershipFee_Unknown() {
        assertEquals(0.0, calc.getMembershipFee("bronze"), 0.001);
    }

    @Test
    void testCalculateTotalFee() {
        // total = base + journalFee (8.0)
        assertEquals(108.0, calc.calculateTotalFee("standard"), 0.001);
        assertEquals(158.0, calc.calculateTotalFee("premium"), 0.001);
        assertEquals(208.0, calc.calculateTotalFee("vip"), 0.001);
        assertEquals(8.0,   calc.calculateTotalFee("unknown"), 0.001);
    }

    @Test
    void testGetJournalFee() {
        assertEquals(8.0, calc.getJournalFee(), 0.001);
    }

    @Test
    void testGetMembershipFee_NullGradeThrows() {
        assertThrows(NullPointerException.class, () -> calc.getMembershipFee(null));
    }
}