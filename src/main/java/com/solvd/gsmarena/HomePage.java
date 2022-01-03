package com.solvd.gsmarena;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

    public HomePage(WebDriver driver) {
        super(driver);
        open();
    }

    @FindBy(xpath = "//*[@id=\"social-connect\"]/a[8]/i")
    private ExtendedWebElement getSignUpButton;

    public SignUpPage clickSignUpButton() {
        getSignUpButton.click();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.PAGE_DOWN).build().perform();
        return new SignUpPage(driver);

    }


}
