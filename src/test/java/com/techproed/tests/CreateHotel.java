package com.techproed.tests;

import com.techproed.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CreateHotel extends TestBase
{
    @Test
    public void createHotel() throws InterruptedException {
        //http://www.fhctrip.com/admin/HotelAdmin/Create adresine gidin.
        driver.get("http://www.fhctrip.com/admin/HotelAdmin/Create");

        //Username textbox ve password textboxlarini locate edin ve asagidaki verileri girin.
        WebElement usernameBox = driver.findElement(By.id("UserName"));
        WebElement passwordBox = driver.findElement(By.id("Password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        action.sendKeys(usernameBox,"manager2").build().perform();//Username : manager2
        passwordBox.sendKeys("Man1ager2!");   //Password : Man1ager2!
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        loginButton.click();

        //Acilan sayfadaki butun textboxlara istediginiz verileri girin (en sondaki dropdowna dikkat edin).
        driver.findElement(By.id("Code")).sendKeys("DUMANNNN");
        driver.findElement(By.id("Name")).sendKeys("SUNSET");
        driver.findElement(By.id("Address")).sendKeys("Athens");
        driver.findElement(By.id("Phone")).sendKeys("12345678987");
        driver.findElement(By.id("Email")).sendKeys("sunsethotel@gmail.com");
        WebElement IDGroup = driver.findElement(By.id("IDGroup"));
        Select select = new Select(IDGroup);
        select.selectByIndex(1);

        driver.manage().window().maximize();

        Thread.sleep(3000);

        //Save butonuna basin.
        WebElement saveButton = driver.findElement(By.id("btnSubmit"));
        saveButton.click();


        //“Hotel was inserted successfully” textinin goruntulendigini dogrulayin.
        WebDriverWait wait = new WebDriverWait(driver,10);
        boolean isTrue =  wait.until(ExpectedConditions.textToBe(By.xpath("//div[@class='bootbox-body']"),"Hotel was inserted successfully"));
        Assert.assertTrue(isTrue);

        //implicitWait() olmuyor

        //OK butonuna tiklayin.
        WebElement okButton = driver.findElement(By.xpath("//button[@data-bb-handler='ok']"));
        okButton.click();
    }
}















