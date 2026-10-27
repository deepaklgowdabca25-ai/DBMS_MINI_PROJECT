/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package eventregistrationsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EventRegistrationSystem {

    private static final String URL =
            "jdbc:mysql://localhost:3306/event_db";
    private static final String USER = "root";

    // Replace with your MySQL root password
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        String sql = "INSERT INTO registrations "
                + "(participant_name, email, phone, event_name) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(
                    URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "Test Participant");
            ps.setString(2, "test@example.com");
            ps.setString(3, "9876543210");
            ps.setString(4, "Java Workshop");

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println(
                        "Registration saved successfully!");
            }

        } catch (SQLException e) {
            System.out.println("Database error: "
                    + e.getMessage());
        }
    }
}

