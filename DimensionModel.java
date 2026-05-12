import java.util.List;

// Represents a quality dimension containing multiple metrics
public class DimensionModel {
    private final String name;
    private final int coefficient;
    private final List<Metric> metrics;

    public DimensionModel(String name, int coefficient, List<Metric> metrics) {
        this.name = name;
        this.coefficient = coefficient;
        this.metrics = metrics;
    }

    // Calculates weighted average score for the dimension
    public double calculateDimensionScore() {
        double total = 0;
        double coefficientTotal = 0;

        for (Metric metric : metrics) {
            metric.calculateScore();
            total += metric.getScore() * metric.getCoefficient();
            coefficientTotal += metric.getCoefficient();
        }

        return total / coefficientTotal;
    }

    public String getName() {
        return name;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public List<Metric> getMetrics() {
        return metrics;
    }
}
