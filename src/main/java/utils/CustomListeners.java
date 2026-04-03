package utils;

import annotations.Author;
import org.testng.*;
import org.testng.annotations.ITestAnnotation;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;

public class CustomListeners implements ITestListener, IAnnotationTransformer {

    @Override
    public void onStart(ITestContext context) {
        try {
            ExtentUtils.initReports();
        }
        catch (Exception e) {

            throw new RuntimeException();
        }

    }

    @Override
    public void onTestStart(ITestResult result) {

        String testName = result.getMethod().getMethodName();

        // Add DataProvider parameters if available
        Object[] params = result.getParameters();
        if (params.length > 0) {
            testName += " - " + Arrays.toString(params);
        }

        ExtentUtils.createTest(testName);

        // Add author if annotation exists
        Author author = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Author.class);
        if (author != null) {
            ExtentUtils.addAuthors(author.name());
        }
    }
    @Override
    public void onFinish(ITestContext context) {

        try {
            ExtentUtils.flushReports();
        }
        catch (Exception e) {

            throw new RuntimeException();
        }

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentUtils.getTest().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            String path = ScreenshotUtils.takeScreenshot(result);
            ExtentUtils.addScreenshotFromPath(path);
            ExtentUtils.getTest().fail(result.getThrowable());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}
