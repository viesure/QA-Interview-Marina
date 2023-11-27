package report;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.testng.ISuite;
import org.testng.ISuiteListener;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

public class SuiteListener implements ISuiteListener {
    private static int completedFeatures = 0;
    private static final int EXPECTED_FEATURES = calculateFeatureCount("src/test/resources/features");
    
    @Override
    public void onFinish(ISuite suite) {
        if (completedFeatures == EXPECTED_FEATURES) {
            onCompletion();
        }
    }

    public static synchronized void incrementCompletedFeatures() {
        completedFeatures++;
    }

    private void onCompletion() {
        //generate cucumber 
        File reportOutputDirectory = new File("target/cucumber-reports");
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("target/cucumber-reports/MVPWeatherTest.json");
        jsonFiles.add("target/cucumber-reports/OpenWeatherMapTest.json"); 
        String projectName = "MVP: Weather Forcast for Vienna + Bonus: OpenWeatherMap testing";

        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        
        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }

    private static int calculateFeatureCount(String folderPath) {
        try {
            Path path = Paths.get(folderPath);
            if (Files.isDirectory(path)) {
                return (int) Files.list(path)
                        .filter(p -> p.toString().endsWith(".feature"))
                        .count();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
