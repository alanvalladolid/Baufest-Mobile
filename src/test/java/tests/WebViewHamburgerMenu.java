package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.WebViewPage;
import utilities.BaseTest;

import java.io.IOException;

public class WebViewHamburgerMenu extends BaseTest
{
    @BeforeMethod
    public void setUpTest() throws IOException
    {
        getProperties();
        createExtentReport();
        openApp();
    }

    @Test(retryAnalyzer = utilities.RetryTest.class)
    public void webViewHM() throws IOException
    {
        try
        {
            extentTest = extent.createTest("WebView Hamburger Menu");

            WebViewPage webView = new WebViewPage(driver);
            webView.clickMenuOption("WebView");
            webView.clickHamburgerMenu();

            webView.selectHamburgerMenuOptions("Docs");
            screenFile = screenShot("Docs");
            extentStep = extentTest.createNode("Docs");
            extentStep.pass("Passed").addScreenCaptureFromPath(screenFile);

            webView.clickHamburgerMenu();
            webView.selectHamburgerMenuOptions("API");
            screenFile = screenShot("API");
            extentStep = extentTest.createNode("API");
            extentStep.pass("Passed").addScreenCaptureFromPath(screenFile);

            webView.clickHamburgerMenu();
            webView.selectHamburgerMenuOptions("Blog");
            screenFile = screenShot("Blog");
            extentStep = extentTest.createNode("Blog");
            extentStep.pass("Passed").addScreenCaptureFromPath(screenFile);

            webView.clickHamburgerMenu();
            webView.selectHamburgerMenuOptions("Contribute");
            screenFile = screenShot("Contribute");
            extentStep = extentTest.createNode("Contribute");
            extentStep.pass("Passed").addScreenCaptureFromPath(screenFile);

            webView.clickHamburgerMenu();
            webView.selectHamburgerMenuOptions("Community");
            screenFile = screenShot("Community");
            extentStep = extentTest.createNode("Community");
            extentStep.pass("Passed").addScreenCaptureFromPath(screenFile);

            webView.clickHamburgerMenu();
            webView.selectHamburgerMenuOptions("v7");
            screenFile = screenShot("v7");
            extentStep = extentTest.createNode("v7");
            extentStep.pass("Passed").addScreenCaptureFromPath(screenFile);
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
