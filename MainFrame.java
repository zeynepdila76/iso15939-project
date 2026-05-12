import javax.swing.*;
import java.awt.*;
// Main application window using CardLayout navigation
public class MainFrame extends JFrame {
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel cards = new JPanel(cardLayout);
    private final JLabel stepLabel = new JLabel("", SwingConstants.CENTER);

    private UserProfile profile;
    private Scenario selectedScenario;

    private final Step1Panel step1;
    private final Step2Panel step2;
    private final Step3Panel step3;
    private final Step4Panel step4;
    private final Step5Panel step5;

    private int currentStep = 1;

    public MainFrame() {
        setTitle("ISO 15939 Measurement Process Simulator");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        stepLabel.setFont(new Font("Arial", Font.BOLD, 16));
        stepLabel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        add(stepLabel, BorderLayout.NORTH);

        step1 = new Step1Panel(this);
        step2 = new Step2Panel(this);
        step3 = new Step3Panel(this);
        step4 = new Step4Panel(this);
        step5 = new Step5Panel(this);

        cards.add(step1, "1");
        cards.add(step2, "2");
        cards.add(step3, "3");
        cards.add(step4, "4");
        cards.add(step5, "5");

        add(cards, BorderLayout.CENTER);
        updateStepLabel();
    }

    public void next() {
        if (currentStep < 5) {
            currentStep++;

            if (currentStep == 3) {
                step3.loadScenario(selectedScenario);
            }

            if (currentStep == 4) {
                step4.loadScenario(selectedScenario);
            }

            if (currentStep == 5) {
                step5.loadScenario(selectedScenario);
            }

            cardLayout.show(cards, String.valueOf(currentStep));
            updateStepLabel();
        }
    }

    public void back() {
        if (currentStep > 1) {
            currentStep--;
            cardLayout.show(cards, String.valueOf(currentStep));
            updateStepLabel();
        }
    }

    // Updates the current wizard step indicator
    private void updateStepLabel() {
        String[] names = {"Profile", "Define", "Plan", "Collect", "Analyse"};
        StringBuilder text = new StringBuilder();

        for (int i = 0; i < names.length; i++) {
            if (i + 1 < currentStep) {
                text.append("✓ ");
            }

            if (i + 1 == currentStep) {
                text.append("[");
            }

            text.append(i + 1).append(". ").append(names[i]);

            if (i + 1 == currentStep) {
                text.append("]");
            }

            if (i < names.length - 1) {
                text.append("  →  ");
            }
        }

        stepLabel.setText(text.toString());
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public void setSelectedScenario(Scenario selectedScenario) {
        this.selectedScenario = selectedScenario;
    }

    public Scenario getSelectedScenario() {
        return selectedScenario;
    }
}
