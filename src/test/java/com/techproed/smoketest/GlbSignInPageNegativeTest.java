package com.techproed.smoketest;

import com.techproed.pages.GlbSignInPage;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import org.testng.annotations.Test;

public class GlbSignInPageNegativeTest
{

    @Test(groups = "group test1")
    public void negativeTest(){
        Driver.getDriver().get(ConfigReader.getProperty("glb_signin_url"));
        GlbSignInPage glbSignInPage = new GlbSignInPage();
        glbSignInPage.email.sendKeys(ConfigReader.getProperty("unvalidEmail"));
        glbSignInPage.password.sendKeys(ConfigReader.getProperty("unvalidPassword"));
        glbSignInPage.loginButton.click();


    }

}
