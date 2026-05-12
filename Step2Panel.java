import javax.swing.*;
import java.awt.*;
import java.util.Objects;

// Step 2 panel for quality type and scenario selection
public class Step2Panel extends JPanel {
    private final JRadioButton educationModeButton = new JRadioButton("Education", true);
    private final JRadioButton healthModeButton = new JRadioButton("Health");
    private final JComboBox<String> scenarioBox = new JComboBox<>();

    public Step2Panel(MainFrame frame) {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Step 2: Define Quality Dimensions", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        add(title, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(4, 1, 10, 10));
        center.setBorder(BorderFactory.createEmptyBorder(40, 250, 40, 250));

        JRadioButton productButton = new JRadioButton("Product Quality", true);
        JRadioButton processButton = new JRadioButton("Process Quality");

        ButtonGroup qualityGroup = new ButtonGroup();
        qualityGroup.add(productButton);
        qualityGroup.add(processButton);

        JPanel qualityPanel = new JPanel();
        qualityPanel.setBorder(BorderFactory.createTitledBorder("Quality Type"));
        qualityPanel.add(productButton);
        qualityPanel.add(processButton);

        ButtonGroup modeGroup = new ButtonGroup();
        modeGroup.add(educationModeButton);
        modeGroup.add(healthModeButton);

        JPanel modePanel = new JPanel();
        modePanel.setBorder(BorderFactory.createTitledBorder("Mode"));
        modePanel.add(educationModeButton);
        modePanel.add(healthModeButton);

        JPanel scenarioPanel = new JPanel();
        scenarioPanel.setBorder(BorderFactory.createTitledBorder("Scenario"));
        scenarioPanel.add(scenarioBox);

        updateScenarioOptions();

        educationModeButton.addActionListener(event -> updateScenarioOptions());
        healthModeButton.addActionListener(event -> updateScenarioOptions());

        center.add(qualityPanel);
        center.add(modePanel);
        center.add(scenarioPanel);

        JPanel buttons = new JPanel();
        JButton backButton = new JButton("Back");
        JButton nextButton = new JButton("Next");
        buttons.add(backButton);
        buttons.add(nextButton);

        add(center, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        backButton.addActionListener(event -> frame.back());

        nextButton.addActionListener(event -> {
            String mode = educationModeButton.isSelected() ? "Education" : "Health";
            String scenarioName = Objects.toString(scenarioBox.getSelectedItem());

            frame.setSelectedScenario(DataRepository.getScenario(mode, scenarioName));
            frame.next();
        });
    }

    private void updateScenarioOptions() {
        scenarioBox.removeAllItems();

        if (educationModeButton.isSelected()) {
            scenarioBox.addItem("Scenario C - Team Alpha");
            scenarioBox.addItem("Scenario D - Team Beta");
        } else {
            scenarioBox.addItem("Scenario A - Hospital System");
            scenarioBox.addItem("Scenario B - Patient Portal");
        }
    }
}
