import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

// Step 5 panel displaying analysis results and gap analysis
public class Step5Panel extends JPanel {
    private final JPanel resultsPanel = new JPanel();

    public Step5Panel(MainFrame frame) {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Step 5: Analyse", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        add(title, BorderLayout.NORTH);

        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        add(new JScrollPane(resultsPanel), BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        JButton backButton = new JButton("Back");
        JButton finishButton = new JButton("Finish");
        buttons.add(backButton);
        buttons.add(finishButton);
        add(buttons, BorderLayout.SOUTH);

        backButton.addActionListener(event -> frame.back());
        finishButton.addActionListener(event -> JOptionPane.showMessageDialog(this, "Analysis completed."));
    }

    public void loadScenario(Scenario scenario) {
        resultsPanel.removeAll();

        Map<String, Double> scores = new LinkedHashMap<>();
        DimensionModel lowestDimension = null;
        double lowestScore = Double.MAX_VALUE;

        for (DimensionModel dimension : scenario.getDimensions()) {
            double score = dimension.calculateDimensionScore();
            scores.put(dimension.getName(), score);

            JLabel label = new JLabel(dimension.getName() + ": " + String.format("%.2f", score));
            JProgressBar bar = new JProgressBar(0, 50);
            bar.setValue((int) (score * 10));
            bar.setStringPainted(true);
            bar.setString(String.format("%.2f / 5.00", score));

            JPanel row = new JPanel(new BorderLayout());
            row.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
            row.add(label, BorderLayout.NORTH);
            row.add(bar, BorderLayout.CENTER);

            resultsPanel.add(row);

            if (score < lowestScore) {
                lowestScore = score;
                lowestDimension = dimension;
            }
        }

        resultsPanel.add(new RadarChartPanel(scores));

        if (lowestDimension != null) {
            double gap = 5.0 - lowestScore;
            String qualityLevel = getQualityLevel(lowestScore);

            JTextArea gapArea = new JTextArea();
            gapArea.setEditable(false);
            gapArea.setFont(new Font("Arial", Font.PLAIN, 16));
            gapArea.setText(
                    "Gap Analysis\n\n" +
                    "Lowest Dimension: " + lowestDimension.getName() + "\n" +
                    "Score: " + String.format("%.2f", lowestScore) + "\n" +
                    "Gap Value: " + String.format("%.2f", gap) + "\n" +
                    "Quality Level: " + qualityLevel + "\n\n" +
                    "This dimension has the lowest score and requires the most improvement."
            );
            gapArea.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
            resultsPanel.add(gapArea);
        }

        revalidate();
        repaint();
    }

    // Determines quality level according to score
    private String getQualityLevel(double score) {
        if (score >= 4.5) {
            return "Excellent";
        }

        if (score >= 3.5) {
            return "Good";
        }

        if (score >= 2.5) {
            return "Needs Improvement";
        }

        return "Poor";
    }
}
