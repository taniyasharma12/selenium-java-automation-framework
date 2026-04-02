package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    int retryCount = 0;
    int maxCount = Integer.parseInt(System.getProperty("retryCount", "0"));

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxCount) {
            retryCount++;
            System.out.println("Retrying: " + result.getName() + " attempt " + retryCount);
            return true;
        }
        return false;
    }
}