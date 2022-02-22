package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class BaseTest
{
    public AppiumDriver<MobileElement> driver;
    public String projectPath = System.getProperty("user.dir");
    public String myHomePath = System.getProperty("user.home");
    public Properties prop = new Properties();
    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest extentTest;
    public ExtentTest extentStep;
    public String screenFile;

    public void getProperties() throws IOException
    {
        InputStream input = new FileInputStream(projectPath + "/src/test/java/utilities/DataTest.properties");
        prop.load(input);
    }

    public AppiumDriver openApp() throws MalformedURLException
    {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 2");
        caps.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");
        caps.setCapability("automationName", "UIAutomator2");
        caps.setCapability("autoGrantPermissions", true);
        caps.setCapability("appPackage", "com.wdiodemoapp");
        caps.setCapability("appActivity", "com.wdiodemoapp.MainActivity");
        caps.setCapability(MobileCapabilityType.APP, myHomePath + "/Downloads/Android-NativeDemoApp-0.2.1.apk");

        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AppiumDriver<MobileElement>(url, caps);

        return driver;
    }

    public void closeApp()
    {
        driver.closeApp();
        driver.removeApp("com.wdiodemoapp");
        driver.quit();
        extent.flush();
    }

    public String screenShot(String filename) throws IOException
    {
        TakesScreenshot screenShotObj = ((TakesScreenshot) driver);
        File screenFileObj = screenShotObj.getScreenshotAs(OutputType.FILE);

        String destinationFile = projectPath + "/reports/screenshots/" + filename + ".png";
        File destinationFileObj = new File(destinationFile);

        FileUtils.copyFile(screenFileObj, destinationFileObj);

        return "screenshots/" + destinationFileObj.getName();
    }

    public ExtentReports getExtentObj()
    {
        if(extent == null)
        {
            createExtentReport();
        }
        return extent;
    }

    public ExtentReports createExtentReport()
    {
        htmlReporter = new ExtentHtmlReporter(projectPath + "/reports/BaufestMobile.html");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setDocumentTitle("Automation Reports");
        htmlReporter.config().setReportName("Automation Test Results: Baufest Mobile");
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.setAppendExisting(true);

        extent = new ExtentReports();
        extent.setSystemInfo("Company", "Baufest");
        extent.setSystemInfo("Device", "Pixel 2");
        extent.setSystemInfo("OS", "Android 9");
        extent.attachReporter(htmlReporter);

        return extent;
    }
}

