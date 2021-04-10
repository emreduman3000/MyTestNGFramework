package com.techproed.tests;

import com.techproed.pages.FHCLoginPage;
import com.techproed.pages.GlbSignInPage;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import com.techproed.utilities.TestBaseFinal;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ExtentReports2 extends TestBaseFinal
{
    @Test
    public void negativeTest()
    {
        //ollustruulan dosyanın path'ini bulmak için TestBaseFinal!a git
        //String filePath = System.getProperty("user.dir") + "/test-output/myprojectreport.html";//create a custom report in the current project.

        //FHCLoginPageNegativeTest2 clasından gecersizUsernamePass() methodunu aldık.
        //hocada negativeTest diye kayıtlı

        extentTest=extentReports.createTest("FHC Login Test","FHC Login function's Test");
        extentTest.info("go to url");
        Driver.getDriver().get(ConfigReader.getProperty("fhc_login_url"));
        FHCLoginPage fhcLoginPage = new FHCLoginPage(Driver.getDriver());

        extentTest.info("send unvalid username");
        fhcLoginPage.username.sendKeys(ConfigReader.getProperty("unvalidUsername"));

        extentTest.info("send unvaid pasword");
        fhcLoginPage.password.sendKeys(ConfigReader.getProperty("unvalidPassword"));

        extentTest.info("click login page");
        fhcLoginPage.loginButton.click();

        extentTest.info("assertion is done");
        Assert.assertTrue(fhcLoginPage.error_mesaj.getText().contains("Try again please"));
        extentTest.pass("this test is successfully tested and passed!");

        Driver.closeDriver();
        extentTest.info("driver was closed!");
    }

}

//test ilk seferde test oldu ve file olusturuldu
//        String filePath = System.getProperty("user.dir") + "/test-output/myprojectreport.html";//create a custom report in the current project.
//        Assert.assertFalse(fhcLoginPage.error_mesaj.getText().contains("Try again please"));
//        dedim ve fail oldu ve screenshot aldı kaydettti
// assertTrue() dersem tekrardan passed olur