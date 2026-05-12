import javax.swing.*;
import java.awt.*;

// Step 1 panel for collecting user profile information
public class Step1Panel extends JPanel {
    public Step1Panel(MainFrame frame) {
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Step 1: Profile", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 26));

        JTextField usernameField = new JTextField(25);
        JTextField schoolField = new JTextField(25);
        JTextField sessionField = new JTextField(25);
        JButton nextButton = new JButton("Next");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(title, gbc);

        gbc.gridwidth = 1;

        gbc.gridy = 1;
        gbc.gridx = 0;
        add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        add(usernameField, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        add(new JLabel("School:"), gbc);
        gbc.gridx = 1;
        add(schoolField, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        add(new JLabel("Session Name:"), gbc);
        gbc.gridx = 1;
        add(sessionField, gbc);

        gbc.gridy = 4;
        gbc.gridx = 1;
        add(nextButton, gbc);

        nextButton.addActionListener(event -> {
            if (usernameField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your username to continue.");
                return;
            }

            if (schoolField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your school to continue.");
                return;
            }

            if (sessionField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your session name to continue.");
                return;
            }

            frame.setProfile(new UserProfile(
                    usernameField.getText().trim(),
                    schoolField.getText().trim(),
                    sessionField.getText().trim()
            ));

            frame.next();
        });
    }
}
