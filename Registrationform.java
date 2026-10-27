/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package eventregistrationsystem;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationForm extends JFrame {

    private JTextField nameField, emailField, phoneField;
    private JComboBox<String> eventBox;

    private static final String URL =
            "jdbc:mysql://localhost:3306/event_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public RegistrationForm() {
        setTitle("Event Registration System");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 12));
        panel.setBorder(BorderFactory.createEmptyBorder(
                25, 25, 25, 25));

        JLabel title = new JLabel("Event Registration", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        nameField = new JTextField();
        emailField = new JTextField();
        phoneField = new JTextField();

        eventBox = new JComboBox<>(new String[]{
            "Java Workshop",
            "Coding Competition",
            "Technical Seminar",
            "Hackathon"
        });

        JButton registerButton = new JButton("Register");

        panel.add(new JLabel("Participant Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Phone:"));
        panel.add(phoneField);
        panel.add(new JLabel("Event Name:"));
        panel.add(eventBox);
        panel.add(new JLabel(""));
        panel.add(registerButton);

        add(panel, BorderLayout.CENTER);

        registerButton.addActionListener(e -> registerParticipant());
    }

    private void registerParticipant() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String event = (String) eventBox.getSelectedItem();

        if (name.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter your name and email.");
            return;
        }

        String sql = "INSERT INTO registrations "
                + "(participant_name, email, phone, event_name) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(
                    URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, event);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "Registration successful!");

            nameField.setText("");
            emailField.setText("");
            phoneField.setText("");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Database error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RegistrationForm().setVisible(true);
        });
    }
}
