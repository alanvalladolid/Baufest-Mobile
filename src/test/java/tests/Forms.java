package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.FormsPage;
import utilities.BaseTest;

import java.io.IOException;

public class Forms extends BaseTest
{
    @BeforeMethod
    public void setUpTest() throws IOException
    {
        getProperties();
        createExtentReport();
        openApp();
    }

    @Test(retryAnalyzer = utilities.RetryTest.class)
    public void formsTab() throws IOException
    {
        try
        {
            extentTest = extent.createTest("Forms");

            FormsPage forms = new FormsPage(driver);
            forms.clickMenuOption("Forms");
            forms.enterInputfield(prop.getProperty("input"));
            forms.clickswitch();
            forms.selectDropdownOptions(prop.getProperty("dropdownOption"));
            forms.clickActive();

            if(forms.isButtonActiveAlertDisplayed())
            {
                screenFile = screenShot("Forms");
                extentTest.pass("Passed").addScreenCaptureFromPath(screenFile);
                forms.clickOK();
            }
            else
            {
                screenFile = screenShot("Forms");
                extentTest.fail("Failed").addScreenCaptureFromPath(screenFile);
                forms.clickOK();
            }
        }

        catch(Exception ex)
        {
            screenFile = screenShot("Forms");
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
