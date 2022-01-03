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

    @DataProvider(name = "dp1")
    public Object[][] dp1(){
        return new Object[][] {{"aaabbbccc1234","manzanaroja1234"}, {"ganchoperrojueves123","carlosgardel666"}};
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
    public void verifyInvalidEmailsNotAllowed(String nickname,String password){
       SignUpPage signUpPage = signUpWithInvalidEmail(driver,nickname,password,"ABCEDFG.com");
       assertEquals(signUpPage.isSubmitButtonClickable(), false, "FATAL!, Not same");

    }
}
