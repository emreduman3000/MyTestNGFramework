package com.techproed.smoketest;

import com.techproed.pages.GlbHomePage;
import com.techproed.pages.GlbSignUpPage;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GlbSignUptest
{
    GlbSignUpPage glbSignUpPage=new GlbSignUpPage();
    GlbHomePage glbHomePage=new GlbHomePage();


    @Test
    public void signUpTest() throws InterruptedException {
        //Driver.getDriver().get(ConfigReader.getProperty("glb_signup_url"));
        Driver.getDriver().get(ConfigReader.getProperty("glb_url"));
        glbHomePage.joinNowLink.click();

        glbSignUpPage.email.sendKeys(ConfigReader.getProperty("TestEmail"));
        glbSignUpPage.name.sendKeys(ConfigReader.getProperty("TestUserName"));
        glbSignUpPage.phone.sendKeys(ConfigReader.getProperty("TestPhone"));
        glbSignUpPage.password.sendKeys(ConfigReader.getProperty("TestPassword"));
        glbSignUpPage.repassword.sendKeys(ConfigReader.getProperty("TestPassword"));
        glbSignUpPage.signUpButton.click();

        Thread.sleep(3000);
        Assert.assertTrue(glbSignUpPage.successMessage.getText().equals("Success! !"));

        System.out.println("SUCCESS MESAJ : "+ glbSignUpPage.successMessage.getText());

        //Test fail olacak cunku actual Success! ! fakat expected Success!
        Driver.closeDriver();

    }

}

