package com.techproed.tests;

import com.techproed.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class EnableTest extends TestBase {

    //1.	Bir class olusturun : EnableTest
    //2.	Bir metod olusturun :  isEnabled()
    //3.	https://the-internet.herokuapp.com/dynamic_controls adresine gidin.
    //4.	Enable butonuna basin.
    //5.	Textbox’in etkin olup olmadigini(enabled) dogrulayin.
    //6.	“It’s enabled!” mesajinin goruntulendigini dogrulayin.

    @Test
    public void isEnabled()
    {
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        WebElement enableButton=driver.findElement(By.xpath("//button[@onclick='swapInput()']"));
        enableButton.click();


        WebDriverWait wait = new WebDriverWait(driver,20);
        WebElement disableMessage=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        Assert.assertEquals(disableMessage.getText(),"It's enabled!");

        //5.  Textbox’in etkin olup olmadigini(enabled) dogrulayin.
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);//  ==>>> TestBase'e de koyabilirim
        WebElement textBox = driver.findElement(By.xpath("//input[@type='text']"));
        System.out.println(textBox);
        boolean isEnable = textBox.isEnabled();
        Assert.assertTrue(isEnable);

        //isEnabled(); => bir elementin etkin olup olmadigini kontrol eder
        //isDisplayed(); => bir elementin gorunup gorunmedigini kontrol eder
        //isSelected(); => radio button ve checkbox
    }
}
