package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SignUpPage;
import utilities.BaseTest;
import java.io.IOException;

public class SignUpUser extends BaseTest
{
    @BeforeMethod
    public void setUpTest() throws IOException
    {
        getProperties();
        createExtentReport();
        openApp();
    }

    @Test(retryAnalyzer = utilities.RetryTest.class)
    public void signUp() throws IOException
    {
        try
        {
            extentTest = extent.createTest("Sign Up");

            SignUpPage signUp = new SignUpPage(driver);
            signUp.clickMenuOption("Login");
            signUp.clickSignUp();
            signUp.enterEmail(prop.getProperty("email"));
            signUp.enterPassword(prop.getProperty("password"));
            signUp.enterConfirmPassword(prop.getProperty("password"));
            signUp.clickSignUpSave();

            if(signUp.isSignUpSuccessfullyDisplayed())
            {
                screenFile = screenShot("SignUp");
                extentTest.pass("Passed").addScreenCaptureFromPath(screenFile);
                signUp.clickOK();
            }
            else
            {
                screenFile = screenShot("SignUp");
                extentTest.fail("Failed").addScreenCaptureFromPath(screenFile);
                signUp.clickOK();
            }
        }

        catch(Exception ex)
        {
            screenFile = screenShot("SignUp");
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
