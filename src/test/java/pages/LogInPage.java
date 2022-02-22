package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.testng.Assert;
import utilities.BasePage;

public class LogInPage extends BasePage
{
    //Constructor
    public LogInPage(AppiumDriver driver)
    {
        super(driver);
    }

    //Elements Locators
    By emailField = By.xpath("//android.widget.EditText[contains(@text, 'Email')]");
    By passwordField = By.xpath("//android.widget.EditText[contains(@text, 'Password')]");
    By logInSaveButton = By.xpath("//android.widget.TextView[contains(@text, 'LOGIN')]");
    By successText = By.id("android:id/message");
    By oKButton = By.xpath("//android.widget.Button[contains(@text, 'OK')]");

    //Actions
    public void enterEmail(String Email)
    {
        waitForElement(emailField);
        click(emailField);
        write(Email, emailField);
        driver.hideKeyboard();
    }

    public void enterPassword(String Password)
    {
        waitForElement(passwordField);
        click(passwordField);
        write(Password, passwordField);
        driver.hideKeyboard();
    }

    public void clickLogInSave()
    {
        waitForElement(logInSaveButton);
        click(logInSaveButton);
    }

    public boolean isLogInSuccessfullyDisplayed()
    {
        try
        {
            waitForElement(successText);
            String alertMessage = getText(successText);
            Assert.assertTrue(alertMessage.contains("You are logged in"));
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
