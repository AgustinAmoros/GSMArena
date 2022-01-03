package com.solvd.gsmarena.services;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import com.solvd.gsmarena.HomePage;
import com.solvd.gsmarena.SignUpPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import java.util.concurrent.TimeUnit;

public interface ISearchService {

    default HomePage quickSearch(WebDriver driver,String searchQuery){
        HomePage homePage = new HomePage(driver);
        homePage.clickSearchField();
        homePage.setSearchField(searchQuery);
        driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).build().perform();
        action.sendKeys(Keys.ENTER).build().perform();
        return homePage;
    }

    default HomePage fullSearch(WebDriver driver, String searchQuery){
        HomePage homePage = new HomePage(driver);
        homePage.clickSearchField();
        homePage.setSearchField(searchQuery);
        homePage.clickGoSearchButton();
        driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.PAGE_DOWN).build().perform();
        homePage.clickFirstSearchResult();
        return homePage;

    }

    default HomePage displayFullSearchResults(WebDriver driver,String searchQuery){
        HomePage homePage= new HomePage(driver);
        homePage.clickSearchField();
        homePage.setSearchField(searchQuery);
        homePage.clickGoSearchButton();
        driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        return homePage;
    }



}
