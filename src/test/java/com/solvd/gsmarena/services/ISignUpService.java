package com.solvd.gsmarena.services;
import org.openqa.selenium.WebDriver;
import com.solvd.gsmarena.HomePage;
import com.solvd.gsmarena.SignUpPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import java.util.concurrent.TimeUnit;


public interface ISignUpService {





    default SignUpPage goToSignUp(WebDriver driver){
        HomePage homePage = new HomePage(driver);
        SignUpPage signUp = homePage.clickSignUpButton();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        return new SignUpPage(driver);
    }

    default SignUpPage signUpWithInvalidEmail(WebDriver driver,String nickname,String password,String email)
    {
        SignUpPage signUpPage = goToSignUp(driver);
        signUpPage.setNicknameField(nickname);
        signUpPage.setEmailField(email);
        signUpPage.setPasswordField(password);
        signUpPage.clickIAgreeConditionButton();
        signUpPage.clickIAmSixteenConditionButton();
        return signUpPage;
    }


    default SignUpPage signUpWithInvalidCredentials(WebDriver driver,String nickname,String password)
    {
        SignUpPage signUpPage = goToSignUp(driver);
        signUpPage.setNicknameField(nickname);
        signUpPage.setEmailField("ABCDEFG@gmail.com");
        signUpPage.setPasswordField(password);
        signUpPage.clickIAgreeConditionButton();
        signUpPage.clickIAmSixteenConditionButton();
        signUpPage.clickSubmitButton();
        return signUpPage;

    }


    default SignUpPage signUpWithoutConditions(WebDriver driver,String nickname,String password)
    {
        SignUpPage signUpPage = goToSignUp(driver);
        signUpPage.setNicknameField(nickname);
        signUpPage.setEmailField("ABCDEFG@gmail.com");
        signUpPage.setPasswordField(password);
        return signUpPage;

    }



}
