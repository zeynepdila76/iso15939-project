import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

// Step 3 panel displaying measurement plan data
public class Step3Panel extends JPanel {
    private final DefaultTableModel tableModel = new DefaultTableModel();
    private final JTable table = new JTable(tableModel);

    public Step3Panel(MainFrame frame) {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Step 3: Plan Measurement", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        add(title, BorderLayout.NORTH);

        tableModel.setColumnIdentifiers(new String[]{"Dimension", "Metric", "Coefficient", "Direction", "Range", "Unit"});
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
                tableModel.addRow(new Object[]{
                        dimension.getName() + " (" + dimension.getCoefficient() + ")",
                        metric.getName(),
                        metric.getCoefficient(),
                        metric.getDirectionText(),
                        metric.getMin() + "-" + metric.getMax(),
                        metric.getUnit()
                });
            }
        }
    }
}
