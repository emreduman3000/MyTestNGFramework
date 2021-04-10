package com.techproed.tests;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Priority {


    //Priority hiç kullanmazsak alphabetic order ile yazılır

    @Test(priority = 0)//default 0 dır
    public void zlogin()
    {
        System.out.println("zLogin");
    }


    //@Ignore koyarsam homepage calsmaz cünkü login calısmıyor-no tests were found
    @Test(priority = -2)
    public void login()
    {
        System.out.println("Login -2");
    }

    @Test(priority = -1 )
    public void loginn()
    {
        System.out.println("Loginn -1");
    }



    @Test( dependsOnMethods ="login" , priority = 0)//defaulttan bile ilk yazılır
    public void homepage()//buraya basarsam login() method da calısıcak, cünkü o calısmazsa homepage() calısmaz
    {
        System.out.println("Homepage");
    }



    @Test(priority = 2)
    public void search()
    {
        System.out.println("Search");
    }

    @Test(priority = 1)
    public void result()
    {
        System.out.println("Result");
    }

    @Test(priority = 1)//if the priority is same as another method, compiler looks the alphabetic order
    public void resultt()
    {
        System.out.println("Resultt");
    }




    @Test(dependsOnMethods = "b", priority = 1)
    public void a() {System.out.println("a");}//normalde priority'ler aynı oldugu icin a calısır
    @Test(priority = 1)
    public void b() {System.out.println("b");}//b calısmadan a calısmaz oyuzden ilk b sonra a calıscak

    //priority si az olan ilk yazılır
    //priority leri aynı olan methodlar alphabetic sıraya göre sıralanır ve sonra dependsonMethods
    //özelliğine bakılır


}

