import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Displays radar chart visualization of dimension scores
public class RadarChartPanel extends JPanel {
    private final Map<String, Double> scores;

    public RadarChartPanel(Map<String, Double> scores) {
        this.scores = scores;
        setPreferredSize(new Dimension(700, 350));
        setMaximumSize(new Dimension(900, 400));
        setBorder(BorderFactory.createTitledBorder("Radar Chart"));
    }

    // Draws radar chart using Java Graphics2D
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        if (scores.isEmpty()) {
            return;
        }

        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = Math.min(getWidth(), getHeight()) / 3;
        int dimensionCount = scores.size();

        List<String> names = new ArrayList<>(scores.keySet());
        List<Double> values = new ArrayList<>(scores.values());

        for (int level = 1; level <= 5; level++) {
            Path2D polygon = new Path2D.Double();
            double currentRadius = radius * level / 5.0;

            for (int i = 0; i < dimensionCount; i++) {
                double angle = -Math.PI / 2 + 2 * Math.PI * i / dimensionCount;
                double x = centerX + currentRadius * Math.cos(angle);
                double y = centerY + currentRadius * Math.sin(angle);

                if (i == 0) {
                    polygon.moveTo(x, y);
                } else {
                    polygon.lineTo(x, y);
                }
            }

            polygon.closePath();
            graphics2D.draw(polygon);
        }

        Path2D dataPolygon = new Path2D.Double();

        for (int i = 0; i < dimensionCount; i++) {
            double angle = -Math.PI / 2 + 2 * Math.PI * i / dimensionCount;
            double axisX = centerX + radius * Math.cos(angle);
            double axisY = centerY + radius * Math.sin(angle);

            graphics2D.drawLine(centerX, centerY, (int) axisX, (int) axisY);
            graphics2D.drawString(names.get(i), (int) axisX - 45, (int) axisY);

            double valueRadius = radius * values.get(i) / 5.0;
            double x = centerX + valueRadius * Math.cos(angle);
            double y = centerY + valueRadius * Math.sin(angle);

            if (i == 0) {
                dataPolygon.moveTo(x, y);
            } else {
                dataPolygon.lineTo(x, y);
            }
        }

        dataPolygon.closePath();
        graphics2D.setStroke(new BasicStroke(3));
        graphics2D.draw(dataPolygon);
    }
}
