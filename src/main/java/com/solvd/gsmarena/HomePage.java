package com.solvd.gsmarena;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.apache.poi.ss.usermodel.ExcelGeneralNumberFormat;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends AbstractPage {

    public HomePage(WebDriver driver) {
        super(driver);
        open();
    }

    @FindBy(xpath = "//*[@id=\"social-connect\"]/a[8]/i")
    private ExtendedWebElement getSignUpButton;

    @FindBy(xpath="//*[@id=\"login-active\"]/i")
    private ExtendedWebElement getLogInMenuButton;

    @FindBy(xpath="/html/body/header/div/div/div[3]/span/form/p")
    private ExtendedWebElement logInMessage;

    @FindBy(xpath="/html/body/header/div/div/div[3]/span/form/input[2]")
    private ExtendedWebElement emailField;

    @FindBy(xpath="/html/body/header/div/div/div[3]/span/form/input[3]")
    private ExtendedWebElement passwordField;

    @FindBy(xpath="/html/body/header/div/div/div[3]/span/form/input[4]")
    private ExtendedWebElement logInButton;

    @FindBy(xpath="/html/body/div[1]/div[1]/div[2]/div/div[2]/h3")
    private ExtendedWebElement logInFailedMessage;

    @FindBy(xpath="/html/body/header/div/div/div[3]/a[8]/i")
    private ExtendedWebElement logOutButton;

    @FindBy(xpath="/html/body/div[1]/div[1]/div[2]/div/div[2]/h3")
    private ExtendedWebElement logInSuccessfullMessage;

    @FindBy(xpath="/html/body/header/div/div/div[2]/form/input")
    private ExtendedWebElement searchField;

    @FindBy(xpath="/html/body/header/div/div/div[2]/form/div[2]/a[1]")
    private ExtendedWebElement goSearchButton;

    @FindBy(xpath="/html/body/div[1]/div[1]/div[2]/div/div[1]/div/div[1]/h1")
    private ExtendedWebElement productName;

    @FindBy(xpath="/html/body/div[1]/div[1]/div[2]/div/div[2]/h3")
    private ExtendedWebElement noResultsMessage;

    @FindBy(xpath="/html/body/div[1]/div[1]/div[2]/div/div[2]/div/ul/li[1]/a/strong/span")
    private ExtendedWebElement firstSearchResult;

    public SignUpPage clickSignUpButton() {
        getSignUpButton.click();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.PAGE_DOWN).build().perform();
        return new SignUpPage(driver);

    }

    public HomePage clickLogInMenuButton(){
        getLogInMenuButton.click();
        return this;
    }

    public String getLogInMessage(){
        WebDriverWait wait = new WebDriverWait(driver,3);
        wait.until(ExpectedConditions.visibilityOf(logInMessage.getElement()));
        return logInMessage.getText();
    }

    public void setEmailField(String email){
        emailField.type(email);
    }

    public void setPasswordField(String password){
        passwordField.type(password);
    }

    public void clickLogInButton(){
        logInButton.click();
    }

    public String getLogInFailedMessage(){
        return logInFailedMessage.getText();
    }
    public String getLogInSuccessfullMessage(){
        return logInSuccessfullMessage.getText();
    }

    public boolean isLogInSuccesfullMessageVisible(){
        try{
            WebDriverWait wait = new WebDriverWait(driver, 2);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[1]/div[2]/div/div[2]/h3")));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean isLogOutButtonClickable()
    {
        try{
            WebDriverWait wait = new WebDriverWait(driver, 2);
            wait.until(ExpectedConditions.elementToBeClickable(logOutButton.getElement()));
            return true;
        }
        catch (Exception e){
            return false;
        }

    }

    public void clickSearchField(){
        searchField.click();
    }

    public void setSearchField(String searchQuery){
        searchField.type(searchQuery);
    }

    public void clickGoSearchButton(){
        goSearchButton.click();
    }

    public String getProductName(){
        return productName.getText();
    }

    public String getNoResultsMessage(){
        return noResultsMessage.getText();
    }

    public void clickFirstSearchResult(){
        firstSearchResult.click();
    }
}
