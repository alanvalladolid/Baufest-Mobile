package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.testng.Assert;
import utilities.BasePage;

public class FormsPage extends BasePage
{
    //Constructor
    public FormsPage(AppiumDriver driver)
    {
        super(driver);
    }

    //Elements Locators
    By inputField = By.xpath("//android.widget.EditText[contains(@text, 'Type something')]");
    By switchButton = By.xpath("//android.widget.Switch[@content-desc='switch']");
    By dropDownButton = By.xpath("//android.widget.TextView[contains(@text, 'Dropdown')]");
    By activeButton = By.xpath("//android.widget.TextView[contains(@text, 'Active')]");
    By successText = By.id("android:id/message");
    By oKButton = By.xpath("//android.widget.Button[contains(@text, 'OK')]");

    //Actions
    public void enterInputfield(String InputField)
    {
        waitForElement(inputField);
        click(inputField);
        write(InputField, inputField);
        driver.hideKeyboard();
    }

    public void clickswitch()
    {
        waitForElement(switchButton);
        click(switchButton);
    }

    public void selectDropdownOptions(String DropDownOption)
    {
        waitForElement(dropDownButton);
        click(dropDownButton);
        waitForElement(By.xpath("//android.widget.CheckedTextView[contains(@text, '"+DropDownOption+"')]"));
        click(By.xpath("//android.widget.CheckedTextView[contains(@text, '"+DropDownOption+"')]"));
    }

    public void clickActive()
    {
        try
        {
            MobileElement activeButton = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector().description(\"Forms-screen\")).getChildByText("
                            + "new UiSelector().className(\"android.widget.TextView\"), \"Active\")"));
            activeButton.click();
        }
        catch (Exception e)
        {
            System.out.println("Cannot scroll further");
        }
    }

    public boolean isButtonActiveAlertDisplayed()
    {
        try
        {
            waitForElement(successText);
            String alertMessage = getText(successText);
            Assert.assertTrue(alertMessage.contains("This button is active"));
            return true;
        }
        catch (AssertionError Ex)
        {
            return false;
        }
    }

    public void clickOK()
    {
        waitForElement(oKButton);
        click(oKButton);
    }
}
