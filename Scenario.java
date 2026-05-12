import java.util.List;

// Represents a predefined measurement scenario
public class Scenario {
    private final String mode;
    private final String name;
    private final List<DimensionModel> dimensions;

    public Scenario(String mode, String name, List<DimensionModel> dimensions) {
        this.mode = mode;
        this.name = name;
        this.dimensions = dimensions;
    }

    public String getMode() {
        return mode;
    }

    public String getName() {
        return name;
    }

    public List<DimensionModel> getDimensions() {
        return dimensions;
    }
}
