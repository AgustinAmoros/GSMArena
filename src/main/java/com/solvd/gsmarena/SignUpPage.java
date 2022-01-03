package com.solvd.gsmarena;


import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import groovy.lang.Newify;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class SignUpPage extends AbstractPage {

    @FindBy(xpath="//*[@id=\"body\"]/div/div[1]/div/div/div[2]/div/h1")
    private ExtendedWebElement signUpMessage;

    @FindBy(xpath="//*[@id=\"uname\"]")
    private ExtendedWebElement nicknameField;

    @FindBy(xpath="//*[@id=\"email\"]")
    private ExtendedWebElement emailField;

    @FindBy(xpath="//*[@id=\"upass\"]")
    private ExtendedWebElement passwordField;

    @FindBy(xpath="//*[@id=\"frmOpin\"]/fieldset[2]/div[1]/label")
    private ExtendedWebElement iAgreeConditionButton;

    @FindBy(xpath="//*[@id=\"frmOpin\"]/fieldset[2]/div[2]/label")
    private ExtendedWebElement iAmSixteenConditionButton;

    @FindBy(xpath="//*[@id=\"nick-submit\"]")
    private ExtendedWebElement submitButton;



    public SignUpPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getSignUpMessage(){
        return signUpMessage.getText();
    }
    public void setNicknameField(String nickname){
        Actions action = new Actions(driver);
        action.sendKeys(Keys.PAGE_DOWN).build().perform();

     nicknameField.type(nickname);
     nicknameField.sendKeys(Keys.TAB);
    }

    public void setEmailField(String email){
//     WebDriverWait wait =new WebDriverWait(driver, 5);
//     wait.until(ExpectedConditions.elementToBeClickable(By.xpath("\"//*[@id=\\\"email\\\"]\"")));
        Actions action = new Actions(driver);
        action.sendKeys(email).build().perform();
        action.sendKeys(Keys.TAB).build().perform();
    }

    public void setPasswordField(String password){
        Actions action = new Actions(driver);
        action.sendKeys(password).build().perform();
    }

    public void clickIAgreeConditionButton(){
        iAgreeConditionButton.click();
    }

    public void clickIAmSixteenConditionButton(){
        iAmSixteenConditionButton.click();
    }

    public boolean isSubmitButtonClickable()
    {
        try{
            WebDriverWait wait = new WebDriverWait(driver, 2);
            wait.until(ExpectedConditions.elementToBeClickable(submitButton.getElement()));
            return true;
        }
        catch (Exception e){
            return false;
        }

    }


}
