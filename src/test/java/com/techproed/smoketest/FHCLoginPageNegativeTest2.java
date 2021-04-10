package com.techproed.smoketest;

import com.techproed.pages.FHCLoginPage;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FHCLoginPageNegativeTest2 extends TestBase
{
    @Test(groups="group test1")
    public  void gecersizUsername(){   //http://www.fhctrip.com/Account/Logon
        driver.get(ConfigReader.getProperty("fhc_login_url"));
        FHCLoginPage fhcLoginPage=new FHCLoginPage(driver);
        fhcLoginPage.username.sendKeys(ConfigReader.getProperty("unvalidUsername"));
        fhcLoginPage.password.sendKeys(ConfigReader.getProperty("validPassword"));
        fhcLoginPage.loginButton.click();

       //wait problemi varsa bunu kullanmaliyiz.
        // WebDriverWait wait = new WebDriverWait(driver,10);
        // WebElement tryAgain = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='Try again please']")));
        // Assert.assertTrue(tryAgain.isDisplayed());

        Assert.assertTrue(fhcLoginPage.error_mesaj.getText().contains(ConfigReader.getProperty("login_error_message")));
        //Assert.assertTrue(fhcLoginPage.error_mesaj.isDisplayed());
    }
    @Test
    public void gecersizPassword(){
        driver.get(ConfigReader.getProperty("fhc_login_url"));
        FHCLoginPage fhcLoginPage = new FHCLoginPage(driver);
        fhcLoginPage.username.sendKeys(ConfigReader.getProperty("unvalidUsername"));
        fhcLoginPage.password.sendKeys(ConfigReader.getProperty("unvalidPassword"));
        fhcLoginPage.loginButton.click();
        Assert.assertTrue(fhcLoginPage.error_mesaj.isDisplayed());
    }
    @Test
    public  void gecersizUsernamePass(){
        driver.get("http://fhctrip-qa.com/Account/Logon");
        FHCLoginPage fhcLoginPage = new FHCLoginPage(driver);
        fhcLoginPage.username.sendKeys("manage");
        fhcLoginPage.password.sendKeys("manager12!");
        fhcLoginPage.loginButton.click();
        Assert.assertTrue(fhcLoginPage.error_mesaj.getText().contains("Try again please"));
    }

}
