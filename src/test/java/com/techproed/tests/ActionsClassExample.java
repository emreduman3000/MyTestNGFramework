package com.techproed.tests;

import com.techproed.utilities.TestBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class ActionsClassExample extends TestBase
{
    //Create  class: ActionsClassExample
    //Create  test method : contextClickMethod() ve aşağıdaki senaryoyu test edin:
    //https://the-internet.herokuapp.com/context_menu web sitesine gidin.
    //Dikdörtgen kutu icinde sağa tıklayın.
    //Alert mesajinin “You selected a context menu” oldugunu dogrulayin.
    //Alert’te OK ye basin

    @Test
    public void contextClickMethod()//rightClick
    {
        //action.contextClick(rectangle).perform();

        driver.get("https://the-internet.herokuapp.com/context_menu");
        //Actions action=new Actions(driver); // TestBase class'ına koy

        action.contextClick().build().perform();//normal sayfada sağa tıklar

        WebElement rectangle=driver.findElement(By.id("hot-spot"));
        action.contextClick(rectangle).perform();//right click:contextClick

        Alert alert=driver.switchTo().alert();//alert mesajı
        String alertMessage=alert.getText();
        System.out.println("Alert Message:"+alertMessage);
        String expectedAlertMessage="You selected a context menu";
        assertEquals(alertMessage,expectedAlertMessage);

        driver.switchTo().alert().accept();//click OK on alert!

    }


    //Create  test method : hoverOver() ve aşağıdaki senaryoyu test edin:
    //https://www.amazon.com/  web sitesine gidin.
    //“Your Account” linkine tıklayın.
    //Sayfa basliginin(page title) “Your Account) icerdigini(contains) dogrulayin.
    @Test
    public void hoverOver(){
        //        action.moveToElement(helloSignIn).perform();
        //https://www.amazon.com/  web sitesine gidin.
        driver.get("https://www.amazon.com/");

        //“Your Account” linkine tıklayın.
        WebElement helloSignIn = driver.findElement(By.xpath("//span[text()='Hello, Sign in']"));
        action.moveToElement(helloSignIn).perform();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement account = driver.findElement(By.linkText("Account"));
        account.click();

        //Sayfa basliginin(page title) “Your Account" icerdigini(contains) dogrulayin.

        String title=driver.getTitle();
        assertEquals(title,"Your Account");
    }

    @Test
    public void hoverOver2() {

        //specify the LambdaTest URL
        driver.get("https://www.lambdatest.com/");

        assertEquals(driver.getTitle(), "Most Powerful Cross Browser Testing Tool Online | LambdaTest");
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

    //specify the locator of the Resources menu
        WebElement element = driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[4]/a"));


        action.moveToElement(element).build().perform();

        //specify the locator for the element Blog and click
        action.click(driver.findElement(By.linkText("Blog"))).build().perform();
        //action.click(driver.findElement(By.linkText("Blog"))).perform();

        assertEquals(driver.getCurrentUrl(), "https://www.lambdatest.com/blog/");

        //verify the page title after navigating to the Blog section
        assertEquals(driver.getTitle(), "LambdaTest | A Cross Browser Testing Blog");


    }


    //testCase
    //https://www.google.com/ adresine gidin.
    //Google text box’a IPHONE (capital) yazdirin
    //( input(giris) => iphone , output => IPHONE)
    //Text box’ta cift tiklayin(double click).
    @Test
    public void keyUp_keyDown_doubleClick()
    {
        driver.get("https://www.google.com/");
        WebElement googleSearchBar=driver.findElement(By.name("q"));

        //there are 3 different way
        googleSearchBar.sendKeys("iphone".toUpperCase());
        googleSearchBar.clear();

        googleSearchBar.sendKeys(Keys.SHIFT+"iphone");
        googleSearchBar.clear();

        action.moveToElement(googleSearchBar).click().
                keyDown(Keys.SHIFT).sendKeys("iphone").
                keyUp(Keys.SHIFT).perform();

        googleSearchBar.clear();

        action.keyDown(googleSearchBar, Keys.SHIFT).sendKeys("iphone").
                keyUp(googleSearchBar, Keys.SHIFT).perform();//keyUp elini tustan cek


        //Text box’ta cift tiklayin(double click).
        action.doubleClick(googleSearchBar).perform();



    }

    @Test
    public void scrollUpDown() throws InterruptedException {
        driver.get("https://www.amazon.com/");
        action.sendKeys(Keys.PAGE_DOWN).perform();
        Thread.sleep(3000);
        action.sendKeys(Keys.DOWN).perform();
        Thread.sleep(3000);
        action.sendKeys(Keys.ARROW_DOWN).perform();//cokcok az iner
        Thread.sleep(3000);
        action.sendKeys(Keys.ARROW_UP).perform();//cokcok az cıkar
        Thread.sleep(3000);
        action.sendKeys(Keys.UP).perform();
        Thread.sleep(3000);
        action.sendKeys(Keys.PAGE_UP).perform();
        Thread.sleep(3000);
    }

    @Test
    public void sendKey()
    {
        driver.get("http://a.testaddressbook.com/sign_in");
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        // Use the timeout when navigating to a webpage


        WebElement emailCheckBox=driver.findElement(By.name("session[email]"));
        WebElement passwordCheckBox=driver.findElement(By.name("session[password]"));
        WebElement signInButton=driver.findElement(By.name("commit"));
       // emailCheckBox.sendKeys("testtechproed@gmail.com");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        //pass the product name that has to be searched in the website
        action.sendKeys(emailCheckBox, "testtechproed@gmail.com").build().perform();
        action.sendKeys(passwordCheckBox, "Test1234!").build().perform();
        action.click(signInButton).build().perform();

    }

    @Test
    public void clickAndDrop_moveToElement()
    {
        driver.get("https://www.w3schools.com/html/html5_draganddrop.asp");
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);


        WebElement source = driver.findElement(By.xpath("//*[@id=\"drag1\"]"));
        WebElement destination = driver.findElement(By.xpath("//*[@id=\"div2\"]"));

        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        action.clickAndHold(source).moveToElement(destination).release().build().perform();

    }

    @Test
    public void copyPaste()
    {
        driver.get("https://www.google.com/account/about/");

        WebElement element = driver.findElement(By.xpath("//*[text() = 'Create an account']"));
        element.click();

        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);

        WebElement firstName = driver.findElement(By.id("firstName"));
        WebElement userName = driver.findElement(By.id("username"));

        firstName.sendKeys("shalini");

        action.keyDown( Keys.CONTROL ).sendKeys( "a" ).keyUp( Keys.CONTROL ).build().perform();
        action.keyDown( Keys.CONTROL ).sendKeys( "c" ).keyUp( Keys.CONTROL ).build().perform();

        userName.click();
        action.keyDown( Keys.CONTROL ).sendKeys( "v" ).keyUp( Keys.CONTROL ).build().perform();
    }


    @Test
    public void refreshThePage()
    {

        driver.get("https://www.amazon.in");
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        action.keyDown(Keys.CONTROL).sendKeys(Keys.F5).build().perform();
    }

    @Test
    public void dragAndDrop()
    {
        driver.get("http://demo.guru99.com/test/drag_drop.html");

        //Element which needs to drag.
        WebElement From=driver.findElement(By.xpath("//*[@id='credit2']/a"));

        //Element on which need to drop.
        WebElement To=driver.findElement(By.xpath("//*[@id='bank']/li"));

        //Dragged and dropped.
        action.dragAndDrop(From, To).build().perform();


    }

    @Test
    public void dragAndDropBy()
    {
        driver.get("http://demo.guru99.com/test/drag_drop.html");

        //Element(BANK) which need to drag.
        WebElement From=driver.findElement(By.xpath("//*[@id='credit2']/a"));

        //Drag and Drop by Pixel.
        action.dragAndDropBy(From,135, 40).build().perform();
    }

    /*
    Different Methods for performing Keyboard Events:
    keyDown(modifier key): Performs a modifier key press.
    sendKeys(keys to send ): Sends keys to the active web element.
    keyUp(modifier key): Performs a modifier key release.

    Different Methods for performing Mouse Events:
    click(): Clicks at the current mouse location.
    doubleClick(): Performs a double-click at the current mouse location.
    contextClick() : Performs a context-click at middle of the given element.
   *** clickAndHold(): Clicks (without releasing) in the middle of the given element.
    dragAndDrop(source, target): Click-and-hold at the location of the source element, moves to the location of the target element
   *** dragAndDropBy(source, xOffset, yOffset):  Click-and-hold at the location of the source element, moves by a given offset
   *** moveByOffset(x-offset, y-offset): Moves the mouse from its current position (or 0,0) by the given offset
    moveToElement(toElement): Moves the mouse to the middle of the element
   *** release(): Releases the depressed left mouse button at the current mouse location
     */

}

