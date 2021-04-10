package com.techproed.smoketest;

import com.techproed.pages.FHCLoginPage;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.TestBase;
import org.testng.annotations.Test;

public class FHCLoginPagePositiveTest2 extends TestBase
{

    @Test
    public void positiveTestLogin()
    {  //http://www.fhctrip.com/Account/Logon    http://fhctrip-qa.com/Account/Logon
        driver.get(ConfigReader.getProperty("fhc_login_url"));
        FHCLoginPage fhcLoginPage = new FHCLoginPage(driver);
        fhcLoginPage.username.sendKeys(ConfigReader.getProperty("validUsername"));
        fhcLoginPage.password.sendKeys(ConfigReader.getProperty("validPassword"));
        fhcLoginPage.loginButton.click();
    }


}
