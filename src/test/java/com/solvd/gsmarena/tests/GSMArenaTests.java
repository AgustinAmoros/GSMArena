package com.solvd.gsmarena.tests;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.solvd.gsmarena.SignUpPage;
import com.solvd.gsmarena.services.ISignUpService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GSMArenaTests implements IAbstractTest, ISignUpService {

   public WebDriver driver;
   @BeforeTest
           public void setUp(){
        System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");
       driver = new ChromeDriver();
       driver.get("http://www.gsmarena.com");
    }

    @Test
    public void verifyHomePageWorkflow(){
        SignUpPage signUpPage = goToSignUp(driver);
        assertEquals(signUpPage.getSignUpMessage(), "Sign Up", "FATAL!, Not same");
    }
}
