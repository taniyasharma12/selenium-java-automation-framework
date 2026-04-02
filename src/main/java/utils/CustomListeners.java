package utils;

import annotations.Author;
import org.testng.*;
import org.testng.annotations.ITestAnnotation;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

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
    public void onFinish(ITestContext context) {

        try {
            ExtentUtils.flushReports();
        }
        catch (Exception e) {

            throw new RuntimeException();
        }

    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentUtils.createTest(result.getMethod().getMethodName());
        ExtentUtils.addAuthors(result.getMethod().getConstructorOrMethod().getMethod()
                .getAnnotation(Author.class).name());
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        try {
            ScreenshotUtils.takeScreenshot(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            ScreenshotUtils.takeScreenshot(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}
