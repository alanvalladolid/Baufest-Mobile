package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.FormsPage;
import pages.WebViewPage;
import utilities.BaseTest;

import java.io.IOException;

public class WebViewSearch extends BaseTest
{
    @BeforeMethod
    public void setUpTest() throws IOException
    {
        getProperties();
        createExtentReport();
        openApp();
    }

    @Test(retryAnalyzer = utilities.RetryTest.class)
    public void webViewTab() throws IOException
    {
        try
        {
            extentTest = extent.createTest("WebView Search");

            WebViewPage webview = new WebViewPage(driver);
            webview.clickMenuOption("WebView");
            webview.clickSearch();
            webview.enterSearchText(prop.getProperty("searchTxt"));

            if(webview.isSearchResultDisplayed(prop.getProperty("searchTxt")))
            {
                screenFile = screenShot("WebView");
                extentTest.pass("Passed").addScreenCaptureFromPath(screenFile);
            }
            else
            {
                screenFile = screenShot("WebView");
                extentTest.fail("Failed").addScreenCaptureFromPath(screenFile);
            }
        }

        catch(Exception ex)
        {
            screenFile = screenShot("WebView");
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
