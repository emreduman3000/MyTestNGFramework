package com.techproed.tests;

import com.techproed.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HotelRoomCreation extends TestBase
{

    //Tests packagenin altina class olusturun: HotelRoomCreation
    //Bir method olusturun:  RoomCreateTest()
    //http://www.fhctrip.com/admin/HotelRoomAdmin adresine gidin.
    //Username textbox ve password textboxlarini locate edin ve asagidaki verileri girin.
    //Username : manager2
    //Password : Man1ager2!
    //Login butonuna basin.
    //Acilan sayfadaki butun textboxlara istediginiz verileri girin
    //Save butonuna basin.
    //“HotelRoom was inserted successfully” textinin goruntulendigini dogrulayin.
    //OK butonuna tiklayin.
    //Hotel rooms linkine tiklayin.
    //"LIST OF HOTELROOMS" textinin goruntulendigini(isDisplayed) dogrulayin.

    @Test
    public void roomCreateTest() throws InterruptedException {
        //http://www.fhctrip.com/admin/HotelAdmin/Create adresine gidin.
        driver.get("http://fhctrip-qa.com/admin/HotelRoomAdmin");

        //Username textbox ve password textboxlarini locate edin ve asagidaki verileri girin.
        WebElement usernameBox = driver.findElement(By.id("UserName"));
        WebElement passwordBox = driver.findElement(By.id("Password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        usernameBox.sendKeys("manager2");     //Username : manager2
        passwordBox.sendKeys("Man1ager2!");   //Password : Man1ager2!
        loginButton.click();

        WebElement addHotelRoomButton = driver.findElement(By.className("hidden-480"));
        addHotelRoomButton.click();

        WebElement IDHotel = driver.findElement(By.id("IDHotel"));
        Select select = new Select(IDHotel);
        select.selectByIndex(10);

        //Acilan sayfadaki butun textboxlara istediginiz verileri girin (en sondaki dropdowna dikkat edin).
        driver.findElement(By.id("Code")).sendKeys("DUMANNNN");
        driver.findElement(By.id("Name")).sendKeys("204");
        driver.findElement(By.id("Location")).sendKeys("Athens");
        driver.findElement(By.xpath("//textarea[@dir='ltr']")).sendKeys("400");

        driver.manage().window().maximize();
        action.sendKeys(Keys.PAGE_DOWN).perform();//ya bu 2sini dene,draganddrop için
        Thread.sleep(5000);

        WebElement price= driver.findElement(By.id("Price"));
        WebElement source =driver.findElement(By.xpath("//li[@data-id='300']"));
        action.dragAndDrop(source,price).perform();

        WebElement roomType = driver.findElement(By.id("IDGroupRoomType"));
        Select select2 = new Select(roomType);
        select2.selectByIndex(5);

        driver.findElement(By.id("MaxAdultCount")).sendKeys("2");
        driver.findElement(By.id("MaxChildCount")).sendKeys("2");
        driver.findElement(By.id("btnSubmit")).click();  //Save butonuna basin.


        //explicit wait  //“HotelRoom was inserted successfully” textinin goruntulendigini dogrulayin.
        WebDriverWait wait = new WebDriverWait(driver,20);
        boolean isTrue = wait.until(ExpectedConditions.textToBe(By.xpath("//div[@class='bootbox-body']"),"HotelRoom was inserted successfully"));
        Assert.assertTrue(isTrue);  //true ise PASS olacak.
        //OK butonuna tiklayin.
        WebElement okButton = driver.findElement(By.xpath("//button[@data-bb-handler='ok']"));
        okButton.click();
/*
        action.sendKeys(Keys.HOME).perform();
        //Hotel rooms linkine tiklayin.
        WebElement hotelRoomsLink = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[@href='/admin/HotelRoomAdmin']"))));
        hotelRoomsLink.click();
        //"LIST OF HOTELROOMS" textinin goruntulendigini(isDisplayed) dogrulayin.
        WebElement listOfHotel = driver.findElement(By.xpath("//span[.='List Of Hotelrooms']"));
        Assert.assertTrue(listOfHotel.isDisplayed());
*/


    }

}
