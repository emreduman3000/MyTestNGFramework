package com.techproed.tests;

import com.techproed.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class ReviewClass
{

    /*
        https://www.airbnb.co.in/ adresine git
        Sayfa basligini(title) al ve konsolda yazdir.
        Sayfanin mevcut url’ ini (current url) al
        Sayfanin source(page source) ‘u al.
    */

    @Test
    public void airbnb(){
        Driver.getDriver().get("https://www.airbnb.co.in/");
        String title = Driver.getDriver().getTitle();
        System.out.println("Sayfa basligi: " + title);
        //Sayfa basligi: Holiday Lets, Homes, Experiences & Places - Airbnb
        String url = Driver.getDriver().getCurrentUrl();
        System.out.println("URL: " + url);
        //URL: https://www.airbnb.co.in/
        String source = Driver.getDriver().getPageSource();
        System.out.println(source);
    }

    /*
        http://ebay.com adresine git
        Search box’a “Selenium” yaz
        Search butonuna tikla
        Selenium icin kac sonuc var kontrol et ve konsolda yazdir.
        Sayfa basligini(title) al ve konsolda yazdir.
        Sayfanin mevcut url’ini (current url) al
        Sayfanin source(page source) ‘u al.
     */

    @Test
    public void ebay(){
        Driver.getDriver().get("http://ebay.com");
        WebElement searchBox = Driver.getDriver().findElement(By.id("gh-ac"));

        //searcBox.sendKeys("Selenium" + Keys.ENTER);
        searchBox.sendKeys("Selenium" );
        WebElement searchButton = Driver.getDriver().findElement(By.id("gh-btn"));
        searchButton.click();

        WebElement result = Driver.getDriver().findElement(By.className("srp-controls__count-heading"));
        System.out.println("Toplam sonuc: " + result.getText());//Toplam sonuc: 1,569 results for Selenium

        String title = Driver.getDriver().getTitle();
        System.out.println(title);//Selenium | eBay

        String correntURL = Driver.getDriver().getCurrentUrl();
        System.out.println(correntURL);//https://www.ebay.com/sch/i.html?_from=R40&_trksid=m570.l1313&_nkw=Selenium&_sacat=0

        String pageSource = Driver.getDriver().getPageSource();
        System.out.println(pageSource);
    }


}
