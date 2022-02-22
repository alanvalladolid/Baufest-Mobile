package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.testng.Assert;
import utilities.BasePage;

public class WebViewPage extends BasePage
{
    //Constructor
    public WebViewPage(AppiumDriver driver)
    {
        super(driver);
    }

    //Elements Locators
    By searchButton = By.xpath("//android.widget.Button[contains(@text, 'Search')]");
    By searchField = By.xpath("//android.widget.EditText");
    By hamburgerMenuButton = By.xpath("//android.widget.Button[contains(@text, 'Navigation bar toggle')]");

    //Actions
    public void clickSearch()
    {
        waitForLoading();
        waitForElement(searchButton);
        click(searchButton);
    }

    public void enterSearchText(String Search)
    {
        waitForElement(searchField);
        click(searchField);
        write(Search, searchField);
        driver.hideKeyboard();
    }

    public boolean isSearchResultDisplayed(String Search)
    {
        try
        {
            By searchResult = By.xpath("//android.view.View[contains(@content-desc,'"+ Search +"')]");

            waitForElement(searchResult);
            String searchText = getValue(searchResult);
            Assert.assertTrue(searchText.contains(Search));
            return true;
        }
        catch (AssertionError Ex)
        {
            return false;
        }
    }

    public void clickHamburgerMenu()
    {
        waitForLoading();
        waitForElement(hamburgerMenuButton);
        click(hamburgerMenuButton);
    }

    public void selectHamburgerMenuOptions(String HMOption)
    {
        By hmOpt = By.xpath("//android.widget.TextView[contains(@text,'"+HMOption+"')]");

        waitForElement(hmOpt);
        click(hmOpt);

        waitForElement(By.xpath("//android.view.View"));
    }
}
