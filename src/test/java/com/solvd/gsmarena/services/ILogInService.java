package com.solvd.gsmarena.services;
import org.openqa.selenium.WebDriver;
import com.solvd.gsmarena.HomePage;
import com.solvd.gsmarena.SignUpPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import java.util.concurrent.TimeUnit;

public interface ILogInService {

    default HomePage goToLogIn(WebDriver driver, String nickname, String password){
        HomePage homePage = new HomePage(driver);
        homePage.clickLogInMenuButton();
        return homePage;
    }

    default HomePage logInWithInvalidCombination(WebDriver driver, String nickname, String password){
        HomePage homePage = goToLogIn(driver,nickname,password);
        homePage.setEmailField("ABCDEFG@ZHGETSZ.com");
        homePage.setPasswordField(password);
        homePage.clickLogInButton();
        return homePage;
    }

    default HomePage logInWithEmptyEmail(WebDriver driver, String nickname, String password){
        HomePage homePage = goToLogIn(driver,nickname,password);
        homePage.setEmailField("");
        homePage.setPasswordField(password);
        homePage.clickLogInButton();
        return homePage;
    }
    default HomePage logInWithEmptyPassword(WebDriver driver, String nickname, String password){
        HomePage homePage = goToLogIn(driver,nickname,password);
        homePage.setEmailField("ABCDEFG@gmail.com");
        homePage.clickLogInButton();
        return homePage;
    }

    default HomePage logInWithRealCredentials(WebDriver driver){
        HomePage homePage = goToLogIn(driver,"dummyDummy","dummyDummy123");
        homePage.setEmailField("agusela2000@gmail.com");
        homePage.setPasswordField("Password123");
        homePage.clickLogInButton();
        return homePage;
    }
}
