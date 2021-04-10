package com.techproed.smoketest;

import com.techproed.pages.GlbHomePage;
import com.techproed.pages.GlbSignInPage;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import org.testng.Assert;
import org.testng.annotations.Test;

    //Global Trader Sign In Test.
    //Kullanici https://www.glbtrader.com/login.html sayfasina gitsin.
    //Sayfaya dogru ve yanlis veriler girerek sign in ozelligini test etsin.
    //pages  paketinin altina bir page class olusturun : GlbSignInPage
    //Page objelerini(webelement) bulun.
    //smoketest  paketinin altina iki tane test classi olusturun :
    //PositiveTestGlbSignIn
    //NegativeTestGlbSignIn

public class GlbSignInPagePositiveTest
{
    GlbSignInPage glbSignInPage = new GlbSignInPage();
    GlbHomePage glbHomePage=new GlbHomePage();
    @Test
    public void positiveTest()
    {
        Driver.getDriver().get(ConfigReader.getProperty("glb_signin_url"));

        //Driver.getDriver().get(ConfigReader.getProperty("glb_url"));
        //glbHomePage.signIn.click();

        glbSignInPage.email.sendKeys(ConfigReader.getProperty("TestEmail"));
        glbSignInPage.password.sendKeys(ConfigReader.getProperty("TestPassword"));
        glbSignInPage.loginButton.click();

        Assert.assertTrue(glbSignInPage.message.getText().equals("Sorry!"));
        Driver.closeDriver();//bunu diyerek static driver nesnesinin null yapıyoruz

    }
}
