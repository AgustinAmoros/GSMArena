package com.solvd.gsmarena.services;
import org.openqa.selenium.WebDriver;
import com.solvd.gsmarena.HomePage;
import com.solvd.gsmarena.SignUpPage;

import java.util.concurrent.TimeUnit;


public interface ISignUpService {

    default SignUpPage goToSignUp(WebDriver driver){
        HomePage homePage = new HomePage(driver);
        SignUpPage signUp = homePage.clickSignUpButton();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        return new SignUpPage(driver);
    }

}
