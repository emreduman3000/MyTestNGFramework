package com.techproed.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class IFrameTest {

    //iframe: a website which is inserted into another website
    //Bir class olusturun: IframeTest
    //https://the-internet.herokuapp.com/iframe  adresine gidin.
    //Bir metod olusturun: iframeTest
    //“An IFrame containing….” textini konsolda yazdirin.
    //Text Box’a “Merhaba Dunya!” yazin.
    //TextBox’in altinda bulunan “Elemental Selenium” linkini textini konsolda yazdirin.

    WebDriver driver;

    @BeforeClass
    public void driver()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/iframe");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


    }

    @Test
    public  void totalIframes()
    {
        System.out.println(driver.findElements(By.tagName("iframe")).size());
    }

    @Test(dependsOnMethods="totalIframes")
    public void iframeTest(){
        WebElement anIframeTest = driver.findElement(By.xpath("//h3"));// //h3[1]
        //h3 tag'inin attribute ı yoktur, ve böyle locate dilir
        System.out.println(anIframeTest.getText());

        WebElement titleOfIframe=driver.findElement(By.xpath("//h3[.='An iFrame containing the TinyMCE WYSIWYG Editor']"));

        List<WebElement> amountOfIframes=driver.findElements(By.tagName("//iframe"));
        System.out.println(amountOfIframes.size());

        //WebElement textbox = driver.findElement(By.xpath("//p"));//fail,locate edemez
        //textbox.sendKeys("HELLO WORLD!");


        //IF A TEST FAILS,
        //1.CONTROL LOCATOR
        //2.CONTROL IF THERE IS WAIT ISSUE
        //3.CHECK IF THERE IS IFRAME

        //AT THIS WEBPAGE,THERE IS IFRAME,SO I NEED TO SWITCH TO IFRAME
        //THERE ARE 3 DIFFERENT WAYS TO SWITCH TO AN IFRAME;
        //1.BY INDEX
        //2.BY name or ID
        //3.BY WEBELEMENT

        //switchTo.frame(int  frame number):
        //driver.switchTo().frame(0);//webpagedeki ilk iframe indexi:0  1.
        //switchTo.frame(string  frameNameOrId):
        //driver.switchTo().frame("mce_0_ifr");//iframe'in id'si        2.

        WebElement iframeLocating = driver.findElement(By.id("mce_0_ifr")); //3.
        //switchTo.frame(WebElement  frameElement);
        driver.switchTo().frame(iframeLocating);//BY WEBELEMENT

        //from now on, we can find any element in iframe
        WebElement iframee = driver.findElement(By.xpath("//p"));
        WebElement iframee2 = driver.findElement(By.tagName("p"));
        iframee.clear();//delete writings in the iframe
        iframee.sendKeys("HELLO WORLD!");


        //TextBox’in altinda bulunan “Elemental Selenium” linkini textini konsolda yazdirin.
        // locate Elemental selenium text
        //Parent frame geri gelelim
        //driver.switchTo().parentFrame();
        driver.switchTo().defaultContent();   // default page gider.
        WebElement seleniumText = driver.findElement(By.linkText("Elemental Selenium"));
        System.out.println(seleniumText.getText());

        //iframe  ==========>Buraya gelmek istiyorum. defaultContent();
        //     iframe  =====>Buraya gelmek istiyorum. parentFrame();
        //        iframe   => buradayim
        //            iframe
    }
}
