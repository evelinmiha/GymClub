package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

class GymClubTest {

    @Test
    void testAddMemberIncreasesListSize() {
        GymClub club = new GymClub();
        Member member = new Member("Alice", "Standard", LocalDate.now());
        member.setMembershipFee(50.0);

        club.addMember(member);

        List<Member> members = club.getAllMembers();
        assertEquals(1, members.size());
        assertEquals("Alice", members.get(0).getName());
    }

    @Test
    void testTotalFeesCalculation() {
        GymClub club = new GymClub();

        Member m1 = new Member("Bob", "Premium", LocalDate.now());
        m1.setMembershipFee(40.0);

        Member m2 = new Member("Carol", "VIP", LocalDate.now());
        m2.setMembershipFee(60.0);

        club.addMember(m1);
        club.addMember(m2);

        assertEquals(100.0, club.getTotalFees(), 0.001); // Allowing a small delta
    }

    @Test
    void testEmptyClubHasZeroFeesAndMembers() {
        GymClub club = new GymClub();

        assertTrue(club.getAllMembers().isEmpty());
        assertEquals(0.0, club.getTotalFees(), 0.001);
    }
}