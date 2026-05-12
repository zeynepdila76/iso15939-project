// Represents a measurement metric
public class Metric {
    private final String name;
    private final int coefficient;
    private final boolean higherBetter;
    private final double min;
    private final double max;
    private final String unit;
    private final double value;
    private double score;

    public Metric(String name, int coefficient, boolean higherBetter, double min, double max, String unit, double value) {
        this.name = name;
        this.coefficient = coefficient;
        this.higherBetter = higherBetter;
        this.min = min;
        this.max = max;
        this.unit = unit;
        this.value = value;
    }

    // Calculates metric score between 1.0 and 5.0
    public void calculateScore() {
        double rawScore;

        if (higherBetter) {
            rawScore = 1 + ((value - min) / (max - min)) * 4;
        } else {
            rawScore = 5 - ((value - min) / (max - min)) * 4;
        }

        rawScore = Math.max(1.0, Math.min(5.0, rawScore));
        score = Math.round(rawScore * 2.0) / 2.0;
    }

    public String getDirectionText() {
        return higherBetter ? "Higher ↑" : "Lower ↓";
    }

    public String getName() {
        return name;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public String getUnit() {
        return unit;
    }

    public double getValue() {
        return value;
    }

    public double getScore() {
        return score;
    }
}
