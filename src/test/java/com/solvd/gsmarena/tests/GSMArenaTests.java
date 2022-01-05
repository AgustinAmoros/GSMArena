package com.solvd.gsmarena.tests;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.solvd.gsmarena.HomePage;
import com.solvd.gsmarena.SignUpPage;
import com.solvd.gsmarena.services.ISearchService;
import com.solvd.gsmarena.services.ISignUpService;
import com.solvd.gsmarena.services.ILogInService;
import io.cucumber.java.ja.しかし;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class GSMArenaTests implements IAbstractTest, ISignUpService, ILogInService, ISearchService {

   public WebDriver driver;
   @BeforeTest
           public void setUp(){
        System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");
       driver = new ChromeDriver();
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @DataProvider(name = "dpValidCredentials")
    public Object[][] dp1(){
        return new Object[][] {{"aaabbbccc1234","manzanaroja1234"}, {"ganchoperrojueves1","carlosgardel666"}};
    }

    @DataProvider(name="dpInvalidPasswords")
    public Object[][] dp2(){
        return new Object[][] {{"aaabbbccc1234","man"}, {"ganchoperrojueves1","carlosgardel666777888999"},{"papafritasalame",""}};
    }

    @DataProvider(name="dpInvalidNicknames") //Nicknames longer than 20 characters won't be fully typed, only its first 20 characters.
    public Object[][] dp3(){
        return new Object[][] {{"g","carlosgardel666"},{"","bicicletagod123"}};
    }

    @DataProvider(name = "dpSearch1")
    public  Object[][] dpSearch1(){
        return new Object[][] {{"Samsung"},{"Apple"},{"Xiaomi"},{"iPhone 13"},{"A52s"},{"One Vision Plus"}};
    }

    @DataProvider(name = "dpSearchInvalid")
    public  Object[][] dpSearch2(){
        return new Object[][] {{"ASDasdasd"},{"54576887"},{"./=-"}};
    }




    @Test
    public void verifyHomePageWorkflow(){
        SignUpPage signUpPage = goToSignUp(driver);
        assertEquals(signUpPage.getSignUpMessage(), "Sign Up", "FATAL!, Not same");
    }

    @Test(dataProvider = "dp1")
    public void verifyEmptyEmailsNotAllowed(String nickname,String password){
       SignUpPage signUpPage = signUpWithInvalidEmail(driver,nickname,password,"");
       assertEquals(signUpPage.isSubmitButtonClickable(), false, "FATAL!, Not same");

    }

    @Test(dataProvider = "dp1")
    public void verifyInvalidEmailsNotAllowed(String nickname,String password){
        SignUpPage signUpPage = signUpWithInvalidEmail(driver,nickname,password,"ABCDEFG.com");
        assertEquals(signUpPage.isSubmitButtonClickable(), false, "FATAL!, Not same");

    }

    @Test(dataProvider = "dpInvalidNicknames")
    public void verifyInvalidNicknameIsNotAllowed(String nickname, String password){
        SignUpPage signUpPage = signUpWithInvalidCredentials(driver,nickname,password);
        if(signUpPage.isSubmitButtonClickable()==true) {
            signUpPage.clickSubmitButton();
            assertEquals(signUpPage.getOperationFailedMessage(), "The operation failed.", "FATAL!, Not same");
        }
        else
        {
            assertEquals(signUpPage.isSubmitButtonClickable(), false, "FATAL!, Not same");

        }    }

    @Test(dataProvider="dpInvalidPasswords")
    public void verifyInvalidPasswordIsNotAllowed(String nickname, String password){
        SignUpPage signUpPage = signUpWithInvalidCredentials(driver,nickname,password);
        if(signUpPage.isSubmitButtonClickable()==true) {
            signUpPage.clickSubmitButton();
            assertEquals(signUpPage.getOperationFailedMessage(), "The operation failed.", "FATAL!, Not same");
        }
        else
        {
            assertEquals(signUpPage.isSubmitButtonClickable(), false, "FATAL!, Not same");

        }
        }


    @Test(dataProvider="dpValidCredentials")
    public void verifyConditionsAreRequired(String nickname,String password){
       SignUpPage signUpPage = signUpWithoutConditions(driver,nickname,password);
       assertEquals(signUpPage.isSubmitButtonClickable(), false, "FATAL!, Not same");

    }

    @Test
    public void verifySignUpWithUsedCredentials(){
        SignUpPage signUpPage = signUpWithValidCredentials(driver);
        assertEquals(signUpPage.getAccountCreatedMessage(), "The operation failed.", "FATAL!, Not same");

    }


    @Test(dataProvider="dpValidCredentials")
    public void verifyLogInWorkFlow(String nickname, String password){
       HomePage homePage = goToLogIn(driver,nickname,password);
        assertEquals(homePage.getLogInMessage(), "Login", "FATAL!, Not same");

    }

    @Test(dataProvider = "dpValidCredentials")
    public void verifyLogInWithInvalidCombination(String nickname, String password){
       HomePage homePage = logInWithInvalidCombination(driver, nickname, password);
        assertEquals(homePage.getLogInFailedMessage(), "Login failed.", "FATAL!, Not same");
    }

    @Test(dataProvider = "dpValidCredentials")
    public void verifyLogInWithEmptyEmail(String nickname, String password){
        HomePage homePage = logInWithEmptyEmail(driver, nickname, password);
        assertEquals(homePage.isLogInSuccesfullMessageVisible(), false, "FATAL!, Not same");
   }

    @Test(dataProvider = "dpValidCredentials")
    public void verifyLogInWithEmptyPassword(String nickname, String password){
        HomePage homePage = logInWithEmptyPassword(driver, nickname, password);
        assertEquals(homePage.isLogInSuccesfullMessageVisible(), false, "FATAL!, Not same");
    }

    @Test
    public void verifyLogInWithValidCredentials(){
        HomePage homePage = logInWithRealCredentials(driver);
        assertEquals(homePage.getLogInSuccessfullMessage(), "Login successful.", "FATAL!, Not same");
   }

   @Test(dataProvider = "dpSearch1")
    public void verifyQuickSearchWorkFlow(String searchQuery){
       HomePage homePage = quickSearch(driver,searchQuery);
       assertEquals(homePage.getProductName().contains(searchQuery),true , "FATAL!, Not same");

   }

    @Test(dataProvider = "dpSearch1")
    public void verifyFullSearchWorkFlow(String searchQuery){
        HomePage homePage = fullSearch(driver,searchQuery);
        assertEquals(homePage.getProductName().contains(searchQuery),true , "FATAL!, Not same");

    }

    @Test(dataProvider = "dpSearchInvalid")
    public void verifyNoResultsSearch(String searchQuery){
       HomePage homePage = displayFullSearchResults(driver,searchQuery);
       assertEquals(homePage.getNoResultsMessage(), "We're sorry, we found no results", "FATAL!, Not same");

    }
}
