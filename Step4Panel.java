import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

// Step 4 panel displaying collected data and calculated scores
public class Step4Panel extends JPanel {
    private final DefaultTableModel tableModel = new DefaultTableModel();
    private final JTable table = new JTable(tableModel);

    public Step4Panel(MainFrame frame) {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Step 4: Collect Data", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        add(title, BorderLayout.NORTH);

        tableModel.setColumnIdentifiers(new String[]{"Metric", "Direction", "Range", "Value", "Score", "Coeff / Unit"});
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        JButton backButton = new JButton("Back");
        JButton nextButton = new JButton("Next");
        buttons.add(backButton);
        buttons.add(nextButton);
        add(buttons, BorderLayout.SOUTH);

        backButton.addActionListener(event -> frame.back());
        nextButton.addActionListener(event -> frame.next());
    }

    public void loadScenario(Scenario scenario) {
        tableModel.setRowCount(0);

        for (DimensionModel dimension : scenario.getDimensions()) {
            for (Metric metric : dimension.getMetrics()) {
                metric.calculateScore();

                tableModel.addRow(new Object[]{
                        metric.getName(),
                        metric.getDirectionText(),
                        metric.getMin() + "-" + metric.getMax(),
                        metric.getValue(),
                        metric.getScore(),
                        metric.getCoefficient() + " / " + metric.getUnit()
                });
            }
        }
    }
}
