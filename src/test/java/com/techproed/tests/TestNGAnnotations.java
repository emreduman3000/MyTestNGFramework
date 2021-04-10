package com.techproed.tests;


import org.testng.annotations.*;

public class TestNGAnnotations {

    /*
    --Below is the list of annotations that TestNG support in Selenium--

    @BeforeSuite: @AfterSuite: runs before/ after all tests in this suite

    @BeforeTest:@AfterTest: run before/after all the test methods inside the <test>

    @BeforeClass@AfterClass: run before/ after all the test methods in a test class.

    @BeforeGroups: AfterGroups: run before/after any specific test group.
    Dikkat etmemiz gereken nokta ise bu notasyon ile işaretli metot her test için ayrı
    ayrı çalışmaz, bir kez çalışır.

    @BeforeMethod:@AfterMethod: run before/ after each test method.
    Yani her test metodu için ayrı ayrı çalışacaktır.

     */


    //Before Test is executed by Alphabetic order too
    @Ignore //JUnit and testNG de @Ignore Annotation sadece testlerde calsıır
    @BeforeSuite
    public void BeforeSuite2()
    {
        System.out.println("***************BEFORE SUITE2**************");
    }

    @BeforeSuite
    public void BeforeSuite0()
    {
        System.out.println("***************BEFORE SUITE0**************");
    }

    @BeforeSuite
    public void BeforeSuite1()
    {
        System.out.println("***************BEFORE SUITE1**************");
    }

    /*
     ***************BEFORE SUITE0**************

     ***************BEFORE SUITE1**************

     ***************BEFORE SUITE2**************
     */

    @BeforeGroups(groups = "beforeAfterGroup")
    public void beforeGroups()
    {
     System.out.println("beforeGroups");
    }

    @BeforeTest
    public void BeforeTest() { System.out.println("***************BEFORE TEST**************"); }

    @BeforeClass
    public void BeforeClass() { System.out.println("***************BEFORE CLASS**************"); }


    @BeforeMethod//calısmadı
    public void BeforeMethod() { System.out.println("***************BEFORE METHOD**************"); }


    //testNG'de methodlar alfabetik sıraya göre yazdırılır.
   //JUnit'te ise sırayla yazılır
    @Test(groups = "beforeAfterGroup")
    public void b(){ System.out.println("test1"); }

    @Test
    public void a(){ System.out.println("test2"); }//ilk bunu göstercek
    @Ignore @Test//Ignore sadwce Test annotatıonlarda calısır
    public void c(){ System.out.println("test3"); }



    @AfterMethod
    public void AfterMethod(){ System.out.println("***************AFTER METHOD**************"); }

    @AfterGroups//calısmadı
    public  void afterGroups()
    {
     System.out.println("afterGroups");
    }

    @AfterClass
    public void AfterClass(){System.out.println("***************AFTER CLASS**************"); }

    @AfterTest
    public void AfterTest(){ System.out.println("***************AFTER TEST**************"); }

    @AfterSuite
    public void AfterSuite(){ System.out.println("***************AFTER SUITE**************"); }
}
