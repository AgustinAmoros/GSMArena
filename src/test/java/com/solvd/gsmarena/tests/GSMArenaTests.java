package com.solvd.gsmarena.tests;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.solvd.gsmarena.SignUpPage;
import com.solvd.gsmarena.services.ISignUpService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GSMArenaTests implements IAbstractTest, ISignUpService {

   public WebDriver driver;
   @BeforeTest
           public void setUp(){
        System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");
       driver = new ChromeDriver();
       driver.get("http://www.gsmarena.com");
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

//    @DataProvider(name = "dp2")
//    public  Object[][] dp2(){
//        return new Object[][] {{"dddeeefff1234"}, {"puertaventanamesa123"}};
//    }



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



}
