package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.util.Objects;

public class ExtentUtils {

    public static ExtentReports extent;

    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static void initReports() {
        if(Objects.isNull(extent)) {
            extent = new ExtentReports();
            ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/extent-output/index.html");
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setDocumentTitle("Test Report");
            spark.config().setReportName("Selenium Java Automation Framework Report");
            extent.attachReporter(spark);
        }
    }

    public static void createTest(String testcaseName) {
        ExtentTest extentTest = extent.createTest(testcaseName);
        test.set(extentTest);
    }

    public static void addAuthors(String[] authors) {
        for (String author : authors) {
            test.get().assignAuthor(author);
        }
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void addScreenshotFromPath(String path) {
        ExtentUtils.getTest().addScreenCaptureFromPath(path);
    }


    public static void flushReports() throws Exception {
        if (Objects.nonNull(extent)) {
            extent.flush();
        }
        test.remove(); // Clear ThreadLocal to prevent memory leaks
    }

}
