package com.techproed.tests;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.techproed.pages.GlbHomePage;
import com.techproed.pages.GlbSignUpPage;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


/*
    Extent reports => Bize Html raporları verir.
    ExtentReports nedir? => HTML raporlama aracıdır. Test adımlarını kaydetmemize yardımcı olur. Ayrıca ekran görüntüleri ekleyebiliriz.
    ExtentReports extendReports; => Raporlamayı başlatmak için ExtentReports'a ihtiyacımız var.
    flush() metodunu için ExtentReports kullanıyoruz.
    ExtentHtmlReporter extentHtmlReporter; => Bu, özel raporlara ve raporların yapılandırmasına sahip olmamıza yardımcı olur,html raporlarını oluşturur. Bunu raporun oluşturulacağı yolu ayarlamak için de kullanıyoruz.
    ExtentTest extentTest; => Bilgi eklemek için. Test adımlarını eklemek için (açıklama). Günlükleri(logs) ekliyoruz.
    extentTest.info ("URL'yi Açma"); == >>>. bilgi sadece adım eklemek içindir
 */

public class ExtentReports1//projeyi run edince reports packageı olusuyor
{
    //ExtentReports==>> raporlamaya baslamak ve kapatmak icin  buna ihtiyacimiz var.raporukapatmak ( flush() ) icin kulanırız
    //ExtentHtmlReporter ==> raporları configure etmek için kullanırız. HTML raporlarını create eder
    //ExtentTest ==>acıklama(logs) eklmemk icin, test adımlarını belirlemek ıcın kullanılır.

    public ExtentReports extentReports;
    public ExtentHtmlReporter extentHtmlReporter;
    public ExtentTest extentTest;

    @BeforeTest
    public void setup()
    {
        //reporter'i baslatalım ve konumunu ayarlayalım
        //klasor:reports , file name: extentreport.html
        extentHtmlReporter =new ExtentHtmlReporter("./reports/extentreport.html"); //olusacak bu file . demek proje levelinden basla

        //extentHtmlReporter'la bazı configurationlar yapalım.
        extentHtmlReporter.config().setReportName("Global Trader Report");//ismi ben kendim verdim
        extentHtmlReporter.config().setTheme(Theme.STANDARD);
        extentHtmlReporter.config().setEncoding("UTF-8");
        extentHtmlReporter.config().setDocumentTitle("GLB SIGN UP REPORT");

        //extentReports instance ını kullanararak nesne olusutur
        extentReports=new ExtentReports();
        extentReports.attachReporter(extentHtmlReporter);//extentReports ı extentHtmlReporter'a attach etti

        //daha fazla acıklama ekleyebiliriz
        extentReports.setSystemInfo("automation engineer","emre");//10 tane tester var bunu kim yolladı, belli etnek içiçn ismimimizi yazarız
        extentReports.setSystemInfo("browser","chrome");//10 tane tester var bunu kim yolladı, belli etnek içiçn ismimimizi yazarız
        //k: key  v: value

    }

    @AfterTest
    public void endReport()
    {
        extentReports.flush();//to close report
    }


    @Test
    public void glbSignUp() throws InterruptedException//bu methodu copy-paste yaptım GlbSignUpTest class'ından
    {
        GlbSignUpPage glbSignUpPage=new GlbSignUpPage();
        GlbHomePage glbHomePage=new GlbHomePage();

        //Driver.getDriver().get(ConfigReader.getProperty("glb_signup_url"));
        Driver.getDriver().get(ConfigReader.getProperty("glb_url"));
        glbHomePage.joinNowLink.click();

        /*
            glbSignUpPage.email.sendKeys(ConfigReader.getProperty("TestEmail"));
            glbSignUpPage.name.sendKeys(ConfigReader.getProperty("TestUserName"));
            glbSignUpPage.phone.sendKeys(ConfigReader.getProperty("TestPhone"));
            glbSignUpPage.password.sendKeys(ConfigReader.getProperty("TestPassword"));
            glbSignUpPage.repassword.sendKeys(ConfigReader.getProperty("TestPassword"));
            glbSignUpPage.signUpButton.click();
        */


        extentTest=extentReports.createTest("GLB Sign Up Test");
        extentTest.info("Url'e git");
        Driver.getDriver().get(ConfigReader.getProperty("glb_url"));
        extentTest.info("join now linkine tikla");
        glbHomePage.joinNowLink.click();
        extentTest.info("email gonder");
        glbSignUpPage.email.sendKeys(ConfigReader.getProperty("TestEmail"));
        extentTest.info("usermame gonder");
        glbSignUpPage.name.sendKeys(ConfigReader.getProperty("TestUserName"));
        extentTest.info("telefon numarasi gonder");
        glbSignUpPage.phone.sendKeys(ConfigReader.getProperty("TestPhone"));
        extentTest.info("password Yaz");
        glbSignUpPage.password.sendKeys(ConfigReader.getProperty("TestPassword"));
        extentTest.info("password'u tekrar yaz");
        glbSignUpPage.repassword.sendKeys(ConfigReader.getProperty("TestPassword"));
        extentTest.info("sign up'a tikla");
        glbSignUpPage.signUpButton.click();



        Thread.sleep(5000);
        Assert.assertTrue(glbSignUpPage.successMessage.getText().equals("Success!!"));

        System.out.println("SUCCESS MESAJ : "+ glbSignUpPage.successMessage.getText());

        //Test fail degil pass olacak cunku assertFalse() methodunu kullandım.
        //extentTest.pass("passed: test successfully done");//pass olursa bunu yazdır
        //extentTest.fail("failed: test unsuccessfully done");//fail olursa bunu yazdır
        extentTest.log(Status.PASS,"This is a logging event passed!");
        extentTest.log(Status.FAIL,"This is a logging event FAILED!");
        Driver.closeDriver();
        extentTest.pass("Driver basariyla kapatildi.");

        //reports package ına tıkla extentreport.html right click/open in browser/chrome
        //dataları refresh yapıp tekrar singin yap ve run yapmadan report package ını sil
    }
}