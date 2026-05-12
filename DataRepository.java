import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Stores predefined scenario datasets
public class DataRepository {
    // Returns scenario data according to selected mode and scenario
    public static Scenario getScenario(String mode, String scenarioName) {
        if (mode.equals("Education") && scenarioName.contains("Team Alpha")) {
            return educationAlpha();
        }

        if (mode.equals("Education")) {
            return educationBeta();
        }

        if (scenarioName.contains("Hospital")) {
            return healthHospital();
        }

        return healthPortal();
    }

    public static Scenario educationAlpha() {
        List<DimensionModel> dimensions = new ArrayList<>();

        dimensions.add(new DimensionModel("Usability", 25, Arrays.asList(
                new Metric("SUS score", 50, true, 0, 100, "points", 89),
                new Metric("Onboarding time", 50, false, 0, 60, "min", 5)
        )));

        dimensions.add(new DimensionModel("Performance Efficiency", 20, Arrays.asList(
                new Metric("Video start time", 50, false, 0, 15, "sec", 3),
                new Metric("Concurrent exams", 50, true, 0, 600, "users", 500)
        )));

        dimensions.add(new DimensionModel("Accessibility", 20, Arrays.asList(
                new Metric("WCAG compliance", 50, true, 0, 100, "%", 82),
                new Metric("Screen reader score", 50, true, 0, 100, "%", 76)
        )));

        dimensions.add(new DimensionModel("Reliability", 20, Arrays.asList(
                new Metric("Uptime", 50, true, 95, 100, "%", 98),
                new Metric("MTTR", 50, false, 0, 120, "min", 30)
        )));

        dimensions.add(new DimensionModel("Functional Suitability", 15, Arrays.asList(
                new Metric("Feature completion", 50, true, 0, 100, "%", 90),
                new Metric("Assignment submit rate", 50, true, 0, 100, "%", 85)
        )));

        return new Scenario("Education", "Scenario C - Team Alpha", dimensions);
    }

    public static Scenario educationBeta() {
        List<DimensionModel> dimensions = new ArrayList<>();

        dimensions.add(new DimensionModel("Usability", 25, Arrays.asList(
                new Metric("SUS score", 50, true, 0, 100, "points", 70),
                new Metric("Onboarding time", 50, false, 0, 60, "min", 20)
        )));

        dimensions.add(new DimensionModel("Performance Efficiency", 20, Arrays.asList(
                new Metric("Video start time", 50, false, 0, 15, "sec", 7),
                new Metric("Concurrent exams", 50, true, 0, 600, "users", 350)
        )));

        dimensions.add(new DimensionModel("Accessibility", 20, Arrays.asList(
                new Metric("WCAG compliance", 50, true, 0, 100, "%", 65),
                new Metric("Screen reader score", 50, true, 0, 100, "%", 60)
        )));

        dimensions.add(new DimensionModel("Reliability", 20, Arrays.asList(
                new Metric("Uptime", 50, true, 95, 100, "%", 97),
                new Metric("MTTR", 50, false, 0, 120, "min", 55)
        )));

        dimensions.add(new DimensionModel("Functional Suitability", 15, Arrays.asList(
                new Metric("Feature completion", 50, true, 0, 100, "%", 78),
                new Metric("Assignment submit rate", 50, true, 0, 100, "%", 72)
        )));

        return new Scenario("Education", "Scenario D - Team Beta", dimensions);
    }

    public static Scenario healthHospital() {
        List<DimensionModel> dimensions = new ArrayList<>();

        dimensions.add(new DimensionModel("Security", 25, Arrays.asList(
                new Metric("Login success rate", 50, true, 0, 100, "%", 92),
                new Metric("Unauthorized attempts", 50, false, 0, 100, "attempts", 15)
        )));

        dimensions.add(new DimensionModel("Reliability", 25, Arrays.asList(
                new Metric("System uptime", 50, true, 95, 100, "%", 99),
                new Metric("Error rate", 50, false, 0, 20, "%", 4)
        )));

        dimensions.add(new DimensionModel("Efficiency", 25, Arrays.asList(
                new Metric("Patient search time", 50, false, 0, 30, "sec", 6),
                new Metric("Report generation", 50, false, 0, 60, "sec", 18)
        )));

        dimensions.add(new DimensionModel("Usability", 25, Arrays.asList(
                new Metric("User satisfaction", 50, true, 0, 100, "points", 84),
                new Metric("Training time", 50, false, 0, 120, "min", 35)
        )));

        return new Scenario("Health", "Scenario A - Hospital System", dimensions);
    }

    public static Scenario healthPortal() {
        List<DimensionModel> dimensions = new ArrayList<>();

        dimensions.add(new DimensionModel("Accessibility", 25, Arrays.asList(
                new Metric("Mobile compatibility", 50, true, 0, 100, "%", 80),
                new Metric("WCAG score", 50, true, 0, 100, "%", 74)
        )));

        dimensions.add(new DimensionModel("Performance", 25, Arrays.asList(
                new Metric("Page load time", 50, false, 0, 10, "sec", 3),
                new Metric("Requests per minute", 50, true, 0, 1000, "requests", 760)
        )));

        dimensions.add(new DimensionModel("Reliability", 25, Arrays.asList(
                new Metric("Portal uptime", 50, true, 95, 100, "%", 98),
                new Metric("Downtime", 50, false, 0, 300, "min", 40)
        )));

        dimensions.add(new DimensionModel("Functional Suitability", 25, Arrays.asList(
                new Metric("Appointment completion", 50, true, 0, 100, "%", 88),
                new Metric("Prescription access rate", 50, true, 0, 100, "%", 79)
        )));

        return new Scenario("Health", "Scenario B - Patient Portal", dimensions);
    }
}
