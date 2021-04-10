package com.techproed.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

public class HardAssert_SoftAssert
{
    //http://a.testaddressbook.com/sign_in adresine gidin.
    //email textbox,password textbox, ve signin button elementlerini locate edin.
    //Aşağıdaki username ve password girin ve signin buttonuna tıklayın.
    //Username :  testtechproed@gmail.com
    //Password :   Test1234!
    //Daha sonra farklı iddialar(assertions) kullanarak sayfaya doğru giriş yaptığınızı doğrulayın.
    //Daha sonra farklı iddialar(assertions) kullanarak testtechproed@gmail.com beklenen kullanıcı  kimliğinin(userID) doğrulayın


    //hard assert:ASSERTION
    //SOFT ASSERT:VERIFICATION
    static WebDriver driver;
    static SoftAssert softAssert;


    @BeforeClass
    public void driver()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://a.testaddressbook.com/sign_in");

        //Yavas web siteleri icin implicitly wait kullanilabilir.sayfa acılır ve bekler 10 saniye sonra kodlar calısır
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }




    @Test
    public static void Login()
    {
        WebElement emailCheckBox=driver.findElement(By.name("session[email]"));
        WebElement passwordCheckBox=driver.findElement(By.name("session[password]"));
        WebElement signInButton=driver.findElement(By.name("commit"));

        emailCheckBox.sendKeys("testtechproed@gmail.com");

        passwordCheckBox.sendKeys("Test1234!");
        signInButton.click();

        softAssert=new SoftAssert();

        driver.manage().window().maximize();
    }



    @Test(priority = 1, dependsOnMethods = "Login")
    public void test_Login_byTitle()
    {
        WebElement welcome=driver.findElement(By.xpath("//h1[.='Welcome to Address Book']"));;
        Assert.assertTrue(welcome.isDisplayed());//JUnit
        org.testng.Assert.assertTrue(welcome.isDisplayed());//testNG
        System.out.println("if Hard Assertion fails, this line does not run");

        //SOFT ASSERT
        //// "Hard Assert" kullandigimizda bir fail/hata alinca geriye kalani test etmez.
        //// Eger biz bir hata oldugunda hemen sonucu ogrenmek istiyorsak bunu kullaniriz.
        //// "Soft Assert" ise hata bulsa da sonuna kadar devam eder hepsini test eder ve
        //// sonuc olarak daha ayrintili rapor verir.
        //// "Soft Assert"  icin ilk once obje olusturmak zorundayiz. (SoftAssert class'inda)
        //// a) SoftAssert softAssert = new SoftAssert();
        //// b) softAssert.assertTrue/False/Equals();
        //// c) softAssert.assertAll(); asil test yapan kisim.

        //softAssert.assertTrue(!welcome.isDisplayed());//fails
        softAssert.assertTrue(welcome.isDisplayed());//passes
        //softAssert.assertEquals(3,5);//fails

        softAssert.assertAll();//PASSLERİ AND faiiileri gösterir
        //yazmazsak fail olanları atlar SADECE PASSLERI GOSTERİR
    }

    @Test(priority = 2, dependsOnMethods = "Login")
    public void test_Login_byUserID()//ilk bu test edilir - alphabetic order
    {
        WebElement userID=driver.findElement(By.className("navbar-text"));;
        String actualID=userID.getText();
        String expectedID="testtechproed@gmail.com";
        Assert.assertEquals(actualID,expectedID);//IF IT FAILS , CODES BELOW OF IT DOES NOT RUN

        softAssert.assertEquals(actualID,expectedID);//FAILS AMA PASS OLUR
        softAssert.assertEquals(1,3);//FAILS AMA PASS OLUR
        softAssert.assertEquals(1,1);
        softAssert.assertAll();//YUKARIdakı SON  FAILIN FAIL OLDUGUNU BU ASSERTALL() SOYLER, SADECE 1 KEZ YAZMAK YETERLIDIR

    }


    @AfterClass
    public void close()
    {
        driver.close();
    }


}
