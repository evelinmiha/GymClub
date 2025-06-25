package org.example;
import java.util.ArrayList;
import java.util.List;
/**
 * This class represents a Gym Club that manages members
 * and tracks the total membership fees collected.
 */
public class GymClub {
    // A list to store all members of the gym
    private List<Member> members;
    // A variable to keep track of the total membership fees collected
    private double totalFeesCollected;
// Constructor: initializes the member list and total fees
    public GymClub() {
        members = new ArrayList<>();
        totalFeesCollected = 0.0;
    }
    /**
     * Adds a new member to the gym and updates the total fees.
     * @param m The member to be added.
     */
    public void addMember(Member m) {
        members.add(m);// Add the member to the list
        totalFeesCollected += m.getMembershipFee();// Add their fee to the total
    }
    /**
     * Returns the list of all gym members.
     * @return A list of Member objects.
     */
    public List<Member> getAllMembers() {
        return members;
    }
    /**
     * Returns the total membership fees collected by the gym.
     * @return The total fees as a double.
     */
    public double getTotalFees() {
        return totalFeesCollected;
    }
}
