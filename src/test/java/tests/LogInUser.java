package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LogInPage;
import pages.SignUpPage;
import sun.rmi.runtime.Log;
import utilities.BaseTest;

import java.io.IOException;

public class LogInUser extends BaseTest
{
    @BeforeMethod
    public void setUpTest() throws IOException
    {
        getProperties();
        createExtentReport();
        openApp();
    }

    @Test(retryAnalyzer = utilities.RetryTest.class)
    public void logIn() throws IOException
    {
        try
        {
            extentTest = extent.createTest("Log In");

            LogInPage logIn = new LogInPage(driver);
            logIn.clickMenuOption("Login");
            logIn.enterEmail(prop.getProperty("email"));
            logIn.enterPassword(prop.getProperty("password"));
            logIn.clickLogInSave();

            if(logIn.isLogInSuccessfullyDisplayed())
            {
                screenFile = screenShot("LogIn");
                extentTest.pass("Passed").addScreenCaptureFromPath(screenFile);
                logIn.clickOK();
            }
            else
            {
                screenFile = screenShot("LogIn");
                extentTest.fail("Failed").addScreenCaptureFromPath(screenFile);
                logIn.clickOK();
            }
        }

        catch(Exception ex)
        {
            screenFile = screenShot("LogIn");
            extentTest.fail(ex.getMessage()).addScreenCaptureFromPath(screenFile);
            ex.printStackTrace();
            throw(ex);
        }
    }

    @AfterMethod
    public void closeTest()
    {
        closeApp();
    }
}
