package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.List;


/**
 * Graphical User Interface (GUI) for managing gym club members.
 * Allows users to add members, view all members, and display individual receipts.
 */
public class GymClubGUI extends JFrame{
    // Core components
    private GymClub gymClub;             // Holds member list and total fees
    private MembershipFeeCalculator feeCalculator;       // Calculates membership fees
    private DataHandler<Member> dataHandler;        // Handles saving/loading member data
    private JTextArea outputArea;                   // Displays output text

    // Constructor: initializes the GUI and loads any saved data
    public GymClubGUI() {
        super("Gym Club Membership");

        gymClub = new GymClub();
        feeCalculator = new MembershipFeeCalculator();
        dataHandler = new DataHandler<>("members.dat");

        // Load any previously saved members into the gymClub
        gymClub.getAllMembers().addAll(dataHandler.load());

        setupUI();  // Build the GUI
    }
    // Sets up the GUI layout and functionality
    private void setupUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);  // Exit program on close
        setSize(600, 400);          // Set window size
        setLayout(new BorderLayout());

        // Create a non-editable text area for output and add it to the center
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // Set up the menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        // Create menu items
        JMenuItem addMemberItem = new JMenuItem("Add New Member");
        JMenuItem displayMembersItem = new JMenuItem("Display Members");
        JMenuItem displayReceiptItem = new JMenuItem("Display Member Receipt");
        JMenuItem exitItem = new JMenuItem("Exit");

        // Assign actions to each menu item
        addMemberItem.addActionListener(e -> addMemberDialog());
        displayMembersItem.addActionListener(e -> displayMembers());
        displayReceiptItem.addActionListener(e -> displayReceiptDialog());
        exitItem.addActionListener(e -> {
            dataHandler.save(gymClub.getAllMembers()); // Save members when exiting
            System.exit(0);                            // Close application
        });

        // Add items to the menu and menu bar
        menu.add(addMemberItem);
        menu.add(displayMembersItem);
        menu.add(displayReceiptItem);
        menu.add(exitItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        setVisible(true);        // Show the window
    }

    // Dialog to add a new member
    private void addMemberDialog() {
        JPanel panel = new JPanel(new GridLayout(0, 1)); // Arrange fields vertically
        JTextField nameField = new JTextField();
        String[] grades = { "Standard", "Premium", "VIP" };   // Membership options
        JComboBox<String> gradeBox = new JComboBox<>(grades);

        // Add components to the panel
        panel.add(new JLabel("Enter member name:"));
        panel.add(nameField);
        panel.add(new JLabel("Select membership grade:"));
        panel.add(gradeBox);

        // Show dialog and wait for user to press OK or Cancel
        int result = JOptionPane.showConfirmDialog(this, panel, "Add Member",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        // If user pressed OK
        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText().trim();
            String grade = (String) gradeBox.getSelectedItem();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name cannot be empty.");
                return;
            }

            // Calculate fee and create new Member object
            double totalFee = feeCalculator.calculateTotalFee(grade.toLowerCase());
            Member member = new Member(name, grade, LocalDate.now());
            member.setMembershipFee(totalFee);
            gymClub.addMember(member);  // Add member to the gym club
            // Display confirmation
            outputArea.setText("Member added successfully:\n" + name + " - " + grade);
        }
    }

    // Display all members in the text area
    private void displayMembers() {
        List<Member> members = gymClub.getAllMembers();
        if (members.isEmpty()) {
            outputArea.setText("No members found.");
            return;
        }
        // Build a string listing all members
        StringBuilder sb = new StringBuilder("List of Members:\n");
        for (Member m : members) {
            sb.append(m.getName()).append(" (")
                    .append(m.getMembershipGrade()).append(") - £")
                    .append(String.format("%.2f", m.getMembershipFee()))
                    .append("\n");
        }
        // Display in output area
        outputArea.setText(sb.toString());
    }
    // Prompt for a member name and display their receipt
    private void displayReceiptDialog() {
        String name = JOptionPane.showInputDialog(this, "Enter member name:");

        if (name != null && !name.trim().isEmpty()) {
            for (Member m : gymClub.getAllMembers()) {
                if (m.getName().equalsIgnoreCase(name.trim())) {
                    outputArea.setText(m.generateReceipt());
                    return;
                }
            }
            outputArea.setText("Member not found.");
        }
    }
}
