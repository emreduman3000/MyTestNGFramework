package com.techproed.smoketest;

import com.techproed.pages.FaceLoginPage;
import com.techproed.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FaceLoginPageTest extends TestBase {

    @Test
    public void faceLoginTest()
    {
        driver.get("https://www.facebook.com/");

        FaceLoginPage faceLoginPage = new FaceLoginPage(driver);

        faceLoginPage.email.sendKeys("fakeusername");
        faceLoginPage.password.sendKeys("fakepassword");
        faceLoginPage.loginButton.click();

        WebDriverWait webDriverWait=new WebDriverWait(driver,10);

        /*
        WebElement error= webDriverWait.until(ExpectedConditions.visibilityOf(faceLoginPage.error_message));
        Assert.assertTrue(error.isDisplayed());
        */


        WebElement errorMessage= webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='loginform']/div[2]/div[2]")));
        Assert.assertTrue(errorMessage.isDisplayed());

        //Assert.assertTrue(faceLoginPage.error_message.isDisplayed());//wait gerekmiyorsa bu tek basına yeterli

    }


}
