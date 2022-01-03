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

    @FindBy(xpath="/html/body/div[1]/div[1]/div[2]/div/div[3]/form/fieldset[1]/input[1]")
    private ExtendedWebElement nicknameField;

    @FindBy(xpath="/html/body/div[1]/div[1]/div[2]/div/div[3]/form/fieldset[1]/input[2]")
    private ExtendedWebElement emailField;

    @FindBy(xpath="/html/body/div[1]/div[1]/div[2]/div/div[3]/form/input")
    private ExtendedWebElement passwordField;

    @FindBy(xpath="//*[@id=\"frmOpin\"]/fieldset[2]/div[1]/label")
    private ExtendedWebElement iAgreeConditionButton;

    @FindBy(xpath="//*[@id=\"frmOpin\"]/fieldset[2]/div[2]/label")
    private ExtendedWebElement iAmSixteenConditionButton;

    @FindBy(xpath="/html/body/div[1]/div[1]/div[2]/div/div[3]/form/div/input")
    private ExtendedWebElement submitButton;

    @FindBy(xpath="//*[@id=\"body\"]/div/div[2]/h3")
    private ExtendedWebElement operationFailedMessage;


    public SignUpPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getSignUpMessage(){
        return signUpMessage.getText();
    }
    public void setNicknameField(String nickname){

     nicknameField.type(nickname);
    }

    public void setEmailField(String email){


        emailField.type(email);
    }

    public void setPasswordField(String password){
    passwordField.type(password);
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

    public void clickSubmitButton(){
       if(this.isSubmitButtonClickable()==true) { submitButton.click();}

    }

    public String getOperationFailedMessage(){
        return operationFailedMessage.getText();
    }
}
