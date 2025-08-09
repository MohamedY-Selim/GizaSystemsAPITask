package io.swagger.petstore.base;

import io.restassured.response.Response;
import io.swagger.petstore.factory.ApiFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.util.Arrays;

public class BaseTest {

    protected static final String ANSI_GREEN_BG = "\u001B[42m";
    protected static final String ANSI_RED_BG   = "\u001B[41m";
    protected static final String ANSI_BLACK    = "\u001B[30m";
    protected static final String ANSI_RESET    = "\u001B[0m";

    @BeforeClass
    public void setup() {
        ApiFactory.getApiClient();
    }

    @AfterClass
    public void tearDown() {
        ApiFactory.resetApiClient();
    }

    @AfterMethod(alwaysRun = true)
    public void logResult(ITestResult result) {
        String name = result.getMethod().getMethodName();
        boolean passed = result.getStatus() == ITestResult.SUCCESS;
        if (passed) {
            System.out.println(ANSI_GREEN_BG + ANSI_BLACK + " " + name + " PASSED " + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED_BG + ANSI_BLACK + " " + name + " FAILED " + ANSI_RESET);
        }
    }

    protected int getStatusCode(Response response) {
        return response.getStatusCode();
    }

    protected void assertStatus(Response resp, int... expectedStatuses) {
        int code = resp.getStatusCode();
        boolean ok = Arrays.stream(expectedStatuses).anyMatch(s -> s == code);
        if (!ok) {
            System.out.println(ANSI_RED_BG + ANSI_BLACK +
                    " Unexpected status: " + code + " expected " + Arrays.toString(expectedStatuses) + " " +
                    ANSI_RESET);
        }
        org.testng.Assert.assertTrue(ok,
                "Unexpected status: " + code + " (expected " + Arrays.toString(expectedStatuses) + ")");
    }
}