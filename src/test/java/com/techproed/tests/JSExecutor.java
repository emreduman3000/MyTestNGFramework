package com.techproed.tests;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
public class JSExecutor
{
    @Test
    public void javascriptExecuterTest()
    {
        Driver.getDriver().get(ConfigReader.getProperty("fhc_login_url"));
        WebElement loginButton = Driver.getDriver().findElement(By.id("btnSubmit"));
        WebElement createButton = Driver.getDriver().findElement(By.xpath("//span[@class='btn btn-primary py-3 px-5']"));
        //clickElementByJS(loginButton);
        //clickElementByJS(createButton);
        //System.out.println(getTitleByJS());
        //scrollDownByJS();
        //WebElement intagram = Driver.getDriver().findElement(By.xpath("//*[.='Instagram']"));
        //scrollInToViewJS(intagram);
        //WebElement image = Driver.getDriver().findElement(By.xpath("(//a[@class='blog-img mr-4'])[4]"));
        //scrollInToViewJS(image);

        flash(loginButton);
        //generateAlert("A bug was found!!!");
    }


    //Bu method bir parametre alir(WebElement elememt).
    //elementi methodun icine yazdigimizda js bu elemnte tiklar.
    public void clickElementByJS(WebElement element)
    {
        JavascriptExecutor jsExecutor =((JavascriptExecutor)Driver.getDriver());
        jsExecutor.executeScript("arguments[0].click();", element);
    }

    //Sayfa basligini JS ile alma metodu
    public String getTitleByJS()
    {
        JavascriptExecutor jsExecutor =((JavascriptExecutor)Driver.getDriver());
        String title = jsExecutor.executeScript("return document.title;").toString();
        return title;
    }

    //Scroll down (asagi kaydirma). bu metod sayfada en alt kisma kadar kaydirir.
    public void scrollDownByJS()
    {
        JavascriptExecutor jsExecutor =((JavascriptExecutor)Driver.getDriver());
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }


    //JS ile bir elemente kadar kaydirma(scroll down)
    public void scrollInToViewJS(WebElement element)
    {
        JavascriptExecutor jsExecutor =((JavascriptExecutor)Driver.getDriver());
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);",element);
    }

    //as button clicked, it turns into the
    public void flash(WebElement element)
    {
        String bgColor = element.getCssValue("backgroundcolor");
        for (int i = 0; i < 2; i++) {
            changeColor("rgb(200,500,900", element);
            changeColor(bgColor, element);
        }
    }

    public void changeColor(String color, WebElement element)
    {
        JavascriptExecutor javascriptExecutor = ((JavascriptExecutor) Driver.getDriver());
        javascriptExecutor.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);
        try
        {
            Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }


    //when ıt is required, generate an alert message
    public void generateAlert(String message)
    {
        JavascriptExecutor jsExecutor =((JavascriptExecutor)Driver.getDriver());
        jsExecutor.executeScript("alert('"+ message + "')");
    }
}