package com.techproed.smoketest;

import com.techproed.pages.FHCLoginPage;
import com.techproed.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FHCLoginPageNegativeTest extends TestBase {

    @Test
    public  void unvalidUsername()
    {
        driver.get("http://fhctrip-qa.com/Account/Logon");
        FHCLoginPage fhcLoginPage=new FHCLoginPage(driver);
        fhcLoginPage.username.sendKeys("manager");
        fhcLoginPage.password.sendKeys("Man1ager2!");
        fhcLoginPage.loginButton.click();

        /*
        //wait problemi varsa bunu kullanmaliyiz.
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement tryAgain = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='Try again please']")));
        Assert.assertTrue(tryAgain.isDisplayed());
        System.out.println(tryAgain.getText());
        */

        Assert.assertTrue(fhcLoginPage.error_mesaj.getText().contains("Try again please"));
        Assert.assertTrue(fhcLoginPage.error_mesaj.isDisplayed());
        System.out.println(fhcLoginPage.error_mesaj.getText());
    }

    @Test
    public void unvalidPassword()
    {
        driver.get("http://fhctrip-qa.com/Account/Logon");
        FHCLoginPage fhcLoginPage=new FHCLoginPage(driver);
        fhcLoginPage.username.sendKeys("managerrr");
        fhcLoginPage.password.sendKeys("Man1ager2!");
        fhcLoginPage.loginButton.click();

        Assert.assertTrue(fhcLoginPage.error_mesaj.isDisplayed());
        System.out.println(fhcLoginPage.error_mesaj.getText());
        //Try again please
    }

    @Test
    public void unvalidUsernamePassword()
    {
        driver.get("http://fhctrip-qa.com/Account/Logon");
        FHCLoginPage fhcLoginPage=new FHCLoginPage(driver);
        fhcLoginPage.username.sendKeys("managerrrr");
        fhcLoginPage.password.sendKeys("Man1ager22!");
        fhcLoginPage.loginButton.click();


        Assert.assertTrue(fhcLoginPage.error_mesaj.isDisplayed());
        System.out.println(fhcLoginPage.error_mesaj.getText());
        //Try again please
    }
}
