package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.testng.Assert;
import utilities.BasePage;

public class SignUpPage extends BasePage
{
    //Constructor
    public SignUpPage(AppiumDriver driver)
    {
        super(driver);
    }

    //Elements Locators
    By signUpButton = By.xpath("//android.view.ViewGroup[@content-desc='button-sign-up-container']");
    By emailField = By.xpath("//android.widget.EditText[contains(@text, 'Email')]");
    By passwordField = By.xpath("//android.widget.EditText[contains(@text, 'Password')]");
    By confirmPasswordField = By.xpath("//android.widget.EditText[contains(@text, 'Confirm password')]");
    By signUpSaveButton = By.xpath("//android.widget.TextView[contains(@text, 'SIGN UP')]");
    By successText = By.id("android:id/message");
    By oKButton = By.xpath("//android.widget.Button[contains(@text, 'OK')]");

    //Actions
    public void clickSignUp()
    {
        waitForElement(signUpButton);
        click(signUpButton);
    }

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

    public void enterConfirmPassword(String Password)
    {
        waitForElement(confirmPasswordField);
        click(confirmPasswordField);
        write(Password, confirmPasswordField);
        driver.hideKeyboard();
    }

    public void clickSignUpSave()
    {
        waitForElement(signUpSaveButton);
        click(signUpSaveButton);
    }

    public boolean isSignUpSuccessfullyDisplayed()
    {
        try
        {
            waitForElement(successText);
            String alertMessage = getText(successText);
            Assert.assertTrue(alertMessage.contains("You successfully signed up"));
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
