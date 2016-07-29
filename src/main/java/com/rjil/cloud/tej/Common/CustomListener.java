package com.rjil.cloud.tej.Common;

import com.rjil.cloud.tej.Common.logging.FrameworkLogger;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * Custom Listener to get test status
 */
public class CustomListener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult tr) {
        FrameworkLogger.logFail(tr.getName() + "--Test method failed\n");
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        FrameworkLogger.logWarning(tr.getName() + "--Test method skipped\n");
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        FrameworkLogger.logPass(tr.getName() + "--Test method success\n");
    }

}

