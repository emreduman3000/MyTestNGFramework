package com.techproed.tests;

import com.techproed.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class WaitTest extends TestBase {

    //2.    Iki tane metod olusturun :  implicitWait() , explicitWait()
    @Test
    public void implicitWait(){
        //3.    https://the-internet.herokuapp.com/dynamic_controls adresine gidin.
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        //4.    Remove butonuna basin.
        WebElement removeButton = driver.findElement(By.xpath("//button[@type='button']"));
        removeButton.click();
        //mesajin yuklenmesi biraz zaman aldigi icin wait kullanmamiz gerekir.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//  ==>>> TestBase'e de koyabilirim
        //5.    “It’s gone!” mesajinin goruntulendigini dogrulayin.
        WebElement goneMessage = driver.findElement(By.id("message"));
        Assert.assertEquals(goneMessage.getText(),"It's gone!");
    }

    @Test
    public void setScriptTimeout(){

        /*
        The default timeout for setScriptTimeout method is zero.
        If you do not set time, then there are chances that executeAsyncScript method may fail
        because the JavaScript code may take more than the time allotted. To avoid such failures, set the setScriptTimeout.
        This is mainly used for Javascript objects and executors.
         */

        // setScriptTimeout for 10 seconds
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        ((JavascriptExecutor) driver).executeScript("alert('hello world');");
        ((JavascriptExecutor) driver).executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 500);");

    }

    @Test
    public void pageLoadTimeout()
    {
        /*
            This sets the time to wait for a page to load completely before throwing an error.
            If the timeout is negative, page loads can be indefinite.
         */

        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.get("https://www.google.com/");

        /*
        In the code above, if your page does not load within 30 seconds, WebDriverException will be thrown.

        Selenium Timeouts must be included to create effective, comprehensive and seamlessly running test cases.
        This article intends to help in this regard by briefly explaining how Timeouts work,
        and how they can be incorporated into Selenium test scripts.
         */
    }

    @Test
    public void explicitWait()
    {

        //3.    https://the-internet.herokuapp.com/dynamic_controls adresine gidin.
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        //4.    Remove butonuna basin.
        WebElement removeButton = driver.findElement(By.xpath("//button[@type='button']"));
        removeButton.click();

        //drive'a bekle diyor,eger işi 3 saniyede cözerse 20 saniye beklemez,dynamıc'tir.
        //Create WebDriverWait object.
        WebDriverWait wait = new WebDriverWait(driver,20);
        WebElement goneMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        //5.    “It's gone!” mesajinin goruntulendigini dogrulayin.
        Assert.assertEquals(goneMessage.getText(), "It's gone!");

        /*


        Implicit wait is for all web elements and explicit wait is for specific condition so there is no relation between them.
        Implicit wait will wait for WebElement but explicit wait will wait for condition which you have mentioned.
        The following are the Expected Conditions that can be used in Selenium Explicit Wait

        alertIsPresent()
        elementSelectionStateToBe()
        elementToBeClickable()
        elementToBeSelected()
        frameToBeAvaliableAndSwitchToIt()
        invisibilityOfTheElementLocated()
        invisibilityOfElementWithText()
        presenceOfAllElementsLocatedBy()
        presenceOfElementLocated()
        textToBePresentInElement()
        textToBePresentInElementLocated()
        textToBePresentInElementValue()
        titleIs()
        titleContains()
        visibilityOf()
        visibilityOfAllElements()
        visibilityOfAllElementsLocatedBy()
        visibilityOfElementLocated()
         */
    }

    @Test
    public void fluentWait()
    {

        String eTitle = "Demo Guru99 Page";
        String aTitle = "" ;

        // launch Chrome and redirect it to the Base URL
        driver.get("http://demo.guru99.com/test/guru99home/" );

        //get the actual value of the title
        aTitle = driver.getTitle();

        //compare the actual title with the expected title
        if (aTitle.contentEquals(eTitle))
            System.out.println( "Test Passed") ;
        else
            System.out.println( "Test Failed" );


        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
        WebElement clickseleniumlink = wait.until(new Function<WebDriver, WebElement>(){
        /*
        In the above example, we are declaring a fluent wait with the timeout of 30 seconds and the frequency
        is set to 5 seconds by ignoring "NoSuchElementException"
         */
            public WebElement apply(WebDriver driver ) {
                return driver.findElement(By.xpath("/html/body/div[1]/section/div[2]/div/div[1]/div/div[1]/div/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/div/a/i"));
            }
        });
        //click on the selenium link
        clickseleniumlink.click();


    }

}

//SELENIUM WAITS - SENKRONIZASYON
//Senkronizasyon, otomasyon tester'lar için çok önemlidir.
//İyi bir test yürütmesi için bu konsepte ihtiyacımız var.
//TEST CASE AŞAĞIDAKİ SEBEPLERDEN DOLAYI FAIL OLABİLİR:
//1. Selenyum otomasyon komut dosyası(script) sorunu - locator yanlış olabilir.
//Locator doğruysa:
//2. Yürütme hızı - çok hızlı olabilir.
//3. Web uygulaması hızı - çok yavaş olabilir.
//4. İnternet çok yavaş olabilir.
//5. Web sayfası yavaş yükleniyor olabilir. Çok fazla elementi olabilir(gorseller, iframe'ler)
//Bu gibi durumlarda Senkronize etmek için beklemeler kullanmamız gerekir.
//======================================================================
//Iki tür bekleme(wait) vardır:
//1.Implicit Wait:
//-Sadece bir syntax vardir => driver.manage().timeouts().implicitlywait(10,TimeUnit.SECONDS);
//-Global bekleme olarak bilinir
//-driver objesi kullanildiginda uygulanir.
//-Dinamiktir, driver elementi bulur bulmaz yürütmeye devam edecektir.
//Yukarıdaki örnekte, sürücü 10 saniye kadar bekleyecektir. 3 saniye içinde bulunursa, 7 saniye daha beklemez.
//2.Explicit Wait:
//-Bir ExpectedConditions icin explicit wait kullanıyoruz
//-Kullanmak için WebDriverWait objesi create etmeliyiz.
//
//     WebDriverWait wait = new WebDriverWait (driver, 10);
//-Şimdi beklenen kosula(expected condition) gecmek icin wait objesini kullanabilirim.
//WebElement element = wait.until (ExpectedConditions.visibilityOfElementLocated (By.id ( "id value")));
//=====================================================================
//Soru: Selenium'da Senkronizasyon sorununu nasıl çözersiniz?
//Locator'i düzelttiğini söyleme-> Bu Senkronizasyon ile ilgili değil
//Cevap: Framework'umde TestBase classinda implicitly wait create ettim ve gerektiginde driver icin calisiyor.
//       Implicitly wait  işe yaramadığı bazı durumlar olabilir. Bu durumda, Thread.sleep (5000); Ancak, bu iyi bir fikir değildir, çünkü Thread.sleep SABİT BEKLİYOR(HARD WAIT).
//       Bu nedenle, explicit wait kullanıyorum. Beklenen koşulları(expected conditions) kullanabilirim. Belirli koşulları ile uğraşırken bu çok yardımcı olur. Explicit wait de dinamiktir.
//=====================================================================
//Soru: Hangisini tercih ediyorsun?
//Cevap: Duruma gore değişir. Implicitly wait kullanımı daha kolaydır. Bir kez oluşturun, her zaman kullanın.
//       Ancak Explicit wait'in daha iyi çalıştığı bazı durumlar vardır. Boyle durumlarda explicit wait kullanmak gerekir. Explicit wait bana daha fazla koşul ve seçenek sunuyor.
//=====================================================================