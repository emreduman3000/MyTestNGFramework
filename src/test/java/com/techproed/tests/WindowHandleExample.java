package com.techproed.tests;

import com.techproed.utilities.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class WindowHandleExample extends TestBase {

    //Tests package’inda yeni bir class olusturun: WindowHandleExample
    //https://the-internet.herokuapp.com/windows adresine gidin.
    //Sayfadaki textin  “Opening a new window” olduğunu doğrulayın.
    //Sayfa başlığının(title) “The Internet” olduğunu doğrulayın.
    //Click Here butonuna basın.
    //Acilan yeni pencerenin sayfa başlığının (title)  “New Window” oldugunu dogrulayin.
    //Sayfadaki textin   “New Window” olduğunu doğrulayın.
    //Bir önceki pencereye geri döndükten sonra sayfa başlığının  “The Internet” olduğunu doğrulayın.
    @Test
    public void newWindowTest()
    {
        driver.get("https://the-internet.herokuapp.com/windows");

        String actualText=driver.findElement(By.xpath("//h3")).getText();
        String expectedText="Opening a new window";
        Assert.assertEquals(actualText,expectedText);

        String actualTitle=driver.getTitle();
        String expectedTitle="The Internet";
        Assert.assertEquals(actualTitle,expectedTitle);

        String parentWindowHandle=driver.getWindowHandle();
        System.out.println("Parent Window Handle:"+parentWindowHandle.toString());//unique'dir. at every run, ıt will change

        driver.findElement(By.linkText("Click Here")).click();

        //Acilan yeni pencerenin sayfa başlığının (title)  “New Window” oldugunu dogrulayin.
        Set <String> allWindowHandles = driver.getWindowHandles();
        System.out.println(allWindowHandles.size());
        allWindowHandles.forEach(n -> System.out.println(n));

        for(String childWindowHandle : allWindowHandles)
        {
            if (!childWindowHandle.equals(parentWindowHandle)) {
                driver.switchTo().window(childWindowHandle);
                System.out.println("CHILD WINDOW HANDLE: " + childWindowHandle);
            }
        }
        System.out.println(driver.getWindowHandle());

        String newWindowTitle = driver.getTitle();
        Assert.assertEquals(newWindowTitle,"New Window");
        //Sayfadaki textin   “New Window” olduğunu doğrulayın.
        String newWindowText = driver.findElement(By.xpath("//h3")).getText();
        Assert.assertEquals(newWindowText,"New Window");
        //Bir önceki pencereye geri döndükten sonra sayfa başlığının  “The Internet” olduğunu doğrulayın.

        driver.switchTo().window(parentWindowHandle);
        System.out.println(driver.getWindowHandle());
        Assert.assertEquals(driver.getTitle(),"The Internet");


    }


}

