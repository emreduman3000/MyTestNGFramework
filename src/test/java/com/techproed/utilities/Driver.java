package com.techproed.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class Driver
{
    //Driver Class is used start Driver instance.(Singleton Driver)
    //we used it in order to configurate and start drive when we need
    //driver will be declared as a null(if driver==null)
    //we will initialize driver when we use it with any browser

    //lets create driver instance
    static WebDriver driver;//interface
    //it is easier to get driver instance from outside of the class without creatinh object


    private Driver()
    {
        //private constr. nesne olusturulmasın diye olusturulur
    }

    //Drive'ı baslatmak için method yazdık
    public static WebDriver getDriver()
    {
        if(driver==null)
        {
            switch(ConfigReader.getProperty("browser")) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver=new FirefoxDriver();
                    break;

                case "ie":
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;

                case "safari":
                    WebDriverManager.getInstance(SafariDriver.class).setup();
                    driver=new SafariDriver();
                    break;

                case "headless-chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
                    break;

            }
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return driver;
    }

    public static void closeDriver()
    {
        if(driver!=null)//ıf driver symbolize chrome ,close chrome driver
        {
            driver.quit();
            driver=null;//driverın null olması onemli(multibrowserTest Yaparken)
        }
    }

}
