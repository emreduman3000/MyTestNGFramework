package com.techproed.tests;

import com.techproed.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Upload_Download_CheckFile extends TestBase {

    @Test
    public void chooseUploadFile()
    {
        driver.get("https://the-internet.herokuapp.com/upload");
        WebElement chooseFile=driver.findElement(By.id("file-upload"));
        //action.moveToElement(chooseFile).click().perform();

        String imagePath="C:/Users/Emre Duman/Pictures/Saved Pictures/redfall.jpg";
        chooseFile.sendKeys(imagePath);

        WebElement uploadButton=driver.findElement(By.id("file-submit"));
        uploadButton.click();

        Assert.assertEquals(driver.findElement(By.xpath("//h3")).getText() ,"File Uploaded!");
    }

    @Test
    public void downloadFile() throws InterruptedException {
        //https://the-internet.herokuapp.com/download adresine gidin.
        driver.get("https://the-internet.herokuapp.com/download");
        //image1.jpg dosyasını indir
        WebElement image1 = driver.findElement(By.linkText("flower.jpg"));
        image1.click();
        Thread.sleep(5000);//biraz beklmesi gerek yoksa fail olur


        //Ardından dosyanın başarıyla indirilip indirilmediğini doğrulayın.

        //Kullanici klasoru
        String userKlasor = System.getProperty("user.home");
        System.out.println(userKlasor);//C:\Users\Emre Duman

        // indirilen dosyanin konumunu(path) buluyoruz.
        String filePath = userKlasor + "/Downloads/flower.jpg";
        //String filePath = "C:/Users/Emre Duman/Downloads/image1.jpg";  bu da kullanilabilir.

        //dosya var mi yok mu kontrol ediyoruz.
        boolean isDownloaded = Files.exists(Paths.get(filePath));
        Assert.assertTrue(isDownloaded);  // dosya yuklendi ise PASS, Degilse FAIL
    }


    @Test
    public void isExistFile()
    {
        String mevcutKlasor =  System.getProperty("user.dir");
        System.out.println("MEVCUT KLASOR => "+ mevcutKlasor);
        //MEVCUT KLASOR => C:\Users\emreduman\IdeaProjects\myTestNGFramework


        String userKlasor = System.getProperty("user.home");//C:\Users\emreduman
        System.out.println("USER KLASOR => "+userKlasor);
        //USER KLASOR => C:\Users\emreduman

        String filePath=userKlasor+"/Pictures/saved pictures/redfall.jpg";
        //  C:/users/emreduman/Pictures/saved pictures/redfall.jpg

        boolean isFileExist= Files.exists(Paths.get(filePath));
        Assert.assertTrue(isFileExist);//if this file exist, true will be written on console
    }
}
