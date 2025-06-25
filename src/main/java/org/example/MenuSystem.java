package org.example;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MenuSystem {
    // Core components used in the menu system
    private GymClub gymClub;// Handles member-related operations
    private MembershipFeeCalculator feeCalculator; // Calculates fees based on membership grade
    private Scanner scanner; // Reads user input
    private DataHandler<Member> dataHandler;  // Handles saving/loading member data to/from file
    // Constructor initializes components and loads saved members from file
    public MenuSystem() {
        gymClub = new GymClub();
        feeCalculator = new MembershipFeeCalculator();
        scanner = new Scanner(System.in);
        dataHandler = new DataHandler<>("members.dat");  // Binary file for storing member data

        // Load previously saved members and add them to the gym club
        List<Member> loadedMembers = dataHandler.load();
        for (Member m : loadedMembers) {
            gymClub.addMember(m);
        }
    }

    // Displays the main menu and handles user choices in a loop
    public void displayMenu() {
        int choice = -1;
        do {
            // Print the menu options
            System.out.println("\n--- Gym Club Membership System ---");
            System.out.println("1. Add New Member");
            System.out.println("2. Display All Members");
            System.out.println("3. Display Member Receipt");
            System.out.println("4. Show Total Fees Collected");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            try{
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the leftover newline character
                handleUserChoice(choice); // Handle selected menu option
            }catch (Exception e){
                // Catch non-integer inputs
                System.out.println("Invalid input. Please enter a number.")  ;
                scanner.nextLine(); // Clear the invalid input

            }
        } while (choice != 0); // Loop until user chooses to exit
    }
    // Handles actions based on user’s menu selection
    public void handleUserChoice(int choice) {
        switch (choice) {
            case 1 -> addNewMember();  // Add a new gym member
            case 2 -> displayAllMembers();  // Display details of all members
            case 3 -> displayReceipt();   // Show receipt for a specific member
            case 4 -> System.out.println("Total Fees Collected: £" + gymClub.getTotalFees());   // Display total income
            case 0 -> System.out.println("Exiting system. Goodbye!");   // Exit message
            default -> System.out.println("Invalid choice. Try again.");  // Handle invalid menu choices
        }
    }
    // Adds a new member to the gym
    private void addNewMember() {
        System.out.print("Enter member name: ");
        String name = scanner.nextLine();

        System.out.print("Enter membership grade (Standard, Premium, VIP): ");
        String grade = scanner.nextLine().trim().toLowerCase(); // Normalize input to lowercase
        // Validate membership grade
        if (!grade.equals("standard") && !grade.equals("premium") && !grade.equals("vip")) {
            System.out.println("Invalid grade entered. Member not added.");
            return;
        }

        LocalDate regDate = LocalDate.now();     // Current date as registration date
        double totalFee = feeCalculator.calculateTotalFee(grade);   // Calculate fee based on grade

        // Capitalize the first letter of the grade for consistency
        String formattedGrade = grade.substring(0, 1).toUpperCase() + grade.substring(1).toLowerCase();
        // Create and configure a new member object
        Member member = new Member(name, formattedGrade, regDate);

        member.setMembershipFee(totalFee);
        // Add to club and save to file
        gymClub.addMember(member);
        dataHandler.save(gymClub.getAllMembers());
        System.out.println("Member added successfully!");
    }
    // Displays information for all members in the club
    private void displayAllMembers() {
        for (Member m : gymClub.getAllMembers()) {
            System.out.println(m.getName() + " (" + m.getMembershipGrade() + ") - Fee: £" + m.getMembershipFee());
        }
    }
    // Displays a payment receipt for a specific member
    private void displayReceipt() {
        System.out.print("Enter member name to generate receipt: ");
        String name = scanner.nextLine();
        // Search for the member by name (case-insensitive)
        for (Member m : gymClub.getAllMembers()) {
            if (m.getName().equalsIgnoreCase(name)) {
                System.out.println(m.generateReceipt());  // Print formatted receipt
                return;
            }
        }
        System.out.println("Member not found.");  // No matching name found
    }
}
