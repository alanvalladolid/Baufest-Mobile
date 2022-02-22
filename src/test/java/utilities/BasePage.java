package utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage
{
    public AppiumDriver<MobileElement> driver;
    public WebDriverWait wait;

    //Constructor
    public BasePage(AppiumDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 60);
    }

    //Functions
    public WebElement findElement(By locator)
    {
        return driver.findElement(locator);
    }

    public void click(By locator)
    {
        driver.findElement(locator).click();
    }

    public void write(String inputText, By locator)
    {
        driver.findElement(locator).sendKeys(inputText);
    }

    public String getText(By locator)
    {
        return driver.findElement(locator).getText();
    }

    public String getValue(By locator)
    {
        return driver.findElement(locator).getAttribute("content-desc");
    }

    public Boolean isDisplayed(By locator)
    {
        try
        {
            waitForElement(locator);
            return driver.findElement(locator).isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean waitForElement(By locator)
    {
        try
        {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            return true;
        }
        catch (TimeoutException e)
        {
            return false;
        }
    }

    public void clickMenuOption(String MenuOption)
    {
        waitForElement(By.xpath("//android.widget.TextView[contains(@text, '"+MenuOption+"')]"));
        click(By.xpath("//android.widget.TextView[contains(@text, '"+MenuOption+"')]"));
    }

    public boolean waitForLoading()
    {
        By loading = By.xpath("//android.widget.TextView[contains(@text, 'LOADING')]");

        try
        {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loading));
            return true;
        }
        catch (TimeoutException e)
        {
            return false;
        }
    }
}
