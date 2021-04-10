package com.techproed.smoketest;

import com.techproed.pages.FHCLoginPage;
import com.techproed.utilities.TestBase;
import org.testng.annotations.Test;

////fhctrip uygulamasının oturum açma işlevselliğini test edin.  http://www.fhctrip.com/Account/Logon
//    //Iki ayri class olusturun (package:smoketest) : PositiveTest ve NegativeTest
//    //Positive Test : her ikisi de geçerli username ve password
//    //Negative test :
//    //geçerli  username fakat geçersiz password
//    //geçerli password  fakat geçersiz username
//    //her ikisi de geçersiz username ve password.
public class FHCLoginPagePositiveTest extends TestBase
{
    @Test
    public void positiveTestCaseLogin()
    {

        driver.get("http://fhctrip-qa.com/Account/Logon");
        FHCLoginPage fhcLoginPage = new FHCLoginPage(driver);
        fhcLoginPage.username.sendKeys("manager2");
        fhcLoginPage.password.sendKeys("Man1ager2!");
        fhcLoginPage.loginButton.click();
    }
}
